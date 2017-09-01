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

(ns emender-waive.utils-test
  (:require [clojure.test :refer :all]
            [emender-waive.utils :refer :all]))

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

(deftest test-third-existence
    "Check that the emender-waive.utils/third definition exists."
    (testing "if the emender-waive.utils/third definition exists."
        (is (callable? 'emender-waive.utils/third))))


(deftest test-fourth-existence
    "Check that the emender-waive.utils/fourth definition exists."
    (testing "if the emender-waive.utils/fourth definition exists."
        (is (callable? 'emender-waive.utils/fourth))))


(deftest test-substring-existence
    "Check that the emender-waive.utils/substring definition exists."
    (testing "if the emender-waive.utils/substring definition exists."
        (is (callable? 'emender-waive.utils/substring))))


(deftest test-startsWith-existence
    "Check that the emender-waive.utils/startsWith definition exists."
    (testing "if the emender-waive.utils/startsWith definition exists."
        (is (callable? 'emender-waive.utils/startsWith))))


(deftest test-endsWith-existence
    "Check that the emender-waive.utils/endsWith definition exists."
    (testing "if the emender-waive.utils/endsWith definition exists."
        (is (callable? 'emender-waive.utils/endsWith))))


(deftest test-contains-existence
    "Check that the emender-waive.utils/contains definition exists."
    (testing "if the emender-waive.utils/contains definition exists."
        (is (callable? 'emender-waive.utils/contains))))


(deftest test-replaceAll-existence
    "Check that the emender-waive.utils/replaceAll definition exists."
    (testing "if the emender-waive.utils/replaceAll definition exists."
        (is (callable? 'emender-waive.utils/replaceAll))))


(deftest test-get-exception-message-existence
    "Check that the emender-waive.utils/get-exception-message definition exists."
    (testing "if the emender-waive.utils/get-exception-message definition exists."
        (is (callable? 'emender-waive.utils/get-exception-message))))


(deftest test-parse-int-existence
    "Check that the emender-waive.utils/parse-int definition exists."
    (testing "if the emender-waive.utils/parse-int definition exists."
        (is (callable? 'emender-waive.utils/parse-int))))


(deftest test-parse-float-existence
    "Check that the emender-waive.utils/parse-float definition exists."
    (testing "if the emender-waive.utils/parse-float definition exists."
        (is (callable? 'emender-waive.utils/parse-float))))


(deftest test-parse-boolean-existence
    "Check that the emender-waive.utils/parse-boolean definition exists."
    (testing "if the emender-waive.utils/parse-boolean definition exists."
        (is (callable? 'emender-waive.utils/parse-boolean))))



;
; Tests for functions behaviour
;

