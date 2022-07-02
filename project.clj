(defproject heap_from_java "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "MIT"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :source-paths ["src/clojure"]
  ;; :main ^:skip-aot clojure.core2/heap
  :repl-options {:init-ns heap.core
                 :timeout 180000})
