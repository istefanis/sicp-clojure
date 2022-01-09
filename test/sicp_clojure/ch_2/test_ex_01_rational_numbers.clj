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


(ns sicp_clojure.ch_2.test_ex_01_rational_numbers
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_2.ex_01_rational_numbers 
             :refer [add-rat make-rat-version1 make-rat-version2 
                     make-rat-version3 make-rat-version4 make-rat-version5 
                     print-rat equal-rat?]]))


;//////  2.1  //////

(def rat1 (make-rat-version1 -3 -5))
(def rat2 (make-rat-version1 1 -5))
(def rat3 (make-rat-version2 1 -5))  ;normalizes sign
(def rat4 (add-rat rat1 rat2))
(def rat5 (add-rat rat1 rat3))
(run! print-rat [rat1 rat2 rat3 rat4 rat5])
(newline)

(def rat6 (make-rat-version3 -3 -5))  ;normalizes sign
(def rat7 (make-rat-version4 1 -5))  ;normalizes sign
(def rat8 (make-rat-version5 1 -5))  ;normalizes sign
(def rat9 (add-rat rat6 rat7))
(def rat10 (add-rat rat6 rat8))
(run! print-rat [rat6 rat7 rat8 rat9 rat10])

(deftest test1 (is (equal-rat? rat1 rat6)))
(deftest test2 (is (equal-rat? rat2 rat7)))
(deftest test3 (is (equal-rat? rat3 rat8)))
(deftest test4 (is (equal-rat? rat4 rat9)))
(deftest test5 (is (equal-rat? rat5 rat10)))

(defn test-ns-hook []  ;to be run in order
  (test1) (test2) (test3) (test4) (test5))

(run-tests)
