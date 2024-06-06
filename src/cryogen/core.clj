(ns cryogen.core
  (:require [cryogen-core.compiler :refer [compile-assets-timed]]
            [cryogen-core.plugins :refer [load-plugins]]
            [cryogen.postprocessor :refer [replace-lisb-blocks]] 
            [com.rpl.specter :as s]
            [lisb.core :as lc])
  (:use [lisb.translation.util])
  )

(defn group-docs-by-section [pages]
  (->> pages
       (map #(select-keys % [:title :uri :page-index :section]))
       (group-by :section)
       (map (fn [[section pages]]
              {:section section
               :pages   (map #(select-keys % [:title :uri]) pages)}))))


(defn -main []
  (println :hello)
  (load-plugins)
  (cryogen-core.compiler/compile-assets-timed
     {:update-article-fn #_:postprocess-article-html-fn
      replace-lisb-blocks
      :extend-params-fn
      (fn extend-params [params {:keys [pages]}]
        (assoc params :sections
                      (group-docs-by-section pages)))
      })
  (System/exit 0))

