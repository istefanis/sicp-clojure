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


(ns sicp_clojure.ch_2.test_ex_24_to_29_hierarchical_data_structures
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_2.ex_24_to_29_hierarchical_data_structures
             :refer [append-seq balanced? count-leaves deep-reverse fringe l1 l2 
                     l2-selector l3 l3-selector l4 l4-selector1 l4-selector2 
                     make-branch make-mobile s1 total-weight v1 v2 v2-selector]]))


;//////  2.24 - 2.29  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))  ;collection type-independent equality

;tests (2.24)
(println l1)
(println s1)
(println v1)
(newline)

(deftest test1 (is (print-equal? (list (count l1) (count-leaves l1)) '(2 4))))
(deftest test2 (is (print-equal? (list (count s1) (count-leaves s1)) '(2 4))))
(deftest test3 (is (print-equal? (list (count v1) (count-leaves v1)) '(2 4))))

;tests (2.25)
(println l2)
(println v2)
(println l3)
(println l4)
(newline)

(deftest test4 (is (print-equal? (list l2-selector v2-selector) '(7 7))))
(deftest test5 (is (print-equal? l3-selector 7)))
(deftest test6 (is (print-equal? (list l4-selector1 l4-selector2) '(7 7))))

;tests (2.26)
(def x (list 1 2 3))
(def y (list 4 5 6))
(println (append-seq x y))
(println (cons x y))
(println (list x y))

;tests (2.27)
(def l5 (list (list 1 2) (list 3 4)))
(def l5-reversed (list (list 4 3) (list 2 1)))
(def v5 [[1 2] [3 4]])
(def l6 (list (list 1 (list 2 5)) (list 3 4) (list (list 7 9) 8)))
(def l6-reversed (list (list 8 (list 9 7)) (list 4 3) (list (list 5 2) 1)))

(deftest test7 (is (print-equal? (deep-reverse l5) l5-reversed)))
(deftest test8 (is (print-equal? (deep-reverse v5) l5-reversed)))
(deftest test9 (is (print-equal? (deep-reverse l6) l6-reversed)))

;tests (2.28)
(def l5-fringed (list 1 2 3 4))
(def l5-x2-fringed (list 1 2 3 4 1 2 3 4))

(deftest test10 (is (print-equal? (fringe l5) l5-fringed)))
(deftest test11 (is (print-equal? (fringe (list l5 l5)) l5-x2-fringed)))
(deftest test12 (is (print-equal? (fringe (vector v5 v5)) l5-x2-fringed)))

;tests (2.29)
(def m1 (make-mobile (make-branch 1 2)
                     (make-branch 1 2)))

(def m2 (make-mobile (make-branch 1 1)
                     (make-branch 1 (make-mobile (make-branch 1 1)
                                                 (make-branch 1 1)))))

(def m3 (make-mobile (make-branch 1 (make-mobile (make-branch 1 1)
                                                 (make-branch 1 1)))
                     (make-branch 1 (make-mobile (make-branch 1 1)
                                                 (make-branch 1 1)))))

(def m4 (make-mobile (make-branch 1 (make-mobile (make-branch 4 3)
                                                 (make-branch 4 3)))
                     (make-branch 2 (make-mobile (make-branch 1 2)
                                                 (make-branch 2 1)))))

(deftest test13 (is (print-equal? (total-weight m1) 4)))
(deftest test14 (is (print-equal? (total-weight m2) 3)))
(deftest test15 (is (print-equal? (total-weight m3) 4)))
(deftest test16 (is (print-equal? (total-weight m4) 9)))

(deftest test17 (is (balanced? m1)))
(deftest test18 (is (not (balanced? m2))))
(deftest test19 (is (balanced? m3)))
(deftest test20 (is (balanced? m4)))

;run tests
(defn test-ns-hook []  ;to be run in order
  (test1) (test2) (test3) (test4) (test5)
  (test6) (test7) (test8) (test9) (test10)
  (test11) (test12) (test13) (test14) (test15)
  (test16) (test17) (test18) (test19) (test20))

(run-tests)
