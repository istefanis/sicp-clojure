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


(ns sicp_clojure.ch_1.ex_29_30_simpsons_rule_integration)


;//////  1.29 & 1.30  //////
;implement Simpson's Rule for the definite integral of a function,  
;as well as reformulate the sum procedure as an iterative process

;constants
(def tolerance 0.0001)  ;numerical analysis exercises included

;utils
(defn square [x] (* x x))
(defn cube [x] (* x x x))

(defn sum [term a next b]  ;recursive
  (if (> a b)
    0
    (+ (term a) (sum term (next a) next b))))

(defn integral-iterative [f a b dx]
  (letfn [(iter [a next b total]
            (if (> a b)
              (* total dx)
              (iter (next a dx) next b (+ total (f a)))))
          (add-dx [x dx] (+ x dx))]
    (iter (+ a (/ dx 2)) add-dx b 0.0)))

;implementation (1.29)
(defn integral-simpsons-rule [f a b n]  ;iterative
  (letfn [(iter [a next total h k]
            (if (> a b) (/ (* total h) 3.0)
                (cond (or (= k 0) (= k n))
                      (iter (next a h) next
                            (+ total (f a)) h (+ k 1))
                      (= (mod k 2) 0)
                      (iter (next a h) next
                            (+ total (* 2 (f a))) h (+ k 1))
                      :else
                      (iter (next a h) next
                            (+ total (* 4 (f a))) h (+ k 1)))))
          (add-h [x h] (+ x h))]
    (iter a add-h 0.0 (/ (- b a) n) 0)))

;implementation (1.30)
(defn sum-iterative [term a next b]  ;iterative
  (letfn [(iter [a next total]
            (if (> a b)
              total
              (iter (next a) next (+ total (term a)))))]
    (iter a next 0)))
