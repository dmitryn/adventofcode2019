(ns adventofcode-2019.day2
  (:require [clojure.java.io :as io]))

(defn- parse-input [s]
  (clojure.string/split s #","))

(defn- prepare-program [xs]
  (-> xs
      (assoc 1 12)
      (assoc 2 2)))

(defn run []
  (let [input (->> "day2.txt"
                   io/resource
                   slurp
                   clojure.string/trim
                   parse-input
                   (map #(Integer/parseInt %))
                   vec
                   prepare-program)]
    (first
     (reduce (fn [ret [opcode a b position]]
               (case opcode
                 99 (reduced ret)
                 1 (assoc ret position (+ (get ret a) (get ret b)))
                 2 (assoc ret position (* (get ret a) (get ret b)))))
             input
             (partition 4 input)))))

(comment
  (run)
  )
