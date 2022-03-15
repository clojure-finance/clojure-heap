(ns heap_from_clj.core)
; (:import
 ; java.util.Date)

(defrecord Heapnode [data lc rc])
(defprotocol HEAP
 (heap_pop [tree])
  (heap_push [tree data]))
(defrecord Heaptree [root order]
  HEAP
  (heap_pop [this]
       (do
         (def ret (:data (:root this)))
         (def :root (:lc (:root this)))
         ret))
  (heap_push [this data]
             (do ))
  )
;; order should only be "ASC" or "DESC"
(defn heap_push_node [this data order]
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
         (heap_push_node lc data order)
         rc)))))

(defn heap_push [this data]
  (let [root (heap_push_node (:root this) data (:order this))]
    (Heaptree. root (:order this))))

(defn -main
  [& args]
  (do
    (def r (Heapnode. 0 nil nil))
    (def tree (Heaptree. r "ASC"))
    (def tree
      (heap_push
       (heap_push
        (heap_push
         (heap_push
          (heap_push tree 1)
          2)
         3)
        4)
       5))
    (println (:root tree))
    (println (.heap_pop tree))
    (println (:root tree))
    ))
