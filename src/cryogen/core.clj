(ns cryogen.core
  (:require [cryogen-core.compiler :refer [compile-assets-timed]]
            [cryogen-core.plugins :refer [load-plugins]]
            [com.rpl.specter :as s]
            ))

(defn replace-lisb-blocks
  [article _params]
  (println article)
  (update article
          :content-dom
          (fn [content-dom]
            (let [res  (s/transform [(s/walker #(and (map? %) (= (:tag %) :code) (= "lisb" (get-in % [:attrs :class])))) ]
                                    (fn [m] (-> m 
                                                (assoc-in [:attrs :class] "clojure")
                                                (update :content (fn [s]
                                                                   (map (fn [call]
                                                                          (str (list* call) " => " (eval call) \newline)) 
                                                                        (read-string (str \[ (first s) \])))))))
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

