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


(ns sicp_clojure.ch_1.ex_19_fast_logn_fibonaccis)


;//////  1.19  //////
;implement a log(n) iterative process for the computation of 
;Fibonacci numbers, using the properties of a transformation T

;utils
(defn fib [n]
  (letfn [(iter [a b count]
            (if (= count 0)
              b
              (iter (+ a b) a (- count 1))))]
    (iter 1 0 n)))

;derivation
;
; applying T once:
; a'  <-  b*q + a*q + a*p
; b'  <-  b*p + a*q
;
; applying T twice:
; a'' <-  b'*q + a'*q + a'*p
;         = (b*p + a*q)*q + (b*q + a*q + a*p)*q + (b*q + a*q + a*p)*p 
;         = b*(2*p*q + q^2) + a*(2*p*q + q^2) + a*(q^2 + p^2)
;         = b*q' + a*q' + a*p'
; b'' <-  b'*p + a'*q
;         = (b*p + a*q)*p + (b*q + a*q + a*p)*q
;         = b*(p^2 + q^2) + a*(2*p*q + q^2)
;         = b*p' + a*q'
;
; where: q' = (2*p*q + q^2) and: p' = (q^2 + p^2)

;implementation
(defn fib-log [n]
  (letfn [(iter [a b p q count]
            (cond (= count 0) b
                  (even? count) (iter a
                                      b
                                      (+ (* q q) (* p p))  ;p'
                                      (+ (* 2 p q) (* q q))  ;q'
                                      (/ count 2))
                  :else (iter (+ (* b q) (* a q) (* a p))
                              (+ (* b p) (* a q))
                              p
                              q
                              (- count 1))))]
    (iter 1 0 0 1 n)))
