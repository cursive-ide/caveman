(ns example.main
  (:require [example.system :as system]))

(defn -main
  "Start the web server"
  [& _args]
  (system/start-system 3000)
  (println "Server running on port 3000"))
