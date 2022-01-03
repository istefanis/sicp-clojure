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


(ns sicp_clojure.ch_1.test_ex_37_to_39_continued_fractions 
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_37_to_39_continued_fractions 
             :refer [e-cf e-cf-recursive phi phi-cf tan-cf tolerance]]))


;//////  1.37 - 1.39  //////

(defn print-equal-up-to-abs-tolerance? [x y]
  (let [abs-difference (Math/abs (- x y))
        result (if (< abs-difference tolerance) true false)]
    (println x y)
    (println abs-difference)
    (println result)
    result))

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

;tests (1.37)
(deftest test1 (is (print-equal-up-to-abs-tolerance? 
                    phi (phi-cf 11))))

;tests (1.38)
(deftest test2 (is (print-equal? 
                    (e-cf 7) (e-cf-recursive 7))))
(deftest test3 (is (print-equal-up-to-abs-tolerance? 
                    (Math/exp 1) (e-cf 7))))

;tests (1.39)
(deftest test4 (is (print-equal-up-to-abs-tolerance? 
                    (Math/tan 0.2) (tan-cf 0.2 6))))
(deftest test5 (is (print-equal-up-to-abs-tolerance? 
                    (Math/tan 1.3) (tan-cf 1.3 6))))
(deftest test6 (is (print-equal-up-to-abs-tolerance? 
                    (Math/tan 1.9) (tan-cf 1.9 6))))
(deftest test7 (is (print-equal-up-to-abs-tolerance? 
                    (Math/tan 2.2) (tan-cf 2.2 6))))

(defn test-ns-hook [] ;to be run in order
  (test1) (test2) (test3) (test4) (test5)
  (test6) (test7))

(run-tests)
