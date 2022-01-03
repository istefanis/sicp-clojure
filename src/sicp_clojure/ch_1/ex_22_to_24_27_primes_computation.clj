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


(ns sicp_clojure.ch_1.ex_22_to_24_27_primes_computation)


;//////  1.22 - 1.23  //////
;implement a simple and a more optimized procedure 
;for computing the primes in a range

;utils
(defn square [x] (* x x))
(defn divides? [a b] (= (mod b a) 0))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n  ;square root step growth
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n] (find-divisor n 2))
(defn prime? [n] (= n (smallest-divisor n)))

(defn print-prime? [n]
  (if (prime? n)
    (do (print n " ")
        true)
    false))

;implementation (1.22)
(defn search-for-primes [prime-test-method max]
  ;passing also the prime test method as a function argument
  (letfn [(iter [n primes-found]
            (if (<= n max)
              (if (prime-test-method n)
                (if (<= n 2)  ;prime case
                  (iter (+ n 1) (+ primes-found 1))
                  (iter (+ n 2) (+ primes-found 1)))
                (if (<= n 2)  ;not prime case
                  (iter (+ n 1) primes-found)
                  (iter (+ n 2) primes-found)))
              (do (println "range: 0 -" max "| primes: " primes-found)
                  primes-found)))]
    (time (iter 1 0))))

;implementation (1.23)
(defn find-divisor-version2 [n test-divisor]  ;optimized
  (letfn [(next [input]
            (if (= input 2) 3 (+ input 2)))]
    (cond (> (square test-divisor) n) n
          (divides? test-divisor n) test-divisor
          :else (find-divisor-version2 n (next test-divisor)))))

(defn smallest-divisor-version2 [n] (find-divisor-version2 n 2))
(defn prime?-version2 [n] (= n (smallest-divisor-version2 n)))


;//////  1.24  //////
;implement a procedure using Fermat's primality test 
;for computing prime candidates in a range

;constants
(def max-times 100)

;utils
(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (square (expmod base (/ exp 2) m)) m)
        :else (mod (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (if (<= n 2)
    true
    (letfn [(try-it [a]
              (= (expmod a n n) a))]
      (try-it (+ 1 (rand-int (- n 1)))))))

;implementation (1.24)
(defn prime?-fermat [n times]
  (cond (= times 0) true
        (fermat-test n) (prime?-fermat n (- times 1))
        :else false))

(defn prime-candidate? [n] (prime?-fermat n max-times))


;//////  1.27  //////
;the Fermat test computes some prime candidates, 
;which are not in fact primes (Carmichael numbers) 
;ex. for max=5000, it computes 5 such (see tests)
