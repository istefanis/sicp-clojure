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


(ns sicp_clojure.ch_1.ex_17_18_fast_logn_multiplication)


;//////  1.17 & 1.18  //////
;implement a log(b) multiplication procedure,
;using either a recursive or iterative process

;utils
(defn halve [x] (* 1/2 x))
(defn double-number [x] (* x 2))

;implementation (1.17)
(defn fast-mult-recursive [a b]  ;logarithmic (in b) step growth
  (if (= b 0)
    0
    (if (even? b)
      (fast-mult-recursive (double-number a) (halve b))
      (+ a (fast-mult-recursive a (- b 1))))))

;implementation (1.18)
(defn fast-mult-iterative [a b]  ;logarithmic (in b) step growth
  (letfn [(iter [a b acc]
            (cond
              (= b 0) acc
              (even? b) (iter (double-number a) (halve b) acc)
              :else (iter a (- b 1) (+ a acc))))]
    (iter a b 0)))
