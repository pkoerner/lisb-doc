(ns cryogen.core
  (:require [cryogen-core.compiler :refer [compile-assets-timed]]
            [cryogen-core.plugins :refer [load-plugins]]
            [com.rpl.specter :as s]
            [lisb.core :as lc])
  (:use [lisb.translation.util])
  )

(defn repl [form]
  (println form)
  (str "user=> " (str form) "   ;; B: " (try (lisb->b form) (catch Exception e "lol, broken")) \newline
       (str (try (lc/eval-ir-formula (lisb->ir form))
                 (catch Exception e "lol, broken"))) \newline))

(defn get-forms [content-field]
  (apply str 
       (map repl (read-string (str \[ (first content-field) \])))))

(defn replace-lisb-blocks
  [article _params]
  (println article)
  (update article
          :content-dom
          (fn [content-dom]
            (let [res  (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (= "lisb" (get-in % [:attrs :class])))) ]
                                    (fn [m] (-> m 
                                                (assoc-in [:attrs :class] "clojure")
                                                (update :content 
                                                        get-forms)))
                                    content-dom)]
              (println res)
              res
              ))))

(defn -main []
  (println :hello)
  (load-plugins)
  (cryogen-core.compiler/compile-assets-timed
     {:update-article-fn #_:postprocess-article-html-fn
      replace-lisb-blocks
      })
  (System/exit 0))

