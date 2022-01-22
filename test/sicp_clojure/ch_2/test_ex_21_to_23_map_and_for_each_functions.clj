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


(ns sicp_clojure.ch_2.test_ex_21_to_23_map_and_for_each_functions
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_2.ex_21_to_23_map_and_for_each_functions
             :refer [cube map-version2 square square-list 
                     square-list-incorrectly-fixed square-list-iterative 
                     square-list-version2 square-list-wrongly-reversed]]))


;//////  2.21 - 2.23  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))  ;collection type-independent equality

(def x1 (rand-int 50))
(def x2 (rand-int 50))
(def x3 (rand-int 50))
(def list1 (list x1 x2 x3))

;tests (2.21)
(deftest test1 (is (print-equal? (map-version2 square list1) 
                                 (list (square x1) (square x2) (square x3)))))
(deftest test2 (is (print-equal? (map-version2 cube list1)
                                 (list (cube x1) (cube x2) (cube x3)))))
(deftest test3 (is (print-equal? (map-version2 square list1)
                                 (map square list1))))
(deftest test4 (is (print-equal? (square-list list1)
                                 (map square list1))))
(deftest test5 (is (print-equal? (square-list-version2 list1)
                                 (square-list list1))))

;tests (2.22)
(deftest test6 (is (print-equal? (reverse (square-list-wrongly-reversed list1))
                                 (square-list list1))))
(deftest test7 (is (print-equal? (next (flatten 
                                        (square-list-incorrectly-fixed list1)))
                                 (square-list list1))))
(deftest test8 (is (print-equal? (square-list-iterative list1)
                                 (square-list list1))))


(defn test-ns-hook []  ;to be run in order
  (test1) (test2) (test3) (test4) (test5)
  (test6) (test7) (test8))

(run-tests)
