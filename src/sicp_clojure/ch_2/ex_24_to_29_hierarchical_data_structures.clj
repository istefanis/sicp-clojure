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


(ns sicp_clojure.ch_2.ex_24_to_29_hierarchical_data_structures)


;//////  2.24 - 2.29  //////

;utils (2.24)
(defn count-leaves [x]
  (cond (nil? x) 0
        (not (coll? x)) 1  ;works for: list, seq, vector etc.
        :else (+ (count-leaves (first x)) 
                 (count-leaves (next x)))))

;implementation (2.24)
;list, seq, and vector formats:
(def l1 (list 1 (list 2 (list 3 4))))
(def s1 (cons 1 (cons (cons 2 (cons (cons 3 (cons 4 nil)) nil)) nil)))
(def v1 (vector 1 (vector 2 (vector 3 4))))

;implementation (2.25)
(def l2 (list 1 3 (list 5 7) 9))  ;list format
(def v2 (vector 1 3 (vector 5 7) 9))  ;vector format
(def l2-selector (first (next (first (next (next l2))))))
(def v2-selector (first (next (first (next (next v2))))))

(def l3 (list (list 7)))
(def l3-selector (first (first l3)))

(def l4 (list 1 (list 2 (list 3 (list 4 (list 5 (list 6 7)))))))
(def l4-selector1 (first
                   (next
                    (first
                     (next
                      (first
                       (next
                        (first
                         (next (first (next (first (next l4)))))))))))))
(def l4-selector2 (second
                   (second
                    (second
                     (second (second (second l4)))))))

;utils (2.26)
(defn append-seq [items1 items2]
  (if (nil? items1)
    items2
    (cons (first items1) (append-seq (next items1) items2))))

;implementation (2.27)
(defn deep-reverse [items]  ;accepts list or vector input, and returns a seq
  (letfn [(last-item [items] (nth items (- (count items) 1)))
          (rec [last counter]
            (if (= counter 0)
              (cons last nil)
              (if (coll? (nth items (- counter 1)))
                (cons last (rec (deep-reverse (nth items (- counter 1)))
                                (- counter 1)))
                (cons last (rec (nth items (- counter 1)) (- counter 1))))))]

    (if (coll? (last-item items))
      (rec (deep-reverse (last-item items)) (- (count items) 1))
      (rec (last-item items) (- (count items) 1)))))

;implementation (2.28)
(defn fringe [items]  ;accepts list or vector input, and returns a seq
  (letfn [(rec [x acc counter]
            (cond (= (- (count x) counter) -1) acc
                  :else
                  (if (coll? (nth x (- (count x) counter)))
                    (rec x (rec (nth x (- (count x) counter)) acc 1) (+ counter 1))
                    (rec x (cons (nth x (- (count x) counter)) acc) (+ counter 1)))))]
    (rec items nil 1)))

;utils (2.29)
(defn make-mobile [left right] (list left right))
(defn make-branch [length structure] (list length structure))

;implementation (2.29a)
(defn left-branch [mobile] (first mobile))
(defn right-branch [mobile] (second mobile))
(defn branch-length [branch] (first branch))
(defn branch-structure [branch] (second branch))

;implementation (2.29b)
(defn total-weight [mobile]
  (+ (if (coll? (branch-structure (left-branch mobile)))
       (total-weight (branch-structure (left-branch mobile)))
       (branch-structure (left-branch mobile)))
     (if (coll? (branch-structure (right-branch mobile)))
       (total-weight (branch-structure (right-branch mobile)))
       (branch-structure (right-branch mobile)))))

;implementation (2.29c)
(defn balanced? [mobile]
  (cond (and (coll? (branch-structure (left-branch mobile)))
             (coll? (branch-structure (right-branch mobile))))
        (if (and (balanced? (branch-structure (left-branch mobile)))
                 (balanced? (branch-structure (right-branch mobile))))
          (= (* (total-weight (branch-structure (left-branch mobile)))
                (branch-length (left-branch mobile)))
             (* (total-weight (branch-structure (right-branch mobile)))
                (branch-length (right-branch mobile))))
          false)

        (coll? (branch-structure (left-branch mobile)))
        (if (balanced? (branch-structure (left-branch mobile)))
          (= (* (total-weight (branch-structure (left-branch mobile)))
                (branch-length (left-branch mobile)))
             (* (branch-structure (right-branch mobile))
                (branch-length (right-branch mobile))))
          false)

        (coll? (branch-structure (right-branch mobile)))
        (if (balanced? (branch-structure (right-branch mobile)))
          (= (* (branch-structure (left-branch mobile))
                (branch-length (left-branch mobile)))
             (* (total-weight (branch-structure (right-branch mobile)))
                (branch-length (right-branch mobile))))
          false)

        :else
        (= (* (branch-structure (left-branch mobile))
              (branch-length (left-branch mobile)))
           (* (branch-structure (right-branch mobile))
              (branch-length (right-branch mobile))))))

;explanation (2.29d)
;only the selectors must be changed if needed, 
;as the constructors and selectors form an abstraction barrier

;ex. in an implementation using vectors, not even the selectors have to be changed:
;(defn make-mobile [left right] [left right])
;(defn make-branch [length structure] [length structure])
