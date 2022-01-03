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


(ns sicp_clojure.ch_1.test_ex_12_pascals_triangle
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_12_pascals_triangle 
             :refer [compute-element print-pascals-triangle]]))


;//////  1.12  //////

(print-pascals-triangle 10)

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

(deftest test1 (is (print-equal? 35 (compute-element 4 8))))
(deftest test2 (is (print-equal? 9 (compute-element 2 10))))
(deftest test3 (is (print-equal? 126 (compute-element 5 10))))

(defn test-ns-hook [] ;to be run in order
  (test1) (test2) (test3))

(run-tests)
