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

(ns emender-waive.config-test
  (:require [clojure.test   :refer :all]
            [clojure.pprint :as     pprint]
            [emender-waive.config :refer :all]))

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

;
; Tests for functions behaviour
;

(deftest test-assoc-in-if-not-nil-1
    "Check the behaviour of function emender-waive.config/assoc-in-if-not-nil."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (assoc-in-if-not-nil {:first 1 :second 2} [:third] 3)        {:first 1 :second 2 :third 3}
            (assoc-in-if-not-nil {:first 1 :second 2} [:third] "string") {:first 1 :second 2 :third "string"}
            (assoc-in-if-not-nil {:first 1 :second 2} [:third] '())      {:first 1 :second 2 :third ()}
            (assoc-in-if-not-nil {:first 1 :second 2} [:third] [])       {:first 1 :second 2 :third []}
            (assoc-in-if-not-nil {:first 1 :second 2} [:third] '(1 2 3)) {:first 1 :second 2 :third '(1 2 3)}
            (assoc-in-if-not-nil {:first 1 :second 2} [:third] [1 2 3])  {:first 1 :second 2 :third [1 2 3]})))

(deftest test-assoc-in-if-not-nil-2
    "Check the behaviour of function emender-waive.config/assoc-in-if-not-nil."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (assoc-in-if-not-nil {:first 1 :second 2} [:third] nil)   {:first 1 :second 2}
            (assoc-in-if-not-nil {:first 1 :second 2} [:third] false) {:first 1 :second 2}
            (assoc-in-if-not-nil {:first 1 :second 2} [:third] true)  {:first 1 :second 2 :third true})))

(deftest test-assoc-in-if-not-nil-3
    "Check the behaviour of function emender-waive.config/assoc-in-if-not-nil."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (assoc-in-if-not-nil {:first 1 :second 2} [:first] 3)        {:second 2 :first 3}
            (assoc-in-if-not-nil {:first 1 :second 2} [:first] "string") {:second 2 :first "string"}
            (assoc-in-if-not-nil {:first 1 :second 2} [:first] '())      {:second 2 :first ()}
            (assoc-in-if-not-nil {:first 1 :second 2} [:first] [])       {:second 2 :first []}
            (assoc-in-if-not-nil {:first 1 :second 2} [:first] '(1 2 3)) {:second 2 :first '(1 2 3)}
            (assoc-in-if-not-nil {:first 1 :second 2} [:first] [1 2 3])  {:second 2 :first [1 2 3]})))

(deftest test-assoc-in-if-not-nil-4
    "Check the behaviour of function emender-waive.config/assoc-in-if-not-nil."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (assoc-in-if-not-nil {:first 1 :second 2} [:second] 3)        {:first 1 :second 3}
            (assoc-in-if-not-nil {:first 1 :second 2} [:second] "string") {:first 1 :second "string"}
            (assoc-in-if-not-nil {:first 1 :second 2} [:second] '())      {:first 1 :second ()}
            (assoc-in-if-not-nil {:first 1 :second 2} [:second] [])       {:first 1 :second []}
            (assoc-in-if-not-nil {:first 1 :second 2} [:second] '(1 2 3)) {:first 1 :second '(1 2 3)}
            (assoc-in-if-not-nil {:first 1 :second 2} [:second] [1 2 3])  {:first 1 :second [1 2 3]})))

(deftest test-assoc-in-if-not-nil-5
    "Check the behaviour of function emender-waive.config/assoc-in-if-not-nil."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (assoc-in-if-not-nil {:first {:left "L" :right "R"} :second {:left "L" :right "R"}} [:first :left] "***")   {:first {:left "***", :right "R"},   :second {:left "L",   :right "R"}}
            (assoc-in-if-not-nil {:first {:left "L" :right "R"} :second {:left "L" :right "R"}} [:first :right] "***")  {:first {:left "L",   :right "***"}, :second {:left "L",   :right "R"}}
            (assoc-in-if-not-nil {:first {:left "L" :right "R"} :second {:left "L" :right "R"}} [:second :left] "***")  {:first {:left "L",   :right "R"},   :second {:left "***", :right "R"}}
            (assoc-in-if-not-nil {:first {:left "L" :right "R"} :second {:left "L" :right "R"}} [:second :right] "***") {:first {:left "L",   :right "R"},   :second {:left "L",   :right "***"}})))

