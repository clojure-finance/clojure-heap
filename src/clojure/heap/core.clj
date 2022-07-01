(ns heap.core)
;; (def heap (transient []))

(definterface HeapItf
  (setArr [arr] "change the heap array")
  (getArr [])
  (getComp []))

(defrecord Heap [^:unsynchronized-mutable arr
               ^:unsynchronized-mutable comp]
  HeapItf
  (getArr
    [this]
    arr)
  (getComp
    [this]
    comp))

(defn parent
  [i]
  (int (Math/floor (/ (- i 1) 2))))

(defn left
  [i]
  (+ (* 2 i) 1))

(defn right
  [i]
  (+ (* 2 i) 2))

(defn swap
  "swap the value of i and j in heap arr"
  [heap i j]
  (let [val-i (get heap i)
        val-j (get heap j)]
    (assoc! heap i val-j)
    (assoc! heap j val-i)))

(defn move-forward
  [heap i j]
  (let [val-j (get heap j)]
    (assoc! heap i val-j)))

(defn top-down
  [arr comp]
  (let [size (count arr)]
    (loop [curr 0]
      (let [l (left curr)
            r (+ l 1)]
        (if (>= l size)
          nil
          (if (>= r size)
            ;; only left exist
            (if (comp (get arr curr) (get arr l))
              nil
              (do
                (swap arr curr l)
                (recur l)))
            ;; both left and right
            (let [l-val (get arr l)
                  r-val (get arr r)
                  curr-val (get arr curr)
                  c-l (comp curr-val l-val)
                  c-r (comp curr-val r-val)
                  l-r (comp l-val r-val)]
              (if (and c-l c-r)
                nil
                (if (and c-l (not c-r))
                  (do
                    (swap arr curr r)
                    (recur r))
                  (if l-r
                    (do
                      (swap arr l curr)
                      (recur l))
                    (do
                      (swap arr r curr)
                      (recur r)))))
              )
            ))))))

(defn bottom-up
  [arr comp]
  (let []
    (loop [curr (dec (count arr))]
      (let [p (parent curr)]
        (if (< p 0)
          nil
          (if (not (comp (get arr p) (get arr curr)))
            (do
              (swap arr p curr)
              (recur p))
            nil))))))


;; external API
(defn heap
  ([comp]
   (Heap. (transient []) comp))
  ([comp value]
   (Heap. (transient [value]) comp)))

(defn get-size
  [heap]
  (count (.getArr heap)))

(defn peek
  [heap]
  (get (.getArr heap) 0))

(defn poll
  [heap]
  (let [arr (.getArr heap)
        size (count arr)
        comp (.getComp heap)]
    (if (> size 0)
      (let [ret (get arr 0)]
        (move-forward arr 0 (dec size))
        (pop! arr)
        (top-down arr comp)
        ret)
      nil)))

(defn add
  [heap value]
  (let [comp (.getComp heap)
        arr (.getArr heap)]
    (conj! arr value)
    (bottom-up arr comp)))