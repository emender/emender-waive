;
;  (C) Copyright 2017  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns emender-waive.server
    "Server module that accepts all requests from users and generates proper responses.")

(require '[clojure.pprint           :as pprint])
(require '[ring.util.response       :as http-response])
(require '[clojure.tools.logging    :as log])

(require '[emender-waive.config     :as config])
(require '[emender-waive.rest-api   :as rest-api])

(use     '[emender-waive.utils])

(defn return-file
    "Creates HTTP response containing content of specified file.
     Special value nil / HTTP response 404 is returned in case of any I/O error."
    [file-name content-type]
    (-> (http-response/file-response file-name {:root "www"})
        (http-response/content-type content-type)))

(defn render-front-page
    "Create front page."
    [request]
    (return-file "index.html" "text/html"))

(defn render-error-page
    "Create error page."
    [request]
    (return-file "error.html" "text/html"))

(defn get-hostname
    "Returns hostname of the server."
    []
    (.. java.net.InetAddress getLocalHost getHostName))

(defn get-api-part-from-uri
    "Get API part (string) from the full URI. The API part string should not starts with /"
    [uri prefix]
    (let [api-part (re-find #"/[^/]*" (subs uri (count prefix)))]
       (if (and api-part (startsWith api-part "/"))
           (subs api-part 1)
           api-part)))

(defn get-api-command
    "Retrieve the actual command from the API call."
    [uri prefix]
    (if uri
        (if (startsWith uri prefix)
            (let [uri-without-prefix (subs uri (count prefix))]
                (if (empty? uri-without-prefix) ; special handler for a call with / only
                    ""
                    (get-api-part-from-uri uri prefix))))))

(defn api-call-handler
    "This function is used to handle all API calls. Three parameters are expected:
     data structure containing HTTP request, string with URI, and the HTTP method."
    [request uri method]
    (if (= uri "/api")
        (rest-api/info-handler request (get-hostname))
        (condp = [method (get-api-command uri (config/get-api-prefix request))]
            [:get  ""]                      (rest-api/info-handler request   (get-hostname))
        )))

(defn non-api-call-handler
    "This function is used to handle all API calls. Two parameters are expected:
     data structure containing HTTP request and string with URI."
    [request uri]
    (condp = uri
        "/"                      (render-front-page request)
                                 (render-error-page request)))

(defn log-request
    "Add info about the request into the log"
    [request]
    (if (config/verbose? request)
        (if (config/pretty-print? request)
            (pprint/pprint request)
            (log/info "Handling request: " request))
        (log/info "Handling request: " (:uri request) (:remote-addr request))))

(defn handler
    "Handler that is called by Ring for all requests received from user(s)."
    [request]
    (log-request request)
    (let [uri    (:uri request)
          method (:request-method request)]
         (if (startsWith uri (config/get-api-prefix request))
             (api-call-handler     request uri method)
             (non-api-call-handler request uri))))
