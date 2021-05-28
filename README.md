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

Cela génère un fichier `jar` dans le dossier `target` du projet `lib-asi`.
C'est dans ce fichier jar que contient les class de la library.

### Import du fichier jar en tant library interne.

Il faut explicier à maven le nom de la library et la façon de la récupérer : 

```bash
# lib-asi
mvn install:install-file -Dfile=target/lib-asi-1.0-SNAPSHOT.jar -DgroupId=com.asi.lib -DartifactId=lib-asi -Dversion=1.0-SNAPSHOT -Dpackaging=jar
```