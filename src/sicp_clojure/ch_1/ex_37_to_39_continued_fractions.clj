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


(ns sicp_clojure.ch_1.ex_37_to_39_continued_fractions)


;//////  1.37 - 1.39  //////
;implement a function for computing k-terms continued fractions 
;via either a recursive or iterative process,
;and then use that to compute approximations for phi, e, tan(x)

;constants
(def tolerance 0.0001)  ;approximation exercises included
(def phi (/ (+ 1 (Math/sqrt 5)) 2))

;implementation (1.37)
(defn cont-frac [n d k]  ;iterative
  (letfn [(iter [k fraction]
            (if (= k 0)
              fraction
              (iter (- k 1) (/ (n k) (+ (d k) fraction)))))]
    (iter k 0)))

(defn cont-frac-recursive [n d k]  ;recursive
  (letfn [(rec [n d count]
            (if (= count (+ k 1))
              0
              (/ (n count) (+ (d count) (rec n d (+ count 1))))))]
    (rec n d 1)))

(defn phi-cf [k]  ;phi=(1+sqrt(5))/2 approximation
  (/ 1 (cont-frac (fn [i] i 1.0)
                  (fn [i] i 1.0)
                  k)))

;implementation (1.38)
(defn e-cf [k]  ;Euleur's e approximation
  (letfn ([d-param [i]
           (cond (< i 0) 0
                 (= (mod i 3) 2) (+ 2 (d-param (- i 3)))
                 :else 1)])
    (+ 2 (cont-frac (fn [i] i 1.0)
                    (fn [i] (d-param i))
                    k))))

(defn e-cf-recursive [k]  ;using cont-frac-recursive
  (letfn ([d-param [i]
           (cond (< i 0) 0
                 (= (mod i 3) 2) (+ 2 (d-param (- i 3)))
                 :else 1)])
    (+ 2 (cont-frac-recursive (fn [i] i 1.0)
                              (fn [i] (d-param i))
                              k))))

;implementation (1.39)
(defn tan-cf [x k]  ;Lambert's tan(x) approximation
  (/ x (+ 1 (cont-frac (fn [i] i (* (- x) x))
                       (fn [i] (- (* 2 (+ i 1)) 1.0))
                       (- k 1)))))
