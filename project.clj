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
                   [org.xerial/sqlite-jdbc "3.7.2"]]
    :dev-dependencies [[lein-ring "0.8.10"]]
    :main ^:skip-aot emender-waive.core
    :target-path "target/%s"
    :profiles {:uberjar {:aot :all}})

