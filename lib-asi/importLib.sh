#!/usr/bin/env sh

# Installation des dépendances
mvn clean install -DskipTests

# Installation du répo en local
mvn install -DskipTests
