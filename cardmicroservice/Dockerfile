# Build
FROM maven:3.8-jdk-11-slim AS demo-build
ENV DEMO_HOME /opt/demo
WORKDIR $DEMO_HOME
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

# Run
FROM openjdk:11.0-jre as demo-run
ENV DEMO_HOME /opt/demo
WORKDIR $DEMO_HOME
COPY --from=demo-build $DEMO_HOME/target/*.jar $DEMO_HOME/demo.jar

EXPOSE 5000

ENTRYPOINT java -jar demo.jar