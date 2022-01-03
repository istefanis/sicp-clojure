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


(ns sicp_clojure.ch_1.test_ex_22_to_24_27_primes_computation
  (:require [clojure.test :refer [deftest is run-tests]]
            [sicp_clojure.ch_1.ex_22_to_24_27_primes_computation 
             :refer [prime-candidate? prime? prime?-version2 
                     print-prime? search-for-primes]]))


;//////  1.22 - 1.24 & 1.27  //////

(defn print-equal? [x y]
  (println x y)
  (println (= x y))
  (= x y))

(defn print-not-equal? [x y]
  (println x y)
  (println (not (= x y)))
  (not (= x y)))

(search-for-primes print-prime? 50)

;tests (1.22 - 1.24)
(deftest test1 (is (print-equal? 
                    16 (search-for-primes prime? 50))))
(deftest test2 (is (print-equal? 
                    16 (search-for-primes prime?-version2 50))))
(deftest test3 (is (print-equal? 
                    16 (search-for-primes prime-candidate? 50))))

(deftest test4 (is (print-equal? 
                    670 (search-for-primes prime? 5000))))
(deftest test5 (is (print-equal? 
                    670 (search-for-primes prime?-version2 5000))))
(deftest test6 (is (print-equal? 
                    675 (search-for-primes prime-candidate? 5000))))

;tests (1.24)
(deftest test7 (is (print-not-equal? 
                    (prime? 561) (prime-candidate? 561))))
(deftest test8 (is (print-not-equal? 
                    (prime? 1105) (prime-candidate? 1105))))
(deftest test9 (is (print-not-equal? 
                    (prime? 1729) (prime-candidate? 1729))))
(deftest test10 (is (print-not-equal? 
                    (prime? 2465) (prime-candidate? 2465))))
(deftest test11 (is (print-not-equal? 
                    (prime? 2821) (prime-candidate? 2821))))

(defn test-ns-hook [] ;to be run in order
  (test1) (test2) (test3) (test4) (test5) 
  (test6) (test7) (test8) (test9) (test10)
  (test11))

(run-tests)
