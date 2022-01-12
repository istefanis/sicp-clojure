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


(ns sicp_clojure.ch_2.ex_07_to_16_interval_arithmetic)


;//////  2.7 - 2.10  //////
;implement interval arithmetic

;implementation (2.7)
(defn make-interval [a b] 
  (vector a b))

(defn upper-bound [i] 
  (max (first i) (second i)))

(defn lower-bound [i] 
  (min (first i) (second i)))

(defn print-interval [i]
  (println "[" (lower-bound i) "," (upper-bound i) "]"))

(defn equal-interval? [i1 i2]
  (and (= (upper-bound i1) (upper-bound i2))
       (= (lower-bound i1) (lower-bound i2))))
  
;implementation (2.8)
(defn add-interval [i1 i2]
  (make-interval (+ (lower-bound i1) (lower-bound i2))
                 (+ (upper-bound i1) (upper-bound i2))))

(defn sub-interval [i1 i2]
  (make-interval (- (lower-bound i1) (upper-bound i2))
                 (- (upper-bound i1) (lower-bound i2))))

(defn mul-interval [i1 i2]
  (let [p1 (* (lower-bound i1) (lower-bound i2))
        p2 (* (lower-bound i1) (upper-bound i2))
        p3 (* (upper-bound i1) (lower-bound i2))
        p4 (* (upper-bound i1) (upper-bound i2))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

;implementation (2.9)
(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2.0))  ;always non-negative

;width of sum & difference of intervals:
;
; (width (add-interval i1 i2))
; = (/ (- (upper-bound (add-interval i1 i2)) 
;         (lower-bound (add-interval i1 i2))) 2))
; = (/ (- (+ (upper-bound i1) (upper-bound i2)) 
;         (+ (lower-bound i1) (lower-bound i2))) 2))
; = (/ (- (upper-bound i1) (lower-bound i1)) 2) + 
;   (/ (- (upper-bound i2) (lower-bound i2)) 2)
; = (width i1) + (width i2)
;
; (width (sub-interval i1 i2))
; = (/ (- (upper-bound (sub-interval i1 i2)) 
;         (lower-bound (sub-interval i1 i2))) 2))
; = (/ (- (- (upper-bound i1) (lower-bound i2)) 
;         (- (lower-bound i1) (upper-bound i2))) 2))
; = (/ (+ (- (upper-bound i1) (lower-bound i2)) 
;         (- (upper-bound i2) (lower-bound i1))) 2))
; = (width i1) + (width i2)

;implementation (2.10)
(defn div-interval [i1 i2]
  (if (= (lower-bound i2) (upper-bound i2))
    (throw (ArithmeticException. (str "The second interval spans zero: " i2)))
    (mul-interval i1
                  (make-interval (/ 1 (upper-bound i2))
                                 (/ 1 (lower-bound i2))))))


;//////  2.11 - 2.13  //////
;alternative implementations of interval and interval operations

;implementation (2.11)
(defn mul-interval-version2 [i1 i2]
  (let [l1 (lower-bound i1)
        u1 (upper-bound i1)
        l2 (lower-bound i2) 
        u2 (upper-bound i2)]
    (cond (>= l1 0) (cond (>= l2 0) (make-interval (* l1 l2) (* u1 u2))
                          (<= u2 0) (make-interval (* u1 l2) (* l1 u2))
                          :else (make-interval (* u1 l2) (* u1 u2)))
          (<= u1 0) (cond (>= l2 0) (make-interval (* l1 u2) (* u1 l2))
                          (<= u2 0) (make-interval (* u1 u2) (* l1 l2))
                          :else (make-interval (* l1 u2) (* l1 l2)))
          :else (cond (>= l2 0) (make-interval (* l1 u2) (* u1 u2))
                      (<= u2 0) (make-interval (* u1 l2) (* l1 l2))
                      :else (make-interval (min (* l1 u2) (* u1 l2))
                                           (max (* l1 l2) (* u1 u2)))))))

(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))

(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))
   
;implementation (2.12)
(defn make-center-percent [c p]
  (make-interval (* c (- 1 (/ p 100.0M))) 
                 (* c (+ 1 (/ p 100.0M)))))

(defn percent [i]
  (* (/ (- (upper-bound i) (center i)) (center i)) 100.0M))

;implementation (2.13)
(defn percent-mul-interval [i1 i2]
  (+ (percent i1) (percent i2)))

;derivation:
;
; for positive numbers, the bounds of the product are:
;   (* c1 c2 (- 1 p1) (- 1 p2)))      lower bound
;   (* c1 c2 (+ 1 p1) (+ 1 p2))))     upper bound
; where: {ci: centers, pi: percent tolerances}
;
; for small pi's, the second order terms can be discarded (since: p1*p2 ~= 0),
; therefore: pmul12 = p1 + p2


;//////  2.14 - 2.16  //////
;'dependency problem' of interval arithmetic

;constants
(def tolerance 0.0001)

;implementation (2.14 - 2.16)
(defn par1 [r1 r2]
  (div-interval (mul-interval r1 r2)
                (add-interval r1 r2)))

(defn par2 [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one
                  (add-interval (div-interval one r1) (div-interval one r2)))))

;explanation (2.14 - 2.16)
;
; generally, in operations of intervals, algebraic equivalence doesn't hold, 
; when the same interval appears more than once and each occurence is
; considered independently ('dependency problem'), as that makes the 
; uncertainty increase:
;
;   example 1:  (i1 + i2) - i2 != i1
;   example 2:  (i1 / i2) * i2 != i1
;   example 3:  (i1 - i1) != [0, 0]
;   example 4:  (i1 / i1) != [1, 1]

(defn example1 [i1 i2] (sub-interval (add-interval i1 i2) i2))
(defn example2 [i1 i2] (mul-interval (div-interval i1 i2) i2))
(defn example3 [i] (sub-interval i i))
(defn example4 [i] (div-interval i i))

; because in par2 each interval appears only once, that's why it gives 
; smaller uncertainty than par1

; nevertheless, there may be some intervals, for which the equivalence holds:
;   (example2 (make-interval -1 1) (make-interval -4 4)) = [-1 1]
