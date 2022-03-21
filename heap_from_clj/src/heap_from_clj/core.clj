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
              (Heaptree. (Heapnode. (:data (:rc root)) nil rc_new) order)))
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
(defn heap_push [this data]
  (let [root (:root this) order (:order this)]
    (if (nil? (:lc root))
      (heap_sort (Heaptree. (Heapnode. data (Heapnode. (:data root) nil nil) (:rc root)) order))
      (if (nil? (:rc root))
        (heap_sort (Heaptree. (Heapnode. data (:lc root) (Heapnode. (:data root) nil nil)) order))
        (heap_sort (Heaptree. (Heapnode. data root nil) order))))))

(defn find_leave [this]
  (let [root (:root this)]
    ))

(defn heap_pop [this]
  (let [ret (:data root)]))

(defn -main
  [& args]
  (do
    (def r (Heapnode. 9 nil nil))
    (def tree (Heaptree. r "ASC"))
    (def tree
      (heap_push
       (heap_push
        (heap_push
         (heap_push
          (heap_push tree 1)
          2)
         8)
        4)
       5))
    (println (:root tree))
    ))
