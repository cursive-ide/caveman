(ns example.system
  (:require [ring.adapter.jetty :as jetty]
            [example.routes :as routes]
            [clojure.tools.logging :as log]))

(defn start-server
  "Start the web server on the specified port"
  [system port]
  (log/info "Starting server on port" port)
  (jetty/run-jetty (routes/handler system) {:port port :join? false}))

(defn stop-server
  "Stop the web server"
  [server]
  (log/info "Stopping server")
  (.stop server))

(defn start-system [port]
  (let [system {}
        server (start-server system port)]
    (assoc system ::server server)))

(defn stop-system [system]
  (when-let [server (::server system)]
    (stop-server server))
  nil)
