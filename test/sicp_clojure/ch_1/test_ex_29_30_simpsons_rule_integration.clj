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


(ns sicp_clojure.ch_1.test_ex_29_30_simpsons_rule_integration
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_29_30_simpsons_rule_integration 
             :refer [cube integral-iterative integral-simpsons-rule 
                     square sum sum-iterative tolerance]]))


;//////  1.29 & 1.30  //////

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

;tests (1.29)
(deftest test1 (is (print-equal-up-to-abs-tolerance? 
                    (integral-iterative cube 0 1 1/100)
                    (integral-simpsons-rule cube 0 1 100))))

(deftest test2 (is (print-equal-up-to-abs-tolerance?  
                    (integral-iterative cube 0 1 1/1000)
                    (integral-simpsons-rule cube 0 1 1000))))

(deftest test3 (is (print-equal-up-to-abs-tolerance? 
                    (integral-iterative square 0 1 1/100) 
                    (integral-simpsons-rule square 0 1 100))))
                   
(deftest test4 (is (print-equal-up-to-abs-tolerance? 
                    (integral-iterative square 0 1 1/1000)
                    (integral-simpsons-rule square 0 1 1000))))

;tests (1.30)
(deftest test5 (is (print-equal? 
                    (sum identity 1 inc 100) 
                    (sum-iterative identity 1 inc 100))))

(defn test-ns-hook [] ;to be run in order
  (test1) (test2) (test3) (test4) (test5))

(run-tests)
