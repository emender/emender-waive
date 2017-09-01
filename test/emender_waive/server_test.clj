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

(ns emender-waive.server-test
  (:require [clojure.test :refer :all]
            [emender-waive.server :refer :all]))

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

(deftest test-return-file-existence
    "Check that the emender-waive.server/return-file definition exists."
    (testing "if the emender-waive.server/return-file definition exists."
        (is (callable? 'emender-waive.server/return-file))))


(deftest test-render-front-page-existence
    "Check that the emender-waive.server/render-front-page definition exists."
    (testing "if the emender-waive.server/render-front-page definition exists."
        (is (callable? 'emender-waive.server/render-front-page))))


(deftest test-render-error-page-existence
    "Check that the emender-waive.server/render-error-page definition exists."
    (testing "if the emender-waive.server/render-error-page definition exists."
        (is (callable? 'emender-waive.server/render-error-page))))


(deftest test-get-hostname-existence
    "Check that the emender-waive.server/get-hostname definition exists."
    (testing "if the emender-waive.server/get-hostname definition exists."
        (is (callable? 'emender-waive.server/get-hostname))))


(deftest test-get-api-command-existence
    "Check that the emender-waive.server/get-api-command definition exists."
    (testing "if the emender-waive.server/get-api-command definition exists."
        (is (callable? 'emender-waive.server/get-api-command))))


(deftest test-api-call-handler-existence
    "Check that the emender-waive.server/api-call-handler definition exists."
    (testing "if the emender-waive.server/api-call-handler definition exists."
        (is (callable? 'emender-waive.server/api-call-handler))))


(deftest test-non-api-call-handler-existence
    "Check that the emender-waive.server/non-api-call-handler definition exists."
    (testing "if the emender-waive.server/non-api-call-handler definition exists."
        (is (callable? 'emender-waive.server/non-api-call-handler))))


(deftest test-handler-existence
    "Check that the emender-waive.server/handler definition exists."
    (testing "if the emender-waive.server/handler definition exists."
        (is (callable? 'emender-waive.server/handler))))


(deftest test-log-request-existence
    "Check that the emender-waive.server/log-request definition exists."
    (testing "if the emender-waive.server/log-request definition exists."
        (is (callable? 'emender-waive.server/log-request))))

;
; Tests for functions behaviour
;

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))
