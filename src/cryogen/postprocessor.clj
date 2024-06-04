(ns cryogen.postprocessor
  (:require [com.rpl.specter :as s]
            [lisb.core :as lc])
  (:use [lisb.translation.util]))
  
(defn repl [form]
  (println form)
  (str "user=> " (str form) "   ;; B: " (try (lisb.translation.util/lisb->b form) (catch Exception e "lol, broken")) \newline
       (str (try (lc/eval-ir-formula (lisb->ir form))
                 (catch Exception e "lol, broken"))) \newline))

(defn get-forms [content-field]
  (apply str 
       (map repl (read-string (str \[ (first content-field) \])))))

(defn repl-b [form]
  (println :XXX/b form)
  (str "B => " (str form) \newline
       (str (try (lc/eval-ir-formula (b-expression->ir form))
                 (catch Exception e "lol, broken"))) \newline) )

(defn get-forms-b [content-field]
  (println content-field)
  (apply str 
       (map repl-b (clojure.string/split-lines (first content-field)))))

(defn replace-lisb-blocks
  [article _params]
  (println article)
  (update article
          :content-dom
          (fn [content-dom]
            (let [res  (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (= "lisb" (get-in % [:attrs :class]))))]
                                    (fn [m] (-> m 
                                                (assoc-in [:attrs :class] "clojure") (update :content get-forms)))
                                    content-dom)
                  res (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (= "b-expr" (get-in % [:attrs :class]))))]
                                    (fn [m] (-> m 
                                                (assoc-in [:attrs :class] "B") (update :content get-forms-b)))
                                    res)
                  ]
              (println res)
              res
              ))))

