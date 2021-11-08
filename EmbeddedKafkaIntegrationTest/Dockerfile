FROM openjdk:8
WORKDIR usr/src
ADD ./target/EmbeddedKafkaIntegrationTest-1.0.jar /usr/src/EmbeddedKafkaIntegrationTest-1.0.jar
ENTRYPOINT ["java","-jar", "EmbeddedKafkaIntegrationTest-1.0.jar"]