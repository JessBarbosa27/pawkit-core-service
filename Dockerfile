FROM openjdk:21

EXPOSE 6001

ADD target/pawkit_core_service.jar pawkit_core_service.jar

ENTRYPOINT ["java", "jar", "/pawkit_core_service.jar"]