#!/usr/bin/env sh
mvn clean install -DskipTests
mvn package -DskipTests
mvn install:install-file -Dfile=target/lib-asi-1.0-SNAPSHOT.jar -DgroupId=com.asi.lib -DartifactId=lib-asi -Dversion=1.0-SNAPSHOT -Dpackaging=jar
