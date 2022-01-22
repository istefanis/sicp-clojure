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


(ns sicp_clojure.ch_2.test_ex_20_variadic_functions
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_2.ex_20_variadic_functions
             :refer [same-parity]]))


;//////  2.20  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

(deftest test1 (is (print-equal? (same-parity 1 0 1 2 3 4 5 6 7) 
                                 '(1 1 3 5 7))))
(deftest test2 (is (print-equal? (same-parity 2 0 1 2 3 4 5 6 7) 
                                 '(2 0 2 4 6))))

(defn test-ns-hook []  ;to be run in order
  (test1) (test2))

(run-tests)
