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

(ns emender-waive.config
    "Module that contains all functions that are required to load configuration
     from the provided INI file.")

(require '[clojure.pprint :as pprint])
(require '[clojure.tools.logging :as log])

(require '[emender-waive.utils         :as utils])
(require '[emender-waive.config-loader :as config-loader])

(def default-port
    "Default port used when no -p or --port CLI option is specified."
    "3000")

(defn update-configuration
    "Update selected items in the configuration structure."
    [configuration]
    (-> configuration
        (update-in [:config :verbose]                    utils/parse-boolean)
        (update-in [:config :pretty-print]               utils/parse-boolean)))

(defn load-configuration-from-ini
    "Load configuration from the provided INI file and perform conversions
     on selected items from strings to numeric or Boolean values."
    [ini-file-name]
    (-> (config-loader/load-configuration-file ini-file-name)
        update-configuration))

(defn assoc-in-if-not-nil
    "Assoc new (updated) value into the configuration map, but only when
     new value exists. If value does not exist at all, the old value is kept."
    [input selector value]
    (if value
        (assoc-in input selector value)
        input))

(defn override-runtime-params
    [configuration started-on started-system-ms]
    (-> configuration
        (assoc :started-on started-on)
        (assoc :started-ms started-system-ms)))

(defn print-configuration
    "Print actual configuration to the standard output."
    [configuration]
    (pprint/pprint configuration))

(defn get-api-prefix
    "Read prefix for API calls from the configuration passed via
     HTTP request structure (middleware can be used to pass config into it)."
    [request]
    (-> request :configuration :api :prefix))

(defn get-gui-prefix
    "Read prefix for GUI calls from the configuration passed via
     HTTP request structure (middleware can be used to pass config into it)."
    [request]
    (-> request :configuration :gui :prefix))

(defn verbose?
    "Read verbose mode settings from the configuration passed via
     HTTP request structure (middleware can be used to pass config into it)."
    [request]
    (-> request :configuration :config :verbose))

(defn get-version
    "Read service version from the configuration passed via
     HTTP request structure (middleware can be used to pass config into it)."
    [request]
    (-> request :configuration :info :version))

(defn pretty-print?
    "Read the pretty-print settings (it is used for JSON output etc.)"
    [request]
    (-> request :configuration :config :pretty-print))

(defn get-started-on-str
    [request]
    (-> request :configuration :started-on))

(defn get-started-on-ms
    [request]
    (-> request :configuration :started-ms))

