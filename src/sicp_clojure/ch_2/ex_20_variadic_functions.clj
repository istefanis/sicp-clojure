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


(ns sicp_clojure.ch_2.ex_20_variadic_functions)


;//////  2.20  //////
;implement a function with a variable number of arguments (variadic fuction)

;in Clojure: 
; - instead of "." of other Lisp dialects, the "&" symbol is used  
;   in variadic functions
; - the remaining arguments are collected in a Clojure list

;implementation
(defn same-parity [x & y]  ;first and remaining arguments 
  (letfn [(iter [acc counter return-odds?]
            (cond (= (+ (count y) 1) counter)
                  (cons x acc)
                  (= (mod (nth y (- (count y) counter)) 2) return-odds?)
                  (iter (cons (nth y (- (count y) counter)) acc)
                        (+ counter 1) return-odds?)
                  :else
                  (iter acc (+ counter 1) return-odds?)))]
    (iter nil 1 (mod x 2))))
