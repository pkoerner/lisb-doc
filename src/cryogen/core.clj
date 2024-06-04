(ns cryogen.core
  (:require [cryogen-core.compiler :refer [compile-assets-timed]]
            [cryogen-core.plugins :refer [load-plugins]]
            [cryogen.postprocessor :refer [replace-lisb-blocks]] 
            [com.rpl.specter :as s]
            [lisb.core :as lc])
  (:use [lisb.translation.util])
  )

(defn -main []
  (println :hello)
  (load-plugins)
  (cryogen-core.compiler/compile-assets-timed
     {:update-article-fn #_:postprocess-article-html-fn
      replace-lisb-blocks
      })
  (System/exit 0))

