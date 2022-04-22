price-compare-backend
=====================

Requirements
------------

### Requirements with Docker

- [Docker](https://docs.docker.com/engine/install/)
- [docker-compose](https://docs.docker.com/compose/)

### Requirements without Docker

- [OpenJDK 17 or above](https://openjdk.java.net/install/) or [Oracle JDK 17 or above](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [MongoDB 4.4 or above](https://www.mongodb.com/try/download/community)

How to run
----------

### Running with Docker

You need to run the command below to start MongoDB and Java application containers.

```
$ docker-compose up -d
```

### Running without Docker

You need to run the command below to start this Java application using embedded Gradle.

```
$ ./gradlew bootRun --args='--spring.profiles.active=local'
```

API Documentation
-----------------

This project has an embedded Swagger. You can read all documentation about the endpoints 
accessing http://127.0.0.1:8080/docs/index.html

Tips
----

- If you need to install Java JDK, you can use [SDKMAN](https://sdkman.io/) to do this more easily.
