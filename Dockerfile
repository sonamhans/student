FROM openjdk:21-ea-oracle
EXPOSE 8080
ADD target/student-service.jar student-service.jar
ENTRYPOINT ["java","-jar","/student-service.jar"]