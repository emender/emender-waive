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

(ns emender-waive.config-loader-test
  (:require [clojure.test :refer :all]
            [emender-waive.config-loader :refer :all]))

;
; Common functions used by tests.
;

(defn callable?
    "Test if given function-name is bound to the real function."
    [function-name]
    (clojure.test/function? function-name))

;
; Tests for functions existence
;
(deftest test-properties->map-existence
    "Check that the emender-waive.config-loader/properties->map definition exists."
    (testing "if the emender-waive.config-loader/properties->map definition exists."
        (is (callable? 'emender-waive.config-loader/properties->map))))


(deftest test-load-property-file-existence
    "Check that the emender-waive.config-loader/load-property-file definition exists."
    (testing "if the emender-waive.config-loader/load-property-file definition exists."
        (is (callable? 'emender-waive.config-loader/load-property-file))))


(deftest test-load-configuration-file-existence
    "Check that the emender-waive.config-loader/load-configuration-file definition exists."
    (testing "if the emender-waive.config-loader/load-configuration-file definition exists."
        (is (callable? 'emender-waive.config-loader/load-configuration-file))))

;
; Tests for functions behaviour
;

(deftest test-properties->map-1
    "Check the behaviour of function emender-waive.config-loader/properties->map."
    (let [property (doto (new java.util.Properties)
                         (.setProperty "a" "A")
                         (.setProperty "b" "B"))]
        (is (= {:a "A" :b "B"} (properties->map property)))))

(deftest test-properties->map-2
    "Check the behaviour of function emender-waive.config-loader/properties->map."
    (let [property (doto (new java.util.Properties)
                         (.setProperty "propertyA" "property_a")
                         (.setProperty "propertyB" "property_b"))]
        (is (= {:propertyA "property_a" :propertyB "property_b"} (properties->map property)))))

(deftest test-properties->map-3
    "Check the behaviour of function emender-waive.config-loader/properties->map."
    (let [property (doto (new java.util.Properties)
                         (.setProperty "value1" "1")
                         (.setProperty "value2" "2"))]
        (is (= {:value1 "1" :value2 "2"} (properties->map property)))))

(deftest test-properties->map-4
    "Check the behaviour of function emender-waive.config-loader/properties->map."
    (let [property (doto (new java.util.Properties)
                         (.setProperty "value1" "")
                         (.setProperty "" "2"))]
        (is (= {(keyword "") "2" :value1 ""} (properties->map property)))))

