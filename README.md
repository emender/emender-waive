# emender-waive

REST API and web-bases interface that is used to 'waive' Emender test results.



## Prerequisities

### Prerequisities for compiling and installation

1. JVM version 6 or better
1. Leiningen

### For the already compiled service

1. JRE version 6 or better (JRE 7 is recommended)



## Installation

1. Clone the emender-waive repository
1. Run the following commands:

    $ lein deps
    $ lein uberjar

Result is stored in an Java archive (JAR) that can be found in the 'target/uberjar' subdirectory.



## Usage

To start the service, run the following command:

    $ lein run

Or alternatively you can use the Java archive:

    $ java -jar target/uberjar/emender-waive-0.1.0-SNAPSHOT-standalone.jar



## Options

Current version of this service accepts the following command line options:



## REST API

Please read [this file](doc/REST_API.adoc)



## License

Copyright © 2017 Pavel Tisnovsky, Red Hat Inc.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