(deftest test-assoc-in-if-not-nil-6
    "Check the behaviour of function emender-waive.config/assoc-in-if-not-nil."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (assoc-in-if-not-nil {:first {:left "L" :right "R"} :second {:left "L" :right "R"}} [:first :left] nil)   {:first {:left "L", :right "R"}, :second {:left "L", :right "R"}}
            (assoc-in-if-not-nil {:first {:left "L" :right "R"} :second {:left "L" :right "R"}} [:first :right] nil)  {:first {:left "L", :right "R"}, :second {:left "L", :right "R"}}
            (assoc-in-if-not-nil {:first {:left "L" :right "R"} :second {:left "L" :right "R"}} [:second :left] nil)  {:first {:left "L", :right "R"}, :second {:left "L", :right "R"}}
            (assoc-in-if-not-nil {:first {:left "L" :right "R"} :second {:left "L" :right "R"}} [:second :right] nil) {:first {:left "L", :right "R"}, :second {:left "L", :right "R"}})))

(deftest test-print-configuration
    "Check the behaviour of function emender-waive.config/print-configuration."
        ; use mock instead of clojure.pprint/pprint
        (with-redefs [pprint/pprint (fn [configuration] (str configuration))]
            (is (not (nil? (print-configuration {:first 1 :second 2}))))
            (is (= (type (print-configuration   {:first 1 :second 2})) java.lang.String))))

(deftest test-get-api-prefix-1
    "Check the behaviour of function emender-waive.config/get-api-prefix."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (get-api-prefix {:configuration {:api {:prefix nil}}}) nil
            (get-api-prefix {:configuration {:api {:prefix "xyzzy"}}}) "xyzzy")))

(deftest test-get-api-prefix-2
    "Check the behaviour of function emender-waive.config/get-api-prefix."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (get-api-prefix {:configuration {:api nil}}) nil
            (get-api-prefix {:configuration nil}) nil
            (get-api-prefix nil) nil)))

(deftest test-get-gui-prefix-1
    "Check the behaviour of function emender-waive.config/get-gui-prefix."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (get-gui-prefix {:configuration {:gui {:prefix nil}}}) nil
            (get-gui-prefix {:configuration {:gui {:prefix "xyzzy"}}}) "xyzzy")))

(deftest test-get-gui-prefix-2
    "Check the behaviour of function emender-waive.config/get-gui-prefix."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (get-gui-prefix {:configuration {:gu, nil}}) nil
            (get-gui-prefix {:configuration nil}) nil
            (get-gui-prefix nil) nil)))

(deftest test-get-version-1
    "Check the behaviour of function emender-waive.config/get-version."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (get-version {:configuration {:info {:version nil}}}) nil
            (get-version {:configuration {:info {:version "1.0"}}}) "1.0")))

(deftest test-get-version-2
    "Check the behaviour of function emender-waive.config/get-version."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (get-version {:configuration {:info nil}}) nil
            (get-version {:configuration nil}) nil
            (get-version nil) nil)))

(deftest test-verbose?-1
    "Check the behaviour of function emender-waive.config/verbose?."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (verbose? {:configuration {:config {:verbose nil}}})   nil
            (verbose? {:configuration {:config {:verbose false}}}) false
            (verbose? {:configuration {:config {:verbose true}}})  true
            (verbose? {:configuration {:config {:verbose "xyzzy"}}}) "xyzzy")))

(deftest test-verbose?-2
    "Check the behaviour of function emender-waive.config/verbose?."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (verbose? {:configuration {:config nil}}) nil
            (verbose? {:configuration nil}) nil
            (verbose? nil) nil)))

