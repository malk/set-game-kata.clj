(ns set-game-kata.clj
  (:require [clojure.math.combinatorics :refer [combinations]]))

(def quantity? #{1 2 3})
(def shading? #{:solid :stripped :outlined})
(def color? #{:red :purple :green})
(def shape? #{:oval :squiggle :diamond})

(def card?
  (set
   (for [quantity quantity?
         shading shading?
         color color?
         shape shape?]
     [quantity shading color shape])))

(defn set-of-features?
  "true when all given features are identical or when they all differ"
  [features]
  (or
   (apply = features)
   (apply distinct? features)))

(defn set-of-cards?
  "true when all given cards have only features that pass set-of-features?"
  [cards]
  (every? set-of-features? (apply (partial map list) cards)))

(defn set-game?
  "true when any 3 of the given cards form a set-of-cards"
  [cards]
  (some set-of-cards? (combinations cards 3)))

(defn game-hand?
  "true when given hand is composed of 12 cards"
  [hand]
  (and
   (= 12 (count hand))
   (every? card? hand)))

(defn deal
  "generates a game hand"
  []
  (take 12 (shuffle card?)))
