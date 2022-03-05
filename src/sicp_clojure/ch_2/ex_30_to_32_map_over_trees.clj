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


(ns sicp_clojure.ch_2.ex_30_to_32_map_over_trees)


;//////  2.30 - 2.32  //////
;using map as an abstraction over tree operations

;utils
(defn square [x] (* x x))

(defn append-seq [items1 items2]  ;recursive
  (if (nil? items1)
    items2
    (cons (first items1) (append-seq (next items1) items2))))

(defn scale-tree-seq [tree factor]  ;recursive
  (cond (nil? tree) nil
        (not (coll? tree)) (* tree factor)
        :else (cons (scale-tree-seq (first tree) factor)
                    (scale-tree-seq (next tree) factor))))

(defn scale-tree-seq-version2 [tree factor]  ;recursive
  (map (fn [sub-tree]
         (if (coll? sub-tree)
           (scale-tree-seq-version2 sub-tree factor)
           (* sub-tree factor)))
       tree))

;implementation (2.30)
(defn square-tree-seq [tree]  ;recursive
  (cond (nil? tree) nil
        (not (coll? tree)) (square tree)
        :else (cons (square-tree-seq (first tree))
                    (square-tree-seq (next tree)))))

(defn square-tree-seq-version2 [tree]  ;recursive
  (map (fn [sub-tree]
         (if (coll? sub-tree)
           (square-tree-seq-version2 sub-tree)
           (square sub-tree)))
       tree))

;implementation (2.31)
(defn tree-map [proc items]  ;recursive
  (cond (nil? items) nil
        (not (coll? items)) (proc items)
        :else (cons (tree-map proc (first items))
                    (tree-map proc (next items)))))

(defn square-tree-seq-version3 [tree]  ;recursive
  (tree-map square tree))

;implementation (2.32)
(defn subsets [s]  ;recursive
  (if (nil? s)
    (list nil)
    (let [rest (subsets (next s))]
      (append-seq rest
                  (map (fn [x] (cons (first s) x)) rest)))))
