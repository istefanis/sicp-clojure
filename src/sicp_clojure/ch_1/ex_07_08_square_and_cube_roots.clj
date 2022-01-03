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


(ns sicp_clojure.ch_1.ex_07_08_square_and_cube_roots)


;//////  1.7 & 1.8  //////
;compute the square and cube roots of a number, using robust 
;iterative processes that can handle both small and large numbers
;(special cases of Newton's numerical method for solving equations 
; - see ex. 1.40 - 1.46 for a generic solution to finding roots)

;constants
(def tolerance 0.0001)  ;numerical analysis exercises
(def first-guess 1.0)

;utils
(defn average [x y] (/ (+ x y) 2))
(defn square [x] (* x x))
(defn cube [x] (* x x x))

(defn good-enough?-square-root [guess x]
  (< (Math/abs (- (square guess) x)) tolerance))

(defn improve-square-root [guess x] 
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough?-square-root guess x)
    guess
    (sqrt-iter (improve-square-root guess x) x)))

(defn square-root [x] (sqrt-iter first-guess x))

;implementation (1.7)
(defn good-enough?-square-root-relative [guess x]
  (< (Math/abs (/ (- (improve-square-root guess x) guess) guess))
     (/ tolerance 10)))  ;a smaller tolerance is required here

(defn sqrt-iter-relative [guess x]
  (if (good-enough?-square-root-relative guess x)
    guess
    (sqrt-iter-relative (improve-square-root guess x) x)))

(defn square-root-relative [x] (sqrt-iter-relative first-guess x))

;implementation (1.8)
(defn improve-cube-root [guess x]
  (/ (+ (/ x (square guess)) (* 2N guess)) 3.0))

(defn good-enough?-cube-root [guess x]
  (< (Math/abs (- (cube guess) x)) tolerance))

(defn cube-root-iter [guess x]
  (if (good-enough?-cube-root guess x)
    guess
    (cube-root-iter (improve-cube-root guess x) x)))

(defn cube-root [x] (cube-root-iter first-guess x))
