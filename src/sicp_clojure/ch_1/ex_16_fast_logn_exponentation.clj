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


(ns sicp_clojure.ch_1.ex_16_fast_logn_exponentation)


;//////  1.16  //////
;implement an iterative log(n) version of fast-expt

;utils
(defn square [x] (* x x))
(defn halve [x] (* 1/2 x))

(defn fast-expt [base n]  ;logarithmic step growth
  (cond
    (= n 0) 1
    (even? n) (square (fast-expt base (halve n)))
    :else (* base (fast-expt base (- n 1)))))  ;linear space growth

;implementation
(defn fast-expt-iterative [base n]  ;logarithmic step growth
  (letfn [(iter [acc b n]
            (cond
              (= n 0) acc
              (even? n) (iter acc (square b) (halve n))
              :else (iter (* acc b) b (- n 1))))]  ;constant in space
    (iter 1 base n)))
