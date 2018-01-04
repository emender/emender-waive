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

(defproject emender-waive "0.1.0-SNAPSHOT"
    :description "FIXME: write description"
    :url "http://github.com/emender/emender-waive"
    :license {:name "Eclipse Public License"
              :url "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.7.0"]
                   [org.clojure/java.jdbc "0.3.5"]
                   [org.clojure/tools.cli "0.3.1"]
                   [org.xerial/sqlite-jdbc "3.7.2"]
                   [ring/ring-core "1.3.2"]
                   [ring/ring-jetty-adapter "1.3.2"]
                   [org.clojure/data.json "0.2.5"]
                   [clj-http "2.0.0"]
                   [clojure-ini "0.0.1"]
                   [hiccup "1.0.4"]
                   [org.clojure/tools.logging "0.3.1"]
                   [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                      javax.jms/jms
                                                      com.sun.jmdk/jmxtools
                                                      com.sun.jmx/jmxri]]
                   [org.slf4j/slf4j-log4j12 "1.6.6"]
                   [org.clojars.tisnik/clj-fileutils "0.2.0-SNAPSHOT"]]
    :dev-dependencies [[lein-ring "0.8.10"]]
    :plugins [[lein-ring "0.8.10"]
              [codox "0.8.11"]
              [test2junit "1.1.0"]
              [lein-cloverage "1.0.6"]]
              ;[lein-cucumber "1.0.2"]]
    :main ^:skip-aot emender-waive.core
    :ring {:handler emender-jenkins.core/app}
    :target-path "target/%s"
    :profiles {:uberjar {:aot :all}})

