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


(ns sicp_clojure.ch_2.ex_21_to_23_map_and_for_each_functions)


;//////  2.21 - 2.23  //////
;implement the 'map' and 'for-each' functions, to introduce more abstraction

;note that the (2.21 & 2.22) implemented functions, although named 
;'square-list...', here they do not return a Clojure 'list', but rather 
;a 'seq' (see the 'ex_17_to_19_elementary_collections.clj' discussion)

;utils
(defn square [x] (* x x))
(defn cube [x] (* x x x))

(defn map-version2 [f items]  ;like the 'map' built-in function
  (if (nil? items)
    nil
    (cons (f (first items)) (map f (next items)))))

;implementation (2.21)
(defn square-list [items]  ;recursive
  (if (nil? items)
    nil
    (cons (square (first items)) (square-list (next items)))))

(defn square-list-version2 [items]  ;recursive
  (map square items))

;implementation (2.22)
(defn square-list-wrongly-reversed [items]  ;iterative
  (letfn [(iter [things answer]
            (if (nil? things)
              answer
              (iter (next things)
                    (cons (square (first things)) answer))))]
    (iter items nil)))

;does not produce the expected flattened 'seq' structure:
(defn square-list-incorrectly-fixed [items]  ;iterative
  (letfn [(iter [things answer]
            (if (nil? things)
              answer
              (iter (next things)
                    (cons answer
                          (list (square (first things)))))))]
    (iter items nil)))

(defn square-list-iterative [items]  ;iterative (correctly fixed)
  (letfn [(iter [things answer]
            (if (nil? things)
              (reverse answer)
              (iter (next things)
                    (cons (square (first things))
                          answer))))]
    (iter items nil)))

;implementation (2.23)
(defn for-each [f items]
  (if (nil? items)
    true
    (do (f (first items))
        (for-each f (next items)))))
