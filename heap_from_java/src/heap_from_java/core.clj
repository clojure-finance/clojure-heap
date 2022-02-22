(ns heap_from_java.core
  (:import java.util.PriorityQueue))

(defn init
  "initialize queue"
  ([]
  (let [pq (PriorityQueue.)] pq) (let [pq (PriorityQueue.)] pq) )
  ([comparator]
   (let [pq (PriorityQueue.)] pq) (let [pq (PriorityQueue. comparator)] pq) ))

(defn add
  "add element into priority queue"
  [pq e]
  (.add pq e))

(defn poll
  "poll out the first element in queue"
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
   (add pq 5)
   (add pq 3)
   (add pq 7)
   (println (poll pq) (poll pq))
   (add pq 8)
   (println (size pq))))
