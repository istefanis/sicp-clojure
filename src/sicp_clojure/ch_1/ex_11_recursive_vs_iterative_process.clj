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


(ns sicp_clojure.ch_1.ex_11_recursive_vs_iterative_process)


;//////  1.11  //////
;compute a function defined by a rule, 
;using either a recursive or iterative process

;implementation
(defn f-recursive [n]  ;exponential step growth
  (if (< n 3)
    n
    (+ (f-recursive (- n 1))
       (* 2 (f-recursive (- n 2)))
       (* 3 (f-recursive (- n 3))))))

(defn f-iterative [n]  ;linear step growth
  (letfn [(iter [a b c counter]  ;defined as a nested function here
            (if (= counter 0)
              c
              (iter b c (+ (* 3 a) (* 2 b) c) (- counter 1))))]
    (if (< n 3)
      n
      (iter 0 1 2 (- n 2)))))
