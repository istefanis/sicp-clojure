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


(ns sicp_clojure.ch_1.test_ex_17_18_fast_logn_multiplication
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_17_18_fast_logn_multiplication 
             :refer [fast-mult-iterative fast-mult-recursive]]))


;//////  1.17 & 1.18  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

(def x1 (rand-int 100))
(def x2 (rand-int 100))
(def x3 (rand-int 100))
(def x4 (rand-int 100))

(deftest test1 (is (print-equal? 
                    (* x1 x2) (fast-mult-recursive x1 x2))))
(deftest test2 (is (print-equal? 
                    (* x1 x2) (fast-mult-iterative x1 x2))))
(deftest test3 (is (print-equal? 
                    (* x3 x4) (fast-mult-recursive x3 x4))))
(deftest test4 (is (print-equal? 
                    (* x3 x4) (fast-mult-iterative x3 x4))))

(defn test-ns-hook [] ;to be run in order
  (test1) (test2) (test3) (test4))

(run-tests)
