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

(require '[ring.adapter.jetty      :as jetty])
(require '[ring.middleware.params  :as http-params])
(require '[ring.middleware.cookies :as cookies])

(require '[clojure.tools.cli       :as cli])
(require '[clojure.tools.logging   :as log])

(def cli-options
    "Definitions of all command line options that are  currenty supported."
    ;; an option with a required argument
    [["-h"   "--help"                       "show help"                                                    :id :help]
     ["-p"   "--port   PORT"                "port number on which Emender Jenkins should accepts requests" :id :port]
     ["-c"   "--config"                     "just show the actual configuration"                           :id :show-config]])

(defn show-help
    "Show help and all supported CLI flags."
    [summary]
    (println "Usage:")
    (println summary))

(defn show-config
    "Show the current configuration on the standard output."
    [options]
    (let [port                (options :port)]
       ;(let [configuration (->
       ;         (config/load-configuration-from-ini "config.ini")
       ;         (config/override-options-by-cli jenkins-url test-jobs-suffix))]
       ;    (config/print-configuration configuration))
        (println "Finished")))

(defn run-app
    "Starts application on any supported configuration: regular machine or OpenStack VM."
    [options started-on started-system-ms]
    (let [port                (options :port)
          openshift-ip        (System/getenv "OPENSHIFT_CLOJURE_HTTP_IP")
          openshift-port      (System/getenv "OPENSHIFT_CLOJURE_HTTP_PORT")]
        (log/info "started app on port" port)
        (log/info "openshift-ip" openshift-ip)
        (log/info "openshift-port" openshift-port)
        ))


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

