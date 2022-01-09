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


(ns sicp_clojure.ch_2.test_ex_02_03_geometric_elements
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_2.ex_02_03_geometric_elements
             :refer [make-point make-segment midpoint-segment x-point y-point 
                     rect-area rect-perimeter rectangle-version1 rectangle-version2]]))


;//////  2.2 & 2.3  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

(def point1 (make-point 0 0))
(def point2 (make-point 0 4))
(def point3 (make-point 5 4))
(def point4 (make-point 5 0))

(def seg1 (make-segment point1 point2))
(def seg2 (make-segment point2 point3))
(def seg3 (make-segment point3 point4))
(def seg4 (make-segment point4 point1))
(def midpoint1 (midpoint-segment seg1))

(def rect1 (rectangle-version1 point1 point2 point3 point4))
(def rect2 (rectangle-version2 seg1 seg2 seg3 seg4))

(def per1 (rect-perimeter rect1))
(def per2 (rect-perimeter rect2))
(def area1 (rect-area rect1))
(def area2 (rect-area rect2))

;tests (2.2)
(deftest test1 (is (print-equal? (x-point midpoint1) 0)))
(deftest test2 (is (print-equal? (y-point midpoint1) 2)))

;tests (2.3)
(deftest test3 (is (print-equal? per1 per2)))
(deftest test4 (is (print-equal? area1 area2)))

(defn test-ns-hook []  ;to be run in order
  (test1) (test2) (test3) (test4))

(run-tests)
