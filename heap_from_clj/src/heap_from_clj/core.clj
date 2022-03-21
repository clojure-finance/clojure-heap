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
         (update this :root (:lc (:root this)))
         ret))
  (heap_push [this data]
             (print 1))
  )

;; sort to put root to the right place
(defn heap_sort [this]
  (let [order (:order this) root (:root this)]
    (if (or (nil? (:rc root)) (nil? (:lc root)))
      (if (and (nil? (:rc root)) (nil? (:lc root)))
        this
        (if (nil? (:rc root))
          (if (or (and (= order "ASC") (<  (:data (:lc root)) (:data root))) (and (= order "DESC") (> (:data (:lc root)) (:data root))))
            this
            (let [lc_new (:root (heap_sort (Heaptree. (Heapnode. (:data root) (:lc (:lc root)) (:rc (:lc root))) order)))]
              (Heaptree. (Heapnode. (:data (:lc root)) lc_new nil) order)))
          (if (or (and (= order "ASC") (<  (:data (:rc root)) (:data root))) (and (= order "DESC") (> (:data (:rc root)) (:data root))))
            this
            (let [rc_new (:root (heap_sort (Heaptree. (Heapnode. (:data root) (:lc (:rc root)) (:rc (:rc root))) order)))]
              (Heaptree. (Heapnode. (:data (:rc root)) rc_new nil) order)))
          ))
      (if (= order "ASC")
        (if (< (:data (:rc root)) (:data (:lc root)))
          (if (< (:data root) (:data (:rc root)))
            this
            (let [rc_new (:root (heap_sort (Heaptree. (Heapnode. (:data root) (:lc (:rc root)) (:rc (:rc root))) order)))
                  root_new (Heapnode. (:data (:rc root)) (:lc root) rc_new)]
              (Heaptree. root_new order)))
          (if (< (:data root) (:data (:lc root)))
            this
            (let [lc_new (:root (heap_sort (Heaptree. (Heapnode. (:data root) (:lc (:lc root)) (:rc (:lc root))) order)))
                  root_new (Heapnode. (:data (:lc root)) lc_new (:rc root))]
              (Heaptree. root_new order))))
        (if (> (:data (:rc root)) (:data (:lc root)))
          (if (> (:data root) (:data (:rc root)))
            this
            (let [rc_new (:root (heap_sort (Heaptree. (Heapnode. (:data root) (:lc (:rc root)) (:rc (:rc root))) order)))
                  root_new (Heapnode. (:data (:rc root)) (:lc root) rc_new)]
              (Heaptree. root_new order)))
          (if (> (:data root) (:data (:lc root)))
            this
            (let [lc_new (:root (heap_sort (Heaptree. (Heapnode. (:data root) (:lc (:lc root)) (:rc (:lc root))) order)))
                  root_new (Heapnode. (:data (:lc root)) lc_new (:rc root))]
              (Heaptree. root_new order))))))))

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
    (heap_sort (Heaptree. root (:order this)))))

(defn heap_pop [this]
  (let [ret (:data (:root this))]))

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
    ))
