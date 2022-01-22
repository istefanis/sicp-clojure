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


(ns sicp_clojure.ch_2.ex_17_to_19_elementary_collections)


;//////  2.17 & 2.18  //////
;implement functions for performing non-mutational operations on some
;elementary collections/data structures 

;in Clojure: 
; - instead of: {car, cdr, cadr} of other Lisp dialects, 
;   we use: {first, next, second} for selecting collection items
; - instead of a 'cons' of two numbers 
;   (which cannot be defined), we must use a collection: 
;   ex. a 'seq' (consisting of chained 'cons'), or a 'vector'
; - a 'list' is a 'seq', but not always the inverse
; - '=' checks for collection type-independent equality

;below, generic implementations accepting either 'seq' or 'vector' inputs 
;are provided when that is possible, or different implementations for
;the two types of input are provided when that is not

;utils (2.17 & 2.18)
(defn flatten-vector [items]
  (into []  ;transform from flattened sequence to vector
        (flatten items)))  ;transform from nested collection to flattened sequence

(defn collection-ref [items n]  ;like the 'nth' built-in function
  (if (= n 0)
    (first items)
    (collection-ref (next items) (- n 1))))

(defn length [items]  ;like the 'count' built-in function
  (if (nil? items)
    0
    (+ 1 (length (next items)))))

(defn length-iter [items]
  (letfn [(iter [items counter]
    (if (nil? items)
      counter
      (iter (next items) (+ 1 counter))))]
  (iter items 0)))

(defn append-seq [items1 items2]
  (if (nil? items1)
    items2
    (cons (first items1) (append-seq (next items1) items2))))

(defn append-vector [items1 items2]
  (if (nil? items1)
    items2
    (flatten-vector (vector (first items1) (append-vector (next items1) items2)))))

;implementation (2.17)
(defn last-pair-seq [items]
  (list (collection-ref items (- (length items) 1))))

(defn last-pair-vector [items]
  (vector (collection-ref items (- (length items) 1))))

(defn last-item [items]
  (collection-ref items (- (length items) 1)))

;implementation (2.18)
(defn reverse-seq [items]
  (letfn [(rec [last counter]
            (if (= counter 0)
              (cons last nil)
              (cons last (rec (collection-ref items (- counter 1))
                              (- counter 1)))))]
    (rec (last-item items) (- (length items) 1))))

(defn reverse-seq-iterative [items]
  (letfn [(iter [acc counter]
            (cond (= counter 1) (iter (list (second items) acc) (+ counter 1))
                  (= counter (- (length items) 1))
                  (cons (collection-ref items counter) acc)
                  :else
                  (iter (cons (collection-ref items counter) acc) (+ counter 1))))]
    (iter (first items) 1)))

(defn reverse-vector [items]
  (letfn [(rec [last counter]
            (if (= counter 0)
              last
              (vector last (rec (collection-ref items (- counter 1))
                                (- counter 1)))))]
    (flatten-vector (rec (last-item items) (- (length items) 1)))))

(defn reverse-vector-iterative [items]
  (letfn [(iter [acc counter]
            (cond (= counter 1) (iter (vector (second items) acc) (+ counter 1))
                  (= counter (- (length items) 1))
                  (vector (collection-ref items counter) acc)
                  :else
                  (iter (vector (collection-ref items counter) acc) (+ counter 1))))]
    (flatten-vector (iter (first items) 1))))


;//////  2.19  //////
;count change alternatives computation

;utils (2.19)
(defn cc [amount kinds-of-coins]
  (letfn [(first-denomination [kinds-of-coins]
            (cond (= kinds-of-coins 1) 1  ;us-coins specific
                  (= kinds-of-coins 2) 5
                  (= kinds-of-coins 3) 10
                  (= kinds-of-coins 4) 25
                  (= kinds-of-coins 5) 50))]
    (cond (= amount 0) 1
          (or (< amount 0) (= kinds-of-coins 0)) 0
          :else
          (+ (cc amount (- kinds-of-coins 1))
             (cc (- amount (first-denomination kinds-of-coins)) kinds-of-coins)))))

;implementation (2.19)
(defn cc2 [amount coin-values]
  (letfn [(no-more? [coin-values] (nil? coin-values))
          (first-denomination [coin-values] (first coin-values))
          (except-first-denomination [coin-values] (next coin-values))]
    (cond (== amount 0.0) 1  ;must use '==' and 0.0 here, to handle decimals
          (or (< amount 0.0) (no-more? coin-values)) 0
          :else
          (+ (cc2 amount (except-first-denomination coin-values))
             (cc2 (- amount (first-denomination coin-values)) coin-values)))))

(defn count-change-us [amount] (cc amount 5))
(defn count-change-any-currency [amount coin-values] (cc2 amount coin-values))

(def us-coins (list 50 25 10 5 1))
(def uk-coins (list 100 50 20 10 5 2 1 0.5))

(def us-coins-vector [50 25 10 5 1])
(def uk-coins-vector [100 50 20 10 5 2 1 0.5])
