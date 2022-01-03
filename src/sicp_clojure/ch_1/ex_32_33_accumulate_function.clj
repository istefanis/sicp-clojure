; sicp-clojure - SICP exercise solutions in Clojure
; Copyright (C) 2022  Ioannis Stefanis

; This file is part of sicp-clojure.

; sicp-clojure is free software: you can redistribute it and/or modify 
; it under the terms of the GNU General Public License as published by 
; the Free Software Foundation, either version 3 of the License, or 
; (at your option) any later version.

; sicp-clojure is distributed in the hope that it will be useful, but 
; WITHOUT ANY WARRANTY; without even the implied warranty of 
; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
; General Public License for more details.

; You should have received a copy of the GNU General Public License 
; along with sicp-clojure. If not, see <https://www.gnu.org/licenses/>. 


(ns sicp_clojure.ch_1.ex_32_33_accumulate_function)


;//////  1.32 & 1.33  //////
;implement the more generic accumulate and filtered-accumulate functions,
;and use the latter for some computations

;utils
(defn inc-by-2 [x] (+ x 2))
(defn square [x] (* x x))
(defn divides? [a b] (= (mod b a) 0))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n  ;square root step growth
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n] (find-divisor n 2))
(defn prime? [n] (= n (smallest-divisor n)))

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (mod a b))))
(defn relative-prime? [a b] (= 1 (gcd a b)))

(defn sum [term a next b]  ;recursive
  (if (> a b)
    0
    (+ (term a) (sum term (next a) next b))))

(defn product [function a next b]  ;recursive
  (if (> a b)
    1
    (* (function a)
       (product function (next a) next b))))

;implementation (1.32 part a)
(defn accumulate [combiner null-value term a next b]  ;recursive
  (if (> a b)
    null-value
    (combiner (term a)
              (accumulate combiner null-value term (next a) next b))))

(defn accumulate-version2 [combiner null-value term a next b]  ;recursive
  (letfn [(rec [a]
            (if (> a b)
              null-value
              (combiner (term a) (rec (next a)))))]
    (rec a)))

;implementation (1.32 part b)
(defn accumulate-iterative [combiner null-value term a next b]  ;iterative
  (letfn [(iter [a total]
            (if (> a b)
              total
              (iter (next a) (combiner total (term a)))))]
    (iter a null-value)))

;implementation (1.33)
(defn filtered-accumulate [combiner null-value term a next b filter] 
  (letfn [(iter [a total]
            (cond (> a b) total
                  (filter a) (iter (next a) (combiner total (term a)))
                  :else (iter (next a) total)))]
    (iter a null-value)))

;implementation (1.33 part a)
(def a 1N)
(def b 100)
(println (filtered-accumulate + 0 square a inc b prime?))

;implementation (1.33 part b)
(def n 10)
(defn relative-prime-to-n? [a] (relative-prime? a n))
(println (filtered-accumulate * 1 square 1N inc n relative-prime-to-n?))
