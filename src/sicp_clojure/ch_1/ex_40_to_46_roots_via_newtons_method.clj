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


(ns sicp_clojure.ch_1.ex_40_to_46_roots_via_newtons_method)


;//////  1.40 - 1.46  //////
;compute the square and cube roots of a number, 
;using Newton's numerical method for solving equations

;constants
(def tolerance 0.0001)  ;numerical analysis exercises included
(def dx 0.001)
(def first-guess 1.0)

;utils
(defn average [x y] (/ (+ x y) 2))
(defn square [x] (* x x))
(defn cube [x] (* x x x))

(defn fixed-point [f first-guess]
  (letfn [(close-enough? [a b] (< (Math/abs (- a b)) tolerance))
          (test [guess]
            (let [next (f guess)]
              (if (close-enough? guess next)
                next
                (if (= 0 next)
                  false
                  (test next)))))]
    (test first-guess)))

(defn average-damp [f] (fn [x] (average x (f x))))

(defn derivative [g]
  (fn [x] (/ (- (g (+ x dx)) (g x)) dx)))

(defn newton-transform [g]
  (fn [x] (- x (/ (g x) ((derivative g) x)))))

(defn newtons-method [g guess]
  (fixed-point (newton-transform g) guess))

(defn square-root [x]
  (newtons-method (fn [y] (- (square y) x)) first-guess))

;implementation (1.40)
(defn cubic-equation [a b c]
  (fn [x] (+ (cube x) (* a (square x)) (* b x) c)))

(defn cubic-equation-root [a b c guess]
  (newtons-method (cubic-equation a b c) guess))

;implementation (1.41)
(defn double-function-application [g] 
  (fn [x] (g (g x))))

;implementation (1.42)
(defn compose [f g]  ;function composition
  (fn [x] (f (g x))))

;implementation (1.43)
(defn repeated [f times]  ;repeated function application
  (letfn [(rep [g n]
            (cond (<= n 1) (fn [x] (g x))
                  (> n 1) (fn [x] ((rep (compose f g) (- n 1)) x))))]
    (fn [x] ((rep f times) x))))

;implementation (1.44)
(defn smooth [f]  ;smoothing a function
  (fn [x] (/ (+ (f (- x dx))
                (f x)
                (f (+ x dx))) 3)))

(defn n-fold-smooth [function times]  ;smoothing a function n-times
  (fn [x] (((repeated smooth times) function) x)))

;implementation (1.45)
(defn nth-root [x n]  ;approximating the n-th root of a number (x^(1/n))
  (fixed-point ((repeated average-damp (- n 2)) 
                (fn [y] (/ x (Math/pow y (- n 1)))))
               first-guess))

;implementation (1.46)
(defn iterative-improve [good-enough? improve-guess x]
  (fn [guess]
    (if (good-enough? x guess)
      (identity guess)
      ((iterative-improve good-enough? improve-guess x) 
       (improve-guess x guess)))))

(defn square-root-version2 [x]  ;using iterative-improve
  (letfn [(good-enough?-sqrt [x guess]
            (< (Math/abs (- (square guess) x)) tolerance))
          (improve-guess-sqrt [x guess]
            (average guess (/ x guess)))]
    ((iterative-improve good-enough?-sqrt improve-guess-sqrt x)
     first-guess)))

(defn fixed-point-version2 [f first-guess]  ;using iterative-improve
  (letfn [(good-enough?-fp [a b] (< (Math/abs (- b (a b))) tolerance))
          (improve-guess-fp [x guess] (x guess))]
    ((iterative-improve good-enough?-fp improve-guess-fp f)
     first-guess)))

(defn nth-root-version2 [x n]  ;using fixed-point-version2
  (fixed-point-version2 ((repeated average-damp (- n 2))
                         (fn [y] (/ x (Math/pow y (- n 1)))))
                        first-guess))
