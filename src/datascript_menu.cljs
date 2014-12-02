(ns datascript-menu
  (:require
    [clojure.string :as str]
    [sablono.core :as s :include-macros true]
    [datascript :as d]
    [datascript-menu.react :as r]
    [datascript-menu.gen :as gen]))

(enable-console-print!)

(def ^:private schema {
  :guest/order    {:db/valueType :db.type/ref}
  :order/position {:db/valueType :db.type/ref
                   :db/cardinality :db.cardinality/many}
})

(def conn (d/create-conn schema))

;;;; Reactions

(def reactions (atom {}))

(defn listen-for! [key path fragments callback]
  (swap! reactions assoc-in (concat [path] fragments [key]) callback))

(defn unlisten-for! [key path fragments]
  (swap! reactions update-in (concat [path] fragments) dissoc key))

(d/listen! conn
  (fn [tx-data]
    (doseq [datom (:tx-data tx-data)
            [path m] @reactions
            :let [fragments ((apply juxt path) datom)]
            [key callback] (get-in m fragments)]
      (callback datom tx-data key))))

;;;; Fixtures
 
(d/transact! conn (map (fn [[n d]] {:position/name n
                                    :position/desc d})
                       gen/positions))

(def position-ids (map first (d/q '[:find ?e :where [?e :position/name]] @conn)))

(dotimes [i 100]
  (let [guests (repeatedly (+ (rand-int 4) 1)
                           (fn [] {:guest/name (gen/gen-name)
                                   :guest/order -1}))
        order {:db/id -1
               :order/id (+ 9999 (rand-int 90000))
               :order/position (take (+ (rand-int 3) 1) (shuffle position-ids))}]
    (d/transact! conn (into [order] guests))))

;;;; Views

(def position-view (r/component (fn [p]
  (s/html
    [:li.position
      (:position/name p)
      [:span.id (:db/id p)]]))
  :did-mount (fn [p]
               (let [c r/*component*]
                 (listen-for! (rand) [:e :a] [(:db/id p) :position/name]
                   (fn [datom tx-data key]
                     (r/set-args! c (d/entity (:db-after tx-data) (:db/id p)))
                     ))))
  :will-unmount (fn [p]
                  ;; TODO unlisten
                  )))

(def order (r/component (fn [order]
  (s/html
    [:.order
      (str "Order #" (:order/id order))
      [:span.id (:db/id order)]
      [:ul.positions
        (for [p (:order/position order)]
          (position-view p))]]))))

(def person (r/component (fn [guest]
  (s/html
    [:.person
      (:guest/name guest)
      [:span.id (:db/id guest)]
      (order (:guest/order guest))]))))

(def position-edit (r/component (fn [position]
  (s/html
    [:.position-edit
      [:input {:type "text"
               :value (:position/name position)
               :on-change (fn [e]
                            (let [new-val (.. e -target -value)]
                              (d/transact! conn [[:db/add (:db/id position) :position/name new-val]])))
               }]
      [:span.id (:db/id position)]]))))

(def page (r/component (fn [db]
  (s/html
    [:.page
      [:.guests
        [:h1 "Guests"]
        (for [[eid] (sort (d/q '[:find ?e :where [?e :guest/name]] db))]
          (person (d/entity db eid)))]
      [:.menu
        [:h1 "Menu"]
        (for [[eid] (sort (d/q '[:find ?e :where [?e :position/name]] db))]
          (position-edit (d/entity db eid)))]]))))

(let [rerender (r/mount #(page @conn) (.-body js/document))]
  (rerender) ;; initial re-render
;;   (d/listen! conn (fn [_] (rerender)))
  )
