(ns heap.core-test
  (:require [clojure.test :refer :all]
            [heap.core :refer :all]))

(def n 1000000)
(def x (heap (fn [a b] (> (:id a) (:id b)))))

(deftest speed-test
  (println n)
  (println (time (do
                   (doseq [i (repeat n 0)]
                     (let [id (int (rand n))]
                       (push x {:id id})))
                   nil)))
  (println (time (do
                   (push x {:id n})
                   nil)))
  (println (time (do
                   (doseq [i (repeat n 0)]
                     (let []
                       (pop x)))
                   nil))))
