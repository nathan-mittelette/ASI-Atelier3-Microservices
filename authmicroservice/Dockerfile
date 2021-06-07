FROM openjdk:11.0-jre
ENV HOME /opt/demo
WORKDIR $HOME

COPY target/*.jar $HOME/demo.jar

ENTRYPOINT java -jar demo.jar
