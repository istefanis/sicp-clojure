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


(ns sicp_clojure.ch_1.test_ex_40_to_46_roots_via_newtons_method
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_40_to_46_roots_via_newtons_method 
             :refer [compose cube double-function-application nth-root 
                     nth-root-version2 repeated square square-root 
                     square-root-version2 tolerance]]))


;//////  1.40 - 1.46  //////

(defn print-equal-up-to-rel-tolerance? [x y]
  (let [rel-difference (/ (Math/abs (- x y)) x)
        result (if (< rel-difference tolerance) true false)]
    (println x y)
    (println rel-difference)
    (println result)
    result))

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

(def x1 (rand-int 100))
(def x2 (rand-int 10000))

;tests (1.40)
(deftest test1 (is (print-equal-up-to-rel-tolerance? 
                    x1 (square (square-root x1)))))
(deftest test2 (is (print-equal-up-to-rel-tolerance? 
                    x2 (square (square-root x2)))))

;tests (1.41 - 1.43)
(deftest test3 (is (print-equal? 
                    (cube (cube 2.0)) 
                    ((double-function-application cube) 2.0))))
(deftest test4 (is (print-equal? 
                    (inc (square 2.0)) 
                    ((compose inc square) 2.0))))
(deftest test5 (is (print-equal? 
                    (square (square (square (square 2.0))))
                    ((repeated square 4) 2.0))))

;tests (1.45)
(deftest test6 (is (print-equal-up-to-rel-tolerance? 
                    x1 (square (nth-root x1 2)))))
(deftest test7 (is (print-equal-up-to-rel-tolerance? 
                    x2 (square (nth-root x2 2)))))
(deftest test8 (is (print-equal-up-to-rel-tolerance? 
                    x1 (cube (nth-root x1 3)))))
(deftest test9 (is (print-equal-up-to-rel-tolerance? 
                    x2 (cube (nth-root x2 3)))))

;tests (1.46)
(deftest test10 (is (print-equal-up-to-rel-tolerance? 
                    x1 (square (square-root-version2 x1)))))
(deftest test11 (is (print-equal-up-to-rel-tolerance? 
                    x2 (square (square-root-version2 x2)))))
(deftest test12 (is (print-equal-up-to-rel-tolerance? 
                    x1 (cube (nth-root-version2 x1 3)))))
(deftest test13 (is (print-equal-up-to-rel-tolerance? 
                    x2 (cube (nth-root-version2 x2 3)))))

(defn test-ns-hook [] ;to be run in order
  (test1) (test2) (test3) (test4) (test5)
  (test6) (test7) (test8) (test9) (test10)
  (test11) (test12) (test13))

(run-tests)
