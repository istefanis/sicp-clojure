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


(ns sicp_clojure.ch_2.test_ex_30_to_32_map_over_trees
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_2.ex_30_to_32_map_over_trees
             :refer [scale-tree-seq scale-tree-seq-version2 
                     square-tree-seq square-tree-seq-version2 
                     square-tree-seq-version3 subsets]]))


;//////  2.30 - 2.32  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))  ;collection type-independent equality

(def l1 '(1 (2 (3 4) 5) (6 7)))
(def v1 [1 [2 [3 4] 5] [6 7]])

;tests (utils)
(deftest test1 (is (print-equal? (scale-tree-seq l1 10)
                                 '(10 (20 (30 40) 50) (60 70)))))

(deftest test2 (is (print-equal? (scale-tree-seq v1 10)
                                 (scale-tree-seq-version2 v1 10))))

;tests (2.30)
(deftest test3 (is (print-equal? (square-tree-seq l1)
                                 '(1 (4 (9 16) 25) (36 49)))))

(deftest test4 (is (print-equal? (square-tree-seq v1)
                                 (square-tree-seq-version2 v1))))

;tests (2.31)
(deftest test5 (is (print-equal? (square-tree-seq-version2 l1)
                                 (square-tree-seq-version3 l1))))

(deftest test6 (is (print-equal? (square-tree-seq-version2 v1)
                                 (square-tree-seq-version3 v1))))

;tests (2.32)
(deftest test7 (is (print-equal? (subsets '(1 2 3))
                                 '(nil (3) (2) (2 3) (1) (1 3) (1 2) (1 2 3)))))

(defn test-ns-hook []  ;to be run in order
  (test1) (test2) (test3) (test4) (test5) 
  (test6) (test7))

(run-tests)
