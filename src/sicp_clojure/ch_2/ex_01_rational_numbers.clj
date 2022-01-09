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


(ns sicp_clojure.ch_2.ex_01_rational_numbers)


;//////  2.1  //////
;improve the function that defines rational numbers, 
;so that it can also normalize its sign

;utils
(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (mod a b))))

;instead of: {list, car, cdr, cadr} of other Lisp dialects,
;in Clojure we can use: {vector, first, next, second} 
;(a two-element vector does not resemble a cons pair, 
;but a list of two elements)

(defn make-rat-version1 [n d]
    (vector n d))

(defn make-rat-version2 [n d]  ;normalizes sign
  (let [g (gcd n d)]           ;(the existing implementation works fine)
    (vector (/ n g) (/ d g))))

(defn make-rat-version3 [n d]  ;normalizes sign
  ((fn [g] (vector (/ n g) (/ d g)))  ;function application
   (gcd n d)))

(def make-rat make-rat-version2)

(defn numer [x] (first x))
(defn denom [x] (second x))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y)) (* (denom x) (numer y)))
            (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y)) (* (denom x) (numer y)))
            (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y)) 
            (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y)) 
            (* (denom x) (numer x))))

(defn equal-rat? [x y]
  (= (* (numer x) (denom y)) (* (denom x) (numer y))))

(defn print-rat [x]
  (println (numer x) "/" (denom x)))

;implementation
(defn sign [x]
  (if (= x 0)
    0
    (/ x (Math/abs x))))

(defn make-rat-version4 [n d]  ;normalizes sign
  (let [g (gcd (Math/abs n) (Math/abs d))
        s (sign d)]
    (vector (/ n (* g s))
            (/ d (* g s)))))

(defn make-rat-version5 [n d]  ;normalizes sign
  (let [g (gcd (Math/abs n) (Math/abs d))
        s (sign d)]
    (vector (* (/ n g) s)
            (/ (Math/abs d) g))))
