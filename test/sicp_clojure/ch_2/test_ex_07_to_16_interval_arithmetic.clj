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


(ns sicp_clojure.ch_2.test_ex_07_to_16_interval_arithmetic
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_2.ex_07_to_16_interval_arithmetic
             :refer [add-interval div-interval equal-interval?
                     example1 example2 example3 example4 lower-bound
                     make-center-percent make-interval mul-interval
                     mul-interval-version2 par1 par2 percent print-interval
                     sub-interval tolerance upper-bound width]]))


;//////  2.7 - 2.16  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

(defn print-greater-than? [x y]
  (println x y)
  (println (> x y))
  (> x y))

(defn print-equal-up-to-abs-tolerance? [x y]
  (let [abs-difference (Math/abs (- x y))
        result (if (< abs-difference tolerance) true false)]
    (println x y)
    (println abs-difference)
    (println result)
    result))

(defn print-equal-interval? [i1 i2]
  (run! print-interval [i1 i2])
  (println (equal-interval? i1 i2))
  (equal-interval? i1 i2))

;tests (2.7)
(def i1 (make-interval 7 3))
(def i2 (make-interval 5 6))

(deftest test1 (is (print-equal? (lower-bound i1) 3)))
(deftest test2 (is (print-equal? (upper-bound i1) 7)))

;tests (2.8)
(def sub-interval12 (sub-interval i1 i2))
(deftest test3 (is (print-equal? (lower-bound sub-interval12) -3)))  ;3-6=-3
(deftest test4 (is (print-equal? (upper-bound sub-interval12) 2)))   ;7-5=2

;tests (2.9)
(defn inc-if-equal [x y] (if (= x y) (+ y 1) y))

(def a3 (- (rand-int 10) 5))  ;negative or zero or positive
(def b3 (inc-if-equal a3 (- (rand-int 10) 5)))  ;a3 <> b3
(def a4 (- (rand-int 10) 5))
(def b4 (inc-if-equal a4 (- (rand-int 10) 5)))
(def i3 (make-interval a3 b3))
(def i4 (make-interval a4 b4))
(def add-interval34 (add-interval i3 i4))
(def sub-interval34 (sub-interval i3 i4))

(deftest test5 (is (print-equal? (+ (width i3) (width i4))
                                 (width add-interval34))))
(deftest test6 (is (print-equal? (+ (width i3) (width i4))
                                 (width sub-interval34))))

;tests (2.10)
(defn inc-if-zero [x] (if (= x 0) (+ x 1) x))

(def a5 (inc-if-zero (- (rand-int 10) 5)))  ;non-zero
(def i5 (make-interval a5 a5))

(deftest test7 (is (thrown? ArithmeticException (div-interval i1 i5))))

;tests (2.11)
(deftest test8 (is (print-equal-interval? (mul-interval i3 i4)
                                          (mul-interval-version2 i3 i4))))

;tests (2.12)
(deftest test9 (is (print-equal-interval? (make-center-percent 2 10)
                                          (make-interval 1.8M 2.2M))))
(deftest test10 (is (print-equal? (percent (make-center-percent 2 10))
                                  10.0M)))

;tests (2.14 - 2.16)
(def a6 (+ (rand-int 10) 1))  ;positive
(def b6 (+ (rand-int 10) 11))  ;a6 < b6
(def a7 (+ (rand-int 10) 1))
(def b7 (+ (rand-int 10) 11))
(def i6 (make-interval a6 b6))
(def i7 (make-interval a7 b7))

(deftest test11 (is (not (print-equal-interval? (par1 i6 i7) (par2 i6 i7)))))
(deftest test12 (is (print-greater-than? (width (par1 i6 i7)) 
                                         (width (par2 i6 i7)))))

; examples 1-4 
; (always testing with the most diverse interval bounds possible, 
; although any single example suffices to prove non-equivalence):
(deftest test13 (is (not (print-equal-interval? (example1 i3 i4) i3))))
(deftest test14 (is (not (print-equal-interval? (example2 i6 i7) i6))))
(deftest test15 (is (not (print-equal-interval? (example3 i3) 
                                                (make-interval 0 0)))))

; algebraic equivalence 'holds better' (the 'dependency problem' attenuates) 
; when operations involve intervals with smaller widths
(deftest test16 (is (not (print-equal-up-to-abs-tolerance?
                          (width (sub-interval
                                  (example4 (make-center-percent 1 0.1))
                                  (make-interval 1 1)))
                          0))))
(deftest test17 (is (print-equal-up-to-abs-tolerance?
                     (width (sub-interval 
                             (example4 (make-center-percent 1 0.00001))
                             (make-interval 1 1)))
                     0)))


;run tests
(defn test-ns-hook []  ;to be run in order
  (test1) (test2) (test3) (test4) (test5)
  (test6) (test7) (test8) (test9) (test10)
  (test11) (test12) (test13) (test14) (test15)
  (test16) (test17))

(run-tests)
