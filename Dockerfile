FROM openjdk:8
COPY build/libs/craft.jar craft.jar
ENTRYPOINT ["java","-jar","/craft.jar"]
