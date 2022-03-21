(ns heap_from_clj.core)
; (:import
 ; java.util.Date)

(defrecord Heapnode [data lc rc])
(defrecord Heaptree [root order])

;; sort to put root to the right place
(defn heap_sort [this]
  (let [order (:order this) root (:root this)]
    (if (or (nil? (:rc root)) (nil? (:lc root)))
      (if (and (nil? (:rc root)) (nil? (:lc root)))
        this
        (if (nil? (:rc root))
          (if (or (and (= order "ASC") (>  (:data (:lc root)) (:data root))) (and (= order "DESC") (< (:data (:lc root)) (:data root))))
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
  (let [root (:root this) lc (:lc root) rc (:rc root)]
    (if (and (nil? lc) (nil? rc))
        [(:data root) nil]
      (if (or (nil? lc) (nil? rc))
        (if (nil? lc)
          (let [ret (find_leave (Heaptree. rc (:order this)))]
            [(first ret) (Heaptree. (Heapnode. (:data root) lc (:root (second ret))) (:order this))])
          (let [ret (find_leave (Heaptree. lc (:order this)))]
            [(first ret) (Heaptree. (Heapnode. (:data root) (:root (second ret)) rc) (:order this))]))
        (if (and (nil? (:lc lc)) (nil? (:rc lc)))
          [(:data (:lc root)) (Heaptree. (Heapnode. (:data root) nil rc) (:order this))]
          (if (and (nil? (:lc rc)) (nil? (:rc rc)))
            [(:data (:rc root)) (Heaptree. (Heapnode. (:data root) lc nil) (:order this))]
            (let [ret (find_leave (Heaptree. lc (:order this)))]
              (println ret)
              [(first ret) (Heaptree. (Heapnode. (:data root) (:root (second ret)) rc) (:order this))])))))))

(defn heap_pop [this]
  (let [ret (:data (:root this))
        par (find_leave this)
        new_root (:root (second par))
        new_tree (heap_sort (Heaptree. (Heapnode. (first par) (:lc new_root) (:rc new_root)) (:order this)))]
    [ret new_tree]))

(defn -main
  [& args]
  (do
    (def r (Heapnode. 9 nil nil))
    (def tree (Heaptree. r "ASC"))
    (def tree
      (heap_push tree 1))
    (def tree
      (heap_push tree 3))
    (def tree
      (heap_push tree 10))
    (def tree
      (heap_push tree 8))
    (println tree)
    (def tree
      (do
        (let [ret (heap_pop tree)]
          (println (first ret))
          (second ret))))
(println tree)
    (def tree
      (do
        (let [ret (heap_pop tree)]
          (println (first ret))
          (second ret))))
(println tree)
    (def tree
      (do
        (let [ret (heap_pop tree)]
          (println (first ret))
          (second ret))))
(println tree)
    (def tree
      (do
        (let [ret (heap_pop tree)]
          (println (first ret))
          (second ret))))
    (println tree)))
