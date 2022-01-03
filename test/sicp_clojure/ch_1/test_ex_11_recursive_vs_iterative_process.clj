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


(ns sicp_clojure.ch_1.test_ex_11_recursive_vs_iterative_process
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_11_recursive_vs_iterative_process 
             :refer [f-iterative f-recursive]]))


;//////  1.11  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

(def x1 (rand-int 10))
(def x2 (rand-int 30))

(deftest test1 (is (print-equal? (f-recursive x1) (f-iterative x1))))
(deftest test2 (is (print-equal? (f-recursive x2) (f-iterative x2))))

(defn test-ns-hook [] ;to be run in order
  (test1) (test2))

(run-tests)
