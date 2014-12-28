(defproject datascript-menu "0.1.0"
  :global-vars  {*warn-on-reflection* true}

  :dependencies [
    [org.clojure/clojure "1.7.0-alpha4"]
    [org.clojure/clojurescript "0.0-2511"]
    [org.clojure/core.async "0.1.338.0-5c5012-alpha"]
    [datascript "0.7.2"]
    [rum "0.1.0"]
  ]

  :plugins [
    [lein-cljsbuild "1.0.3"]
  ]

  :cljsbuild { 
    :builds [
      { :id "prod"
        :source-paths  ["src"]
        :compiler {
          :preamble      ["react/react.min.js"]
          :output-to     "web/menu.min.js"
          :optimizations :advanced
          :pretty-print  false
        }}
      { :id "dev"
        :source-paths  ["src"]
        :compiler {
          :output-to     "web/menu.js"
          :output-dir    "web/target-cljs"
          :optimizations :none
          :source-map    true
        }}
  ]}
)
