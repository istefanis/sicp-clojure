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


(ns sicp_clojure.ch_2.test_ex_17_to_19_elementary_collections
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_2.ex_17_to_19_elementary_collections
             :refer [collection-ref count-change-any-currency count-change-us 
                     flatten-vector last-item last-pair-seq last-pair-vector 
                     length length-iter reverse-seq reverse-seq-iterative 
                     reverse-vector reverse-vector-iterative uk-coins us-coins 
                     us-coins-vector]]))


;//////  2.17 - 2.19  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))  ;collection type-independent equality

(def item1 (rand-int 10))
(def item2 (rand-int 10))
(def item3 (rand-int 10))
(def item4 (rand-int 10))

(def list1 (list item1 item2 item3 item4))
(def vector1 (vector item1 item2 item3 item4))
(println list1 vector1)

;tests (utils)
(def n (rand-int 4))
(println n)

(deftest test1 (is (print-equal? (collection-ref list1 n) (nth list1 n))))
(deftest test2 (is (print-equal? (collection-ref vector1 n) (nth vector1 n))))

(deftest test3 (is (print-equal? (length list1) (count list1))))
(deftest test4 (is (print-equal? (length vector1) (count vector1))))

(deftest test5 (is (print-equal? (length list1) (length-iter list1))))
(deftest test6 (is (print-equal? (length vector1) (length-iter vector1))))

;tests (2.17)
(deftest test7 (is (print-equal? (last-pair-seq list1) (list item4))))
(deftest test8 (is (print-equal? (last-pair-vector vector1) (vector item4))))

(deftest test9 (is (print-equal? (last-item list1) item4)))
(deftest test10 (is (print-equal? (last-item vector1) item4)))

;tests (2.18)
(deftest test11 (is (print-equal? (reverse-seq list1) 
                                  (reverse list1))))
(deftest test12 (is (print-equal? (reverse-vector vector1)
                                  (flatten-vector (reverse vector1)))))

(deftest test13 (is (print-equal? (reverse-seq-iterative list1)
                                  (reverse-seq list1))))
(deftest test14 (is (print-equal? (reverse-vector-iterative vector1)
                                  (reverse-vector vector1))))

;tests (2.19)
(deftest test15 (is (print-equal? (count-change-us 100) 292)))
(deftest test16 (is (print-equal? (count-change-any-currency 100 us-coins) 
                                  (count-change-us 100))))
(deftest test17 (is (print-equal? (count-change-any-currency 100 us-coins-vector)
                                  (count-change-any-currency 100 us-coins))))
(deftest test18 (is (print-equal? (count-change-any-currency 10 uk-coins) 50)))


(defn test-ns-hook []  ;to be run in order
  (test1) (test2) (test3) (test4) (test5) 
  (test6) (test7) (test8) (test9) (test10)
  (test11) (test12) (test13) (test14) (test15)
  (test16) (test17) (test18))

(run-tests)
