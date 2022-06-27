(ns java.core
  (:refer-clojure :exclude [remove pop])
  (:gen-class)
  (:import [java.util PriorityQueue]))

(defn init
  "initialize an empty heap"
  ([]
   (let [pq (PriorityQueue.)] pq))
  ([comparator]
   (let [pq (PriorityQueue. comparator)] pq)))

(defn push
  "add element into priority queue"
  [pq e]
  (.add pq e)
  pq)

(defn get-root
  "pop the root element and do not update priority queue"
  [heap]
  (.peek heap))

(defn pop
  "pop out the first element in queue and update queue"
  [pq]
  (.poll pq))

(defn size
  "give the size of  queue"
  [pq]
  (.size pq))

(defn -main
  "main function"
  [& args]
 (let [pq (init >)]
   (push pq 5)
   (push pq 3)
   (push pq 7)
   (println "size" (size pq))
   (println (get-root pq))
   (println (pop pq))
   (println (pop pq))
   (push pq 8)
   (println "size" (size pq))))
