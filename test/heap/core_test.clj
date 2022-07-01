(ns heap.core-test
  (:require [clojure.test :refer :all]
            [heap.core :refer :all]
            [clojure.data.priority-map :as pm]))

(def n 100000)
(def x (heap >=))
(def y (pm/priority-map))

(deftest speed-test
  (println n)
  (println (time (do
                   (doseq [i (repeat n 0)]
                     (let [id (int (rand n))]
                       (def y (assoc y (keyword (str id)) id)))))))
  (println (time (do
                   (def y (assoc y :id n))
                   nil)))
  (println (time (do
                   (pop y)
                   nil)))
  (println (time (do
                   (doseq [i (repeat (count y) 0)]
                     (let []
                       (def y (pop y))))
                   nil)))
  (println (time (do
                   (doseq [i (repeat n 0)]
                     (let [id (int (rand n))]
                       (add x id)))
                   nil)))
  (println (time (do
                   (add x n)
                   nil)))
  (println (time (do
                   (poll x)
                   nil)))
  (println (time (do
                   (doseq [i (repeat n 0)]
                     (let []
                       (poll x)))
                   nil))))
