(defproject com.github.clojure-finance/clojure-heap "1.0.0"
  :description "Pure Clojure implementation of a heap"
  :url "https://github.com/clojure-finance/clojure-heap"
  :license {:name "MIT"
            :url "https://github.com/clojure-finance/clojure-heap/blob/main/LICENSE"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :source-paths ["src/clojure"]
  ;; :main ^:skip-aot clojure.core2/heap
  :repl-options {:init-ns heap.core
                 :timeout 180000})
