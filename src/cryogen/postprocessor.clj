(ns cryogen.postprocessor
  (:require [com.rpl.specter :as s]
            [lisb.core :as lc])
  (:use [lisb.translation.util]))
  
(defn repl [form]
  #_(println form)
  (str "lisb=> " (str form) "   ;; B: " (try (lisb.translation.util/lisb->b form) (catch Exception e "lol, broken")) \newline
       (str (try (let [res (lc/eval-ir-formula (lisb->ir form))]
                   (case res
                     {} "{} ;; TRUE, no bindings"
                     nil "nil ;; FALSE"
                     res))
                 (catch Exception e "lol, broken"))) \newline))

(defn get-forms [content-field]
  (apply str 
       (map repl (read-string (str \[ (first content-field) \])))))

(defn repl-b [form]
  (str "B => " (str form) \newline
       (str (try (lc/eval-ir-formula (b-expression->ir form))
                 (catch Exception e "lol, broken"))) \newline) )

(defn get-forms-b [content-field]
  (apply str 
       (map repl-b (clojure.string/split-lines (first content-field)))))


(defn lisb-b' [form]
  (str "lisb2B=> " (str form) \newline
       (str (try (lisb->b (read-string form))
                 (catch Exception e "lol, broken"))) \newline) )

(defn lisb-b [content-field]
  (apply str 
       (map lisb-b' (clojure.string/split-lines (first content-field)))))

(defn replace-lisb-blocks
  [article _params]
  #_(println article)
  (use 'lisb.translation.util)
  (update article
          :content-dom
          (fn [content-dom]
            (let [res (->> content-dom  
                       (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (= "lisb" (get-in % [:attrs :class]))))]
                                    (fn [m] (-> m 
                                                (assoc-in [:attrs :class] "clojure") (update :content get-forms))))
                       (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (= "b-expr" (get-in % [:attrs :class]))))]
                                    (fn [m] (-> m 
                                                (assoc-in [:attrs :class] "B") (update :content get-forms-b))))
                       (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (= "pplisb" (get-in % [:attrs :class]))))]
                                    (fn [m] (-> m 
                                                (assoc-in [:attrs :class] "clojure") (update :content lisb-b)))
                                    ))
                  ]
              #_(println res)
              res
              ))))

