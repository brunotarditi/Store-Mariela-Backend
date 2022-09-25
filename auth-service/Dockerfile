FROM openjdk:12
VOLUME /tmp
ADD ./target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "auth-service.jar"]