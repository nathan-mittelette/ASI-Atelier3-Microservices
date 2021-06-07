# ASI Step 3

## Compilation du projet

Chaque microservice ou library du projet est un module du `pom.xml` présent à la racine du projet. Lorsque vous
souhaitez lancer le projet, vous devez effectuer la commande suivante à la racine du projet :

```bash
mvn clean install -DskipTests
```

Cela va compiler tous les microservices et les library du projet.

On peut ensuite lancer le docker-compose avec l'ensemble des microservices

```bash
docker-compose up --build
```

## Lors du développement

Lorsque vous développez sur un microservice en particulier vous allez avoir besoin de build uniquement le projet sur
lequel vous travaillez. Dans les cas ou vous allez changez la library il sera nécessaire de relancer une insatllation
maven de la lib et ensuite du microservice pour que les changements soient présents.

Pour lancer un projet spécifique :

Une fois le clean install effectuer vous vous rendez dans le projet que vous souhaitez lancer et effectuez la commande
suivante :

```bash
mvn spring-boot:run
```

PS je vais vous montrer comment lancer tous les projets depuis IntelliJ une fois à CPE.

## Création du package maven

### Création du projet Maven

Pour la création du projet maven la commande suivante à été utilisé :

```bash
# À la racine du projet.
mvn archetype:generate -DgroupId=com.asi.lib -DartifactId=lib-asi -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

### Ajout du contenu dans la lib

Ajout des DTO dans la lib.

### Création du fichier Jar de la library.

Lancement de la commande maven pour faire le package de la library.

```bash
# /lib-asi
mvn package
```

Cela génère un fichier `jar` dans le dossier `target` du projet `lib-asi`. C'est dans ce fichier jar que contient les
class de la library.

### Import du fichier jar en tant library interne.

Il faut explicier à maven le nom de la library et la façon de la récupérer :

```bash
# lib-asi
mvn install:install-file -Dfile=target/lib-asi-1.0-SNAPSHOT.jar -DgroupId=com.asi.lib -DartifactId=lib-asi -Dversion=1.0-SNAPSHOT -Dpackaging=jar
```
