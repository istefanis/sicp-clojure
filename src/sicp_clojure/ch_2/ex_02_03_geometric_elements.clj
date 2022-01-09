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


(ns sicp_clojure.ch_2.ex_02_03_geometric_elements)


;//////  2.2 & 2.3  //////
;implement representations for points, line segments and plane rectangles

;utils
(defn average [x y] (/ (+ x y) 2))
(defn square [x] (* x x))

;implementation (2.2)
(defn make-point [x y] (vector x y))
(defn x-point [p] (first p))
(defn y-point [p] (second p))
(defn print-point [p] (println "(" (x-point p) "," (y-point p) ")"))

(defn distance [p1 p2]
  (Math/sqrt (+ (square (- (x-point p1) (x-point p2)))
                (square (- (y-point p1) (y-point p2))))))

(defn make-segment [p1 p2] (vector p1 p2))
(defn start-segment [s] (first s))
(defn end-segment [s] (second s))

(defn midpoint-segment [s]
  (make-point (average (x-point (start-segment s)) (x-point (end-segment s)))
              (average (y-point (start-segment s)) (y-point (end-segment s)))))

;implementation (2.3)

;representation in terms of points (their order is important)
(defn rectangle-version1 [p1 p2 p3 p4] (vector 1 p1 p2 p3 p4))

(defn rect-version1-p1 [r] (first (next r)))
(defn rect-version1-p2 [r] (first (next (next r))))
(defn rect-version1-p3 [r] (first (next (next (next r)))))
(defn rect-version1-p4 [r] (second (next (next (next r)))))

;alternative representation in terms of segments (their order is important)
(defn rectangle-version2 [s1 s2 s3 s4] (vector 2 s1 s2 s3 s4))

(defn rect-version2-p1 [r] (start-segment (first (next r))))
(defn rect-version2-p2 [r] (start-segment (first (next (next r)))))
(defn rect-version2-p3 [r] (start-segment (first (next (next (next r))))))
(defn rect-version2-p4 [r] (start-segment (second (next (next (next r))))))

(defn representation [r] (first r))

(defn rect-p1 [r] 
  (cond (= (representation r) 1) (rect-version1-p1 r)
        (= (representation r) 2) (rect-version2-p1 r)))
(defn rect-p2 [r]
  (cond (= (representation r) 1) (rect-version1-p2 r)
        (= (representation r) 2) (rect-version2-p2 r)))
(defn rect-p3 [r]
  (cond (= (representation r) 1) (rect-version1-p3 r)
        (= (representation r) 2) (rect-version2-p3 r)))
(defn rect-p4 [r]
  (cond (= (representation r) 1) (rect-version1-p4 r)
        (= (representation r) 2) (rect-version2-p4 r)))

(defn rect-perimeter [r]
  (let [p1 (rect-p1 r)
        p2 (rect-p2 r)
        p3 (rect-p3 r)]
    (* 2 (+ (distance p1 p2) (distance p2 p3)))))

(defn rect-area [r]
  (let [p1 (rect-p1 r)
        p2 (rect-p2 r)
        p3 (rect-p3 r)]
    (* (distance p1 p2) (distance p2 p3))))
