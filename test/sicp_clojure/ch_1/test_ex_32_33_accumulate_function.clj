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


(ns sicp_clojure.ch_1.test_ex_32_33_accumulate_function 
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_32_33_accumulate_function 
             :refer [accumulate accumulate-iterative accumulate-version2 
                     filtered-accumulate inc-by-2 product square sum]]))


;//////  1.32 & 1.33  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

;tests (1.32 - accumulate)
(deftest test1 (is (print-equal?
                    (sum square 1N inc 100)
                    (accumulate + 0 square 1N inc 100))))
(deftest test2 (is (print-equal?
                    (product square 1N inc 10)
                    (accumulate * 1 square 1N inc 10))))

;tests (1.32 - other accumulate versions)
(deftest test3 (is (print-equal?
                    (accumulate + 0 square 1N inc 100)
                    (accumulate-version2 + 0 square 1N inc 100))))
(deftest test4 (is (print-equal?
                    (accumulate + 0 square 1N inc 100)
                    (accumulate-iterative + 0 square 1N inc 100))))

;tests (1.33)
(defn no-filter [x] x true)
(deftest test5 (is (print-equal?
                    (accumulate + 0 square 1N inc 100)
                    (filtered-accumulate + 0 square 1N inc 100 no-filter))))
(deftest test6 (is (print-equal?
                    (accumulate + 0 square 1N inc-by-2 50)
                    (filtered-accumulate + 0 square 1N inc 50 odd?))))

(defn test-ns-hook [] ;to be run in order
  (test1) (test2) (test3) (test4) (test5)
  (test6))

(run-tests)