(deftest test-pretty-print?-1
    "Check the behaviour of function emender-waive.config/pretty-print?."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (pretty-print? {:configuration {:config {:pretty-print nil}}})   nil
            (pretty-print? {:configuration {:config {:pretty-print false}}}) false
            (pretty-print? {:configuration {:config {:pretty-print true}}})  true
            (pretty-print? {:configuration {:config {:pretty-print "xyzzy"}}}) "xyzzy")))

(deftest test-pretty-print?-2
    "Check the behaviour of function emender-waive.config/pretty-print?."
    (testing "the behaviour of function emender-waive.config/assoc-in-if-not-nil."
        (are [x y] (= x y)
            (pretty-print? {:configuration {:config nil}}) nil
            (pretty-print? {:configuration nil}) nil
            (pretty-print? nil) nil)))

(deftest test-get-started-on-str-1
    "Check the behaviour of function emender-waive.config/get-started-on-str."
    (testing "the behaviour of function emender-waive.config/get-started-on-str."
        (are [x y] (= x y)
            (get-started-on-str {:configuration {:started-on nil}})   nil
            (get-started-on-str {:configuration {:started-on false}}) false
            (get-started-on-str {:configuration {:started-on true}})  true
            (get-started-on-str {:configuration {:started-on "10:20:30"}}) "10:20:30")))

(deftest test-get-started-on-str-2
    "Check the behaviour of function emender-waive.config/get-started-on-str."
    (testing "the behaviour of function emender-waive.config/get-started-on-str."
        (are [x y] (= x y)
            (get-started-on-str {:configuration {:started-on nil}}) nil
            (get-started-on-str {:configuration nil})               nil
            (get-started-on-str {:configuration nil})               nil)))

(deftest test-get-started-on-ms-1
    "Check the behaviour of function emender-waive.config/get-started-on-ms."
    (testing "the behaviour of function emender-waive.config/get-started-on-ms."
        (are [x y] (= x y)
            (get-started-on-ms {:configuration {:started-ms nil}})   nil
            (get-started-on-ms {:configuration {:started-ms false}}) false
            (get-started-on-ms {:configuration {:started-ms true}})  true
            (get-started-on-ms {:configuration {:started-ms 12345}}) 12345)))

(deftest test-get-started-on-ms-2
    "Check the behaviour of function emender-waive.config/get-started-on-ms."
    (testing "the behaviour of function emender-waive.config/get-started-on-ms."
        (are [x y] (= x y)
            (get-started-on-ms {:configuration {:started-ms nil}}) nil
            (get-started-on-ms {:configuration nil})               nil
            (get-started-on-ms {:configuration nil})               nil)))

(deftest test-override-runtime-params-1
    "Check the behaviour of function emender-jenkins.config/override-runtime-params."
    (testing "the behaviour of function emender-jenkins.config/override-runtime-params."
    (let [cfg (-> (load-configuration-from-ini "test/test1.ini")
                  (override-runtime-params nil nil))]
        (are [x y] (= x y)
            (-> cfg :started-on)                       nil
            (-> cfg :started-ms)                       nil))))

(deftest test-override-runtime-params-2
    "Check the behaviour of function emender-jenkins.config/override-runtime-params."
    (testing "the behaviour of function emender-jenkins.config/override-runtime-params."
    (let [cfg (-> (load-configuration-from-ini "test/test1.ini")
                  (override-runtime-params "10:20:30" nil))]
        (are [x y] (= x y)
            (-> cfg :started-on)                       "10:20:30"
            (-> cfg :started-ms)                       nil))))

(deftest test-override-runtime-params-3
    "Check the behaviour of function emender-jenkins.config/override-runtime-params."
    (testing "the behaviour of function emender-jenkins.config/override-runtime-params."
    (let [cfg (-> (load-configuration-from-ini "test/test1.ini")
                  (override-runtime-params nil 123456))]
        (are [x y] (= x y)
            (-> cfg :started-on)                       nil
            (-> cfg :started-ms)                       123456))))

