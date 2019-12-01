(ns adventofcode-2019.day1
  (:require [clojure.java.io :as io]))

(defn- mass->fuel [n]
  (-> n
      (/ 3)
      (Math/floor)
      (- 2)))

(defn iter [n]
  (let [fuel (mass->fuel (Integer/parseInt n))]
    (apply +
           (take-while pos?
                       (iterate mass->fuel fuel)))))

(defn- total-fuel [xs]
  (->> xs
       (map iter)
       (apply +)))

(defn- parse-input [s]
  (clojure.string/split s #"\n"))

(defn run []
  (->> "day1.txt"
       io/resource
       slurp
       parse-input
       total-fuel
       println))

(comment
  (run)
  )
