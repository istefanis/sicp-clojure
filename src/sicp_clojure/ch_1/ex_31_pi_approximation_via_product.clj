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


(ns sicp_clojure.ch_1.ex_31_pi_approximation_via_product)


;//////  1.31  //////
;implement the product function using either a recursive or 
;iterative process, and then use that to define a factorial function  
;as well as approximate pi 

;constants
(def tolerance 0.0001)  ;approximation application included

;utils
(defn square [x] (* x x))
(defn inc-by-2 [x] (+ x 2))

;implementation (part a)
(defn product [function a next b]  ;recursive
  (if (> a b)
    1
    (* (function a)
       (product function (next a) next b))))

(defn factorial [n]
  (product identity 1 inc n))

(defn pi-approx [n]  ;Wallis product
  (* 8.0 (/ (product square 4N inc-by-2 n)
            (* n (product square 3N inc-by-2 n)))))
  ;using BigInt here (ex. 4N) to avoid IntOverflow

;implementation (part b)
(defn product-iterative [function a next b]  ;iterative
  (letfn [(iter [a total]
            (if (> a b) 
              total
              (iter (next a) (* total (function a)))))]
    (iter a 1)))

(defn product-loop-recur [function a next b]  ;loop-recur
  (loop [a a
         total 1]
    (if (> a b)
      total
      (recur (next a) (* total (function a))))))

;the iterative version supports larger n values, without overflows;
;also, using BigInt here (ex. 4N) to avoid IntOverflow:
(defn pi-approx-iterative [n]
  (* 8.0 (/ (product-iterative square 4N inc-by-2 n)
            (* n (product-iterative square 3N inc-by-2 n)))))

;the loop-recur version supports even larger n values, 
;without overflows (as it doesn't consume the stack);
;also, using BigInt here (ex. 4N) to avoid IntOverflow:
(defn pi-approx-loop-recur [n]
  (* 8.0 (/ (product-loop-recur square 4N inc-by-2 n)
            (* n (product-loop-recur square 3N inc-by-2 n)))))
