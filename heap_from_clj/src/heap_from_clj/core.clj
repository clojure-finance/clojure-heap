(ns heap_from_clj.core)
; (:import
 ; java.util.Date)

(defrecord Heapnode [data lc rc])
(defrecord Heaptree [root])
(defn heap_push [this data]
  (def root this)
  (if (nil? (:lc root))
    (let [node (Heapnode. data nil nil)]
       (Heapnode. (:data root) node nil))
    (if (nil? (:rc root))
      (let [node (Heapnode. data nil nil)]
        (Heapnode.
         (:data root)
         (:lc root)
         node))
      (let [rc (:rc root) lc (:lc root)]
        (Heapnode.
         (:data root)
         (heap_push lc data)
         rc)
        ))))

(defn -main
  [& args]
  (do
    (def root (Heapnode. 0 nil nil))
    (def root
      (heap_push
       (heap_push
        (heap_push
         (heap_push
          (heap_push root 1)
          2)
         3)
        4)
       5))
    (println "34"  root)))
