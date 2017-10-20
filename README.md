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

To run the previous commands you need to have Leiningen installed. Please see the following section about its installation.


### Leiningen installation

Emender-waive uses Leiningen for automatic project management. This is the standard tool used for almost all Clojure applications, modules, and libraries. It means that in order to install and run Emender-waive, you need to install Leiningen first

1. Download the https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein[lein script]
1. Place it on your `$PATH` where your shell can find it (eg. `~/bin`)
1. Set it to be executable (`chmod a+x ~/bin/lein`)
1. Run it (`lein`) and it will download the self-install package



## Usage

There is not need for compiling the application manually, because Clojure VM uses just-in-time compilation.

### Start the service

To start the service, run the following command:

    $ lein run

Or alternatively you can use the Java archive:

    $ java -jar target/uberjar/emender-waive-0.1.0-SNAPSHOT-standalone.jar

### Run the application in devel mode

Run the application in devel mode

    $ lein ring server-headless

Check project structure + basic syntax check

    $ lein check

Run unit tests

    $ lein test

Compute and display code coverage

    $ lein cloverage

Generate documentation

    $ lein doc

Generate JAR archive with everything included

    $ lein uberjar

Local installation

    $ lein install

Start interactive REPL

    $ lein repl



## Options

Current version of this service accepts the following command line options:



## REST API

Please read [this file](doc/REST_API.adoc)



## Useful links

* link:https://leiningen.org/[Leiningen]



## License

Copyright © 2017 Pavel Tisnovsky, Red Hat Inc.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
