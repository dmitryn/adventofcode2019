(ns adventofcode-2019.day2
  (:require [clojure.java.io :as io]))

(defn- parse-input [s]
  (clojure.string/split s #","))

(defn- prepare-program [xs a b]
  (-> xs
      (assoc 1 a)
      (assoc 2 b)))

(def input (->> "day2.txt"
            io/resource
            slurp
            clojure.string/trim
            parse-input
            (map #(Integer/parseInt %))
            vec))


(defn run [a b]
  (let [in (prepare-program input a b)]
    (first
     (reduce (fn [ret [opcode a b position]]
               (case opcode
                 99 (reduced ret)
                 1 (assoc ret position (+ (get ret a) (get ret b)))
                 2 (assoc ret position (* (get ret a) (get ret b)))))
             in
             (partition 4 in)))))

(defn find-answer []
  (for [a (range 100) b (range 100)
        :let [x (run a b)]
        :while (= 19690720 x)]
    (+ b (* 100 a))))

(comment
  ;; (run 12 2)

  (find-answer)

  )
