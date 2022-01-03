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


(ns sicp_clojure.ch_1.test_ex_07_08_square_and_cube_roots
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_07_08_square_and_cube_roots 
             :refer [cube cube-root square square-root 
                     square-root-relative tolerance]]))


;//////  1.7 & 1.8  //////

(defn print-equal-up-to-rel-tolerance? [x y] 
  (let [rel-difference (/ (Math/abs (- x y)) x)
        result (if (< rel-difference tolerance) true false)]
    (println x y)
    (println rel-difference)
    (println result)
    result))

(def x1 (rand-int 100))
(def x2 (rand-int 10000))

;tests (1.7)
(deftest test1 (is (print-equal-up-to-rel-tolerance?
                    x1 (square (square-root x1)))))
(deftest test2 (is (print-equal-up-to-rel-tolerance?
                    x2 (square (square-root x2)))))

(deftest test3 (is (print-equal-up-to-rel-tolerance?
                    x1 (square (square-root-relative x1)))))
(deftest test4 (is (print-equal-up-to-rel-tolerance?
                    x2 (square (square-root-relative x2)))))

;tests (1.8)
(deftest test5 (is (print-equal-up-to-rel-tolerance?
                    x1 (cube (cube-root x1)))))
(deftest test6 (is (print-equal-up-to-rel-tolerance?
                    x2 (cube (cube-root x2)))))

(defn test-ns-hook [] ;to be run in order
  (test1) (test2) (test3) (test4) (test5)
  (test6))

(run-tests)
