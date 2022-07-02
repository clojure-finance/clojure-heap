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
                       (add x {:id id})))
                   nil)))
  (println (time (do
                   (add x {:id n})
                   nil)))
  (println (time (do
                   (doseq [i (repeat n 0)]
                     (let []
                       (poll x)))
                   nil))))
