(ns example.routes
  (:require [clojure.tools.logging :as log]
            [hiccup.page :as page]))

(defn hello-handler [_system _request]
  (log/debug "Handling hello request")
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str
          (page/html5
           [:head
            [:title "Hello"]]
           [:body
            [:h1 "Hello, World!"]
            [:p "Welcome to our site"]
            [:a {:href "/goodbye"} "Say Goodbye"]]))})

(defn goodbye-handler [_system _request]
  (log/debug "Handling goodbye request")
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str
          (page/html5
           [:head
            [:title "Goodbye"]]
           [:body
            [:h1 "Goodbye!"]
            [:p "Thanks for visiting"]
            [:a {:href "/hello"} "Say Hello Again"]]))})

(defn root-handler [system]
  (fn [request]
    (log/debug "Handling request for" (:uri request))
    (case (:uri request)
      "/hello" (hello-handler system request)
      "/goodbye" (goodbye-handler system request)
      {:status 404
       :headers {"Content-Type" "text/html"}
       :body (str
              (page/html5
               [:head
                [:title "Not Found"]]
               [:body
                [:h1 "Page Not Found"]
                [:p "The requested page does not exist"]
                [:a {:href "/hello"} "Go to Hello page"]]))})))

(defn handler [system]
  (root-handler system))
