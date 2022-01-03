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


(ns sicp_clojure.ch_1.test_ex_31_pi_approximation_via_product   
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_31_pi_approximation_via_product 
             :refer [factorial pi-approx pi-approx-iterative 
                     pi-approx-loop-recur tolerance]]))


;//////  1.31  //////

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

(deftest test1 (is (print-equal? (factorial 5) 120)))

(deftest test2 (is (print-equal-up-to-abs-tolerance?
                    (pi-approx 1000) (pi-approx-iterative 1000))))
  
(deftest test3 (is (print-equal-up-to-abs-tolerance?
                    Math/PI (pi-approx-loop-recur 20000))))
;an execution of (pi-approx 10000) or (pi-approx-iterative 20000) 
;will fail due to StackOverflow

(defn test-ns-hook [] ;to be run in order
  (test1) (test2) (test3))

(run-tests)
