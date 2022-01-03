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


(ns sicp_clojure.ch_1.ex_12_pascals_triangle)


;//////  1.12  //////
;compute Pascal's triangle elements via a recursive process

;implementation
(defn compute-element [element line]  ;coordinates
  (if (or (= element 1) (= element line))
    1
    (+ (compute-element (- element 1) (- line 1)) ;recursive process
       (compute-element element (- line 1)))))

;extra functions for printing Pascal's triangle
(defn print-remaining-line [first-element line]
  (when (<= first-element line)
    ;pretty-printing
    (when (and (= first-element 1) (> line 1)) (newline))
    (when (> first-element 1) (print " "))
    (print (compute-element first-element line))
    (print-remaining-line (+ first-element 1) line)))
  
(defn print-pascals-triangle [depth]
  (letfn [(iter [line]
            (print-remaining-line 1 line)
            (when (< line depth) (iter (+ line 1))))]
    (iter 1)))
