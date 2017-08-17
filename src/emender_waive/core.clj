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

(ns emender-waive.core
    "Core module that contains -main function called by Leiningen to start the application."
    (:gen-class))

(require '[ring.adapter.jetty        :as jetty])
(require '[ring.middleware.params    :as http-params])
(require '[ring.middleware.cookies   :as cookies])

(require '[clojure.tools.cli         :as cli])
(require '[clojure.tools.logging     :as log])

(require '[emender-waive.config      :as config])
(require '[emender-waive.server      :as server])
(require '[emender-waive.middleware  :as middleware])

(def cli-options
    "Definitions of all command line options that are  currenty supported."
    ;; an option with a required argument
    [["-h"   "--help"                       "show help"                                                    :id :help]
     ["-p"   "--port   PORT"                "port number on which Emender Jenkins should accepts requests" :id :port]
     ["-c"   "--config"                     "just show the actual configuration"                           :id :show-config]])

; we need to load the configuration in advance so the 'app' could use it
(def configuration (config/load-configuration-from-ini "config.ini"))

(defn show-help
    "Show help and all supported CLI flags."
    [summary]
    (println "Usage:")
    (println summary))

(defn show-config
    "Show the current configuration on the standard output."
    [options]
    (let [port                (options :port)]
        (let [configuration (->
                 (config/load-configuration-from-ini "config.ini"))]
                 ; (config/override-options-by-cli jenkins-url test-jobs-suffix))]
            (config/print-configuration configuration))
        (println "Finished")))

(def app
    "Definition of a Ring-based application behaviour."
    (-> server/handler            ; handle all events
        (middleware/inject-configuration configuration) ; inject configuration structure into the parameter
        cookies/wrap-cookies      ; we need to work with cookies
        http-params/wrap-params)) ; and to process request parameters, of course

(defn start-server-on-regular-machine
    "Start the service on regular machine."
    [ring-app port]
    (log/info "Starting the server at the port: " port)
    (jetty/run-jetty ring-app {:port (read-string port)}))

(defn start-server-on-openshift
    "Start the service on OpenShift machine."
    [ring-app port host]
    (log/info "Starting the server on openshift at the port: " port " and host: " host)
    (jetty/run-jetty ring-app {:port (read-string port) :host host}))

(defn start-server
    "Start server on specified port."
    [configuration port openshift-port openshift-ip]
    (let [ring-app
        (-> server/handler            ; handle all events
            (middleware/inject-configuration configuration) ; inject configuration structure into the parameter
            cookies/wrap-cookies      ; we need to work with cookies
            http-params/wrap-params)] ; and to process request parameters, of course
        (if (and openshift-ip openshift-port)
            (start-server-on-openshift       ring-app openshift-port openshift-ip)
            (start-server-on-regular-machine ring-app port))))

(defn get-and-check-port
    "Accepts port number represented by string and throws AssertionError
     if port number is outside defined range."
    [port]
    (let [port-number (. Integer parseInt port)]
        (assert (> port-number 0))
        (assert (< port-number 65536))
        port))

(defn get-port
    "Returns specified port or default port if none is specified on the command line."
    [specified-port]
    (if (or (not specified-port) (not (string? specified-port)) (empty? specified-port))
        config/default-port
        (get-and-check-port specified-port)))

(defn run-app
    "Starts application on any supported configuration: regular machine or OpenStack VM."
    [options started-on started-system-ms]
    (let [port                (options :port)
          openshift-ip        (System/getenv "OPENSHIFT_CLOJURE_HTTP_IP")
          openshift-port      (System/getenv "OPENSHIFT_CLOJURE_HTTP_PORT")]
        (log/info "started app on port" port)
        (log/info "openshift-ip" openshift-ip)
        (log/info "openshift-port" openshift-port)
        (let [configuration (->
                 (config/load-configuration-from-ini "config.ini")
                 (config/override-runtime-params started-on started-system-ms))]
            ;(results/reload-all-results configuration) ; to be done in the job-data-fetcher module
            (start-server configuration (get-port port) openshift-port openshift-ip))))

(defn -main
    "Entry point to the Emender-Jenkins service server."
    [& args]
    (let [all-options         (cli/parse-opts args cli-options)
          options             (all-options :options)
          show-help?          (options :help)
          show-config?        (options :show-config)
          started-on-str      (.toString (new java.util.Date))
          started-system-ms   (System/currentTimeMillis)]
          (cond show-help?   (show-help (:summary all-options))
                show-config? (show-config options)
                :else        (run-app options started-on-str started-system-ms))))

; finito

