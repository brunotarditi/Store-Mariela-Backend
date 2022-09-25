FROM openjdk:12
VOLUME /tmp
ADD ./target/brand-service-0.0.1-SNAPSHOT.jar brand-service.jar
ENTRYPOINT ["java", "-jar", "brand-service.jar"]