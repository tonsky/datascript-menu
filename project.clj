(defproject datascript-menu "0.1.0"
  :global-vars  {*warn-on-reflection* true}

  :dependencies [
    [org.clojure/clojure "1.7.0-beta1"]
    [org.clojure/clojurescript "0.0-3196"]
    [datascript "0.10.0"]
    [rum "0.2.6" :exclusions [cljsjs/react]]
  ]

  :plugins [
    [lein-cljsbuild "1.0.3"]
  ]

  :cljsbuild { 
    :builds [
      { :id "prod"
        :source-paths  ["src"]
        :compiler {
          :output-to     "target/menu.js"
          :optimizations :advanced
          :pretty-print  false
          :warnings {:single-segment-namespace false}
          :foreign-libs [
            { :file "react/react-0.12.2.min.js"
              :provides ["cljsjs.react"]}
          ]
          :externs ["react/externs.js"]
        }}
      { :id "dev"
        :source-paths  ["src"]
        :compiler {
          :main          datascript-menu
          :output-to     "target/menu.js"
          :output-dir    "target"
          :optimizations :none
          :source-map    true
          :warnings {:single-segment-namespace false}
          :foreign-libs [
            { :file "react/react-0.12.2.min.js"
              :provides ["cljsjs.react"]}
          ]
        }}
  ]}
)
