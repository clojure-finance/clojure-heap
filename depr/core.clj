;; Deprecated
;; (ns clojure.core
;;   (:gen-class))

;; ;; basic structure
;; (defrecord Heapnode [data lc rc])
;; (defrecord Heaptree [root order])

;; ;; construction function. default order is ASC
;; ;; order should only be "ASC" or "DESC"
;; (defn make-heaptree
;;   ([]
;;    (let [root (Heapnode. nil nil nil)]
;;    (Heaptree. root "ASC")))
;;   ([root]
;;    (Heaptree. root "ASC"))
;;   ([root order]
;;    (Heaptree. root order)))

;; ;; compare function
;; ;; represents relationship between parent and child node. 
;; (defn heap-compare [x1 x2]
;;   (if (< x1 x2) true false))

;; ;; sort to put root to the right place
;; (defn heap-sort [this]
;;   (let [order (:order this) root (:root this)]
;;     (if (or (nil? (:rc root)) (nil? (:lc root)))
;;       (if (and (nil? (:rc root)) (nil? (:lc root)))
;;         this
;;         (if (nil? (:rc root))
;;           (if (or
;;                (and (= order "ASC") (not (heap-compare  (:data (:lc root)) (:data root))))
;;                (and (= order "DESC") (heap-compare (:data (:lc root)) (:data root))))
;;             this
;;             (let [lc_new 
;;                   (:root (heap-sort (Heaptree. (Heapnode. (:data root) (:lc (:lc root)) (:rc (:lc root))) order)))]
;;               (Heaptree. (Heapnode. (:data (:lc root)) lc_new nil) order)))
;;           (if (or
;;                (and (= order "ASC") (heap-compare  (:data (:rc root)) (:data root)))
;;                (and (= order "DESC") (not (heap-compare (:data (:rc root)) (:data root)))))
;;             this
;;             (let [rc_new
;;                   (:root (heap-sort (Heaptree. (Heapnode. (:data root) (:lc (:rc root)) (:rc (:rc root))) order)))]
;;               (Heaptree. (Heapnode. (:data (:rc root)) nil rc_new) order)))
;;           ))
;;       (if (= order "ASC")
;;         (if (heap-compare (:data (:rc root)) (:data (:lc root)))
;;           (if (heap-compare (:data root) (:data (:rc root)))
;;             this
;;             (let [rc_new
;;                   (:root (heap-sort (Heaptree. (Heapnode. (:data root) (:lc (:rc root)) (:rc (:rc root))) order)))
;;                   root_new
;;                   (Heapnode. (:data (:rc root)) (:lc root) rc_new)]
;;               (Heaptree. root_new order)))
;;           (if (heap-compare (:data root) (:data (:lc root)))
;;             this
;;             (let [lc_new 
;;                   (:root (heap-sort (Heaptree. (Heapnode. (:data root) (:lc (:lc root)) (:rc (:lc root))) order)))
;;                   root_new 
;;                   (Heapnode. (:data (:lc root)) lc_new (:rc root))]
;;               (Heaptree. root_new order))))
;;         (if (not (heap-compare (:data (:rc root)) (:data (:lc root))))
;;           (if (not (heap-compare (:data root) (:data (:rc root))))
;;             this
;;             (let [rc_new 
;;                   (:root (heap-sort (Heaptree. (Heapnode. (:data root) (:lc (:rc root)) (:rc (:rc root))) order)))
;;                   root_new 
;;                   (Heapnode. (:data (:rc root)) (:lc root) rc_new)]
;;               (Heaptree. root_new order)))
;;           (if (not (heap-compare (:data root) (:data (:lc root))))
;;             this
;;             (let [lc_new
;;                   (:root (heap-sort (Heaptree. (Heapnode. (:data root) (:lc (:lc root)) (:rc (:lc root))) order)))
;;                   root_new
;;                   (Heapnode. (:data (:lc root)) lc_new (:rc root))]
;;               (Heaptree. root_new order))))))))

;; ;; find a leaf node to replace root while doing pop
;; (defn find-leave [this]
;;   (let [root (:root this) lc (:lc root) rc (:rc root)]
;;     (if (and (nil? lc) (nil? rc))
;;         [(:data root) nil]
;;       (if (or (nil? lc) (nil? rc))
;;         (if (nil? lc)
;;           (let [ret (find-leave (Heaptree. rc (:order this)))]
;;             [(first ret) (Heaptree. (Heapnode. (:data root) lc (:root (second ret))) (:order this))])
;;           (let [ret (find-leave (Heaptree. lc (:order this)))]
;;             [(first ret) (Heaptree. (Heapnode. (:data root) (:root (second ret)) rc) (:order this))]))
;;         (if (and (nil? (:lc lc)) (nil? (:rc lc)))
;;           [(:data (:lc root)) (Heaptree. (Heapnode. (:data root) nil rc) (:order this))]
;;           (if (and (nil? (:lc rc)) (nil? (:rc rc)))
;;             [(:data (:rc root)) (Heaptree. (Heapnode. (:data root) lc nil) (:order this))]
;;             (let [ret (find-leave (Heaptree. lc (:order this)))]
;;               (println ret)
;;               [(first ret) (Heaptree. (Heapnode. (:data root) (:root (second ret)) rc) (:order this))])))))))

;; ;; push data into the heap and sort. 
;; (defn heap-push [this data]
;;   (if (nil? (:data (:root this)))
;;     (Heaptree. (Heapnode. data nil nil) (:order this))
;;     (let [root (:root this) order (:order this)]
;;       (if (nil? (:lc root))
;;         (heap-sort (Heaptree. (Heapnode. data (Heapnode. (:data root) nil nil) (:rc root)) order))
;;         (if (nil? (:rc root))
;;           (heap-sort (Heaptree. (Heapnode. data (:lc root) (Heapnode. (:data root) nil nil)) order))
;;           (heap-sort (Heaptree. (Heapnode. data root nil) order)))))))

;; ;; pop the heap root and sort
;; (defn heap-pop [this]
;;   (if (and (nil? (:rc (:root this))) (nil? (:lc (:root this))))
;;     [(:data (:root this)) (Heaptree. (Heapnode. nil nil nil) (:order this))]
;;     (let [ret
;;           (:data (:root this))
;;           par
;;           (find-leave this)
;;           new_root
;;           (:root (second par))
;;           new_tree
;;           (heap-sort (Heaptree. (Heapnode. (first par) (:lc new_root) (:rc new_root)) (:order this)))]
;;       [ret new_tree])))

;; (defn heap-get-root [this]
;;   (:data (:root this)))

;; (defn -main
;;   [& args]
;;   ;; testing and example
;;   (do
;;     (def tree (make-heaptree))
;;     (def tree
;;       (heap-push tree 4))
;;     (def tree
;;       (heap-push tree 8))
;;     (def tree
;;       (heap-push tree 1))
;;     (def tree
;;       (heap-push tree 5))
;;     (println tree)
;;     (def tree
;;       (do (let [ret (heap-pop tree)] (println (first ret)) (second ret))))
;;     (def tree
;;       (do (let [ret (heap-pop tree)] (println (first ret)) (second ret))))
;;     (def tree
;;       (do (let [ret (heap-pop tree)] (println (first ret)) (second ret))))
;;     (def tree
;;       (do (let [ret (heap-pop tree)] (println (first ret)) (second ret))))
;;     (def tree
;;       (do (let [ret (heap-pop tree)] (println (first ret)) (second ret))))
;;     (println tree)
;;     (def tree
;;       (heap-push tree 6))
;;     (def tree
;;       (heap-push tree 3))
;;     (println (heap-get-root tree))))
