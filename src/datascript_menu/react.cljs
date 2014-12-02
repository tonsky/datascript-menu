(ns datascript-menu.react)

;; Inspired by https://github.com/levand/quiescent/blob/master/src/quiescent.cljs

(def ^:dynamic *component* nil)

(defn node []
  (.getDOMNode *component*))

(defn get-args [component]
  @(aget (.-props component) "args"))

(defn set-args! [component & args]
  (reset! (aget (.-props component) "args") args)
  (.forceUpdate component))

(defn component [renderer & {:keys [did-mount will-unmount will-update did-update]}]
  (let [react-component
        (.createClass js/React
           #js {:getInitialState (fn [] (atom {}))
                :render
                (fn []
                  (this-as this
                    (binding [*component* this]
                      (apply renderer (get-args this)))))
                :componentDidMount
                (fn []
                  (when did-mount
                    (this-as this
                      (binding [*component* this]
                        (apply did-mount (get-args this))))))
                :componentWillUnmount
                (fn []
                  (when will-unmount
                    (this-as this
                      (binding [*component* this]
                        (apply will-unmount (get-args this))))))
                :componentWillUpdate
                (fn [_ _]
                  (when will-update
                    (this-as this
                      (binding [*component* this]
                        (will-update (node))))))
                :componentDidUpdate
                (fn [_ _]
                  (when did-update
                    (this-as this
                      (binding [*component* this]
                        (did-update (node))))))
                })]
    (fn [& args]
      (react-component #js {:args (atom args)}))))

(defn render [component node]
  (.renderComponent js/React component node))

(defn remember [k v]
  (swap! (.-state *component*) assoc k v))

(defn recall [k]
  (get @(.-state *component*) k))

(defn mount [render-fn mount-el]
  (let [dirty? (atom false)]
    (add-watch dirty? :render (fn [_ _ old-val new-val]
      (when (and (= old-val false) (= new-val true))
        (js/requestAnimationFrame
          (fn []
            (render (render-fn) mount-el)
            (reset! dirty? false))))))
    (fn []
      (reset! dirty? true))))
