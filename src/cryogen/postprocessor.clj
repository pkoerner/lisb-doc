(ns cryogen.postprocessor
  (:require [com.rpl.specter :as s]
            [lisb.core :as lc])
  (:use [lisb.translation.util]))

(defn wrap-exception->nil [f]
  (fn [& args] (try (apply f args) (catch Exception _ nil))))

(defn b-something->ir [bstr]
  (or (some identity ((apply juxt (map wrap-exception->nil [b-predicate->ir b-expression->ir b-formula->ir b->ir b-substitution->ir b-operation->ir b-machine-clause->ir])) bstr))
      "ha-Ha broken"))

(defn repl-clj [form]
  (str "clj=> " (str form) \newline
       (pr-str (try (let [res (eval form)]
                      res)
                 (catch Exception e "lol, broken"))) \newline))

(defn repl [form]
  #_(println form)
  (str "lisb=> " (str form) "   ;; B: " (try (lisb.translation.util/lisb->b form) (catch Exception e "lol, broken")) \newline
       (str (try (let [res (lc/eval-ir-formula (lisb->ir form))]
                   (case res
                     {} "{} ;; TRUE, no bindings"
                     nil "nil ;; FALSE"
                     res))
                 (catch Exception e "lol, broken"))) \newline))

(defn get-forms-clj [content-field]
  (apply str 
       (map repl-clj (read-string (str \[ (first content-field) \])))))

(defn get-forms [content-field]
  (apply str 
       (map repl (read-string (str \[ (first content-field) \])))))

(defn repl-b [form]
  (str "B => " (str form) \newline
       (str (try (let [res (lc/eval-ir-formula (b-something->ir #_b-expression->ir form))]
                   (case res
                     {} "TRUE"
                     nil "FALSE"
                     res))
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
                       (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (= "clj" (get-in % [:attrs :class]))))]
                                    (fn [m] (-> m 
                                                (assoc-in [:attrs :class] "clojure") (update :content get-forms-clj))))
                       (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (= "lisb" (get-in % [:attrs :class]))))]
                                    (fn [m] (-> m 
                                                (assoc-in [:attrs :class] "clojure") (update :content get-forms))))
                       (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (#{"B" "b-expr"} (get-in % [:attrs :class]))))]
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

