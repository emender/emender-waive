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

(ns emender-waive.rest-api
    "Handler for all REST API calls.")

(require '[ring.util.response         :as http-response])
(require '[clojure.pprint             :as pprint])
(require '[clojure.data.json          :as json])
(require '[clj-fileutils.fileutils    :as file-utils])
(require '[clojure.tools.logging      :as log])

(require '[emender-waive.config       :as config])
(require '[emender-waive.waive        :as waive])
(require '[emender-waive.process-info :as process-info])

; HTTP codes used by several REST API responses
(def http-codes {
    :ok                    200
    :bad-request           400
    :not-found             404
    :internal-server-error 500
    :not-implemented       501})

(defn read-request-body
    "Read all informations from the request body."
    [request]
    (file-utils/slurp- (:body request)))

(defn body->results
    "Try to parse the request body as JSON format."
    [body]
    (json/read-str body))

(defn send-response
    "Send normal response (with application/json MIME type) back to the client."
    [response request]
    (if (config/pretty-print? request)
        (-> (http-response/response (with-out-str (json/pprint response)))
            (http-response/content-type "application/json"))
        (-> (http-response/response (json/write-str response))
            (http-response/content-type "application/json"))))

(defn send-error-response
    "Send error response (with application/json MIME type) back to the client."
    [response request http-code]
    (if (config/pretty-print? request)
        (-> (http-response/response (with-out-str (json/pprint response)))
            (http-response/content-type "application/json")
            (http-response/status (get http-codes http-code)))
        (-> (http-response/response (json/write-str response))
            (http-response/content-type "application/json")
            (http-response/status (get http-codes http-code)))))

(defn send-plain-response
    "Send a response (with application/json MIME type) back to the client."
    [response]
    (-> (http-response/response response)
        (http-response/content-type "application/json")))

(defn info-handler
    "REST API handler for the /api request."
    [request hostname]
    (let [response {:name       "Emender Waive Service"
                    :version    (config/get-version request)
                    :api_prefix (config/get-api-prefix request)
                    :gui_prefix (config/get-gui-prefix request)
                    :hostname   hostname :test "/api"}]
        (send-response response request)))

(defn configuration-handler
    "REST API handler for the /api/configuration request."
    [request]
    (let [response (-> request :configuration)]
        (send-response response request)))

(defn status-handler
    "REST API handler for the /api/status"
    [request]
    (let [response {:properties     (process-info/read-properties)
                    :pid            (process-info/get-current-pid)
                    :started-on     (config/get-started-on-str request)
                    :started-ms     (config/get-started-on-ms request)
                    :current-time   (.toString (new java.util.Date))
                    :uptime-seconds (/ (- (System/currentTimeMillis) (config/get-started-on-ms request)) 1000)}]
         (send-response response request)))

(defn unknown-call-handler
    "Function called for all unknown API calls."
    [request uri method]
    (let [response {:status :error
                    :error "Unknown API call"
                    :uri uri
                    :method method}]
        (send-error-response response request :bad-request)))

