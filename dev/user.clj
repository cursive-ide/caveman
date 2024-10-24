(ns user
  (:require [example.system :as system]))

(defonce system (atom nil))

(defn server []
  (::system/server @system))

(defn start-system! []
  (when @system
    (system/stop-system @system))
  (reset! system (system/start-system 3000))
  :started)

(defn stop-system! []
  (when @system
    (system/stop-system @system)
    (reset! system nil))
  :stopped)

(defn restart-system! []
  (stop-system!)
  (start-system!))
