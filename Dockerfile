FROM amazon-corretto-17
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} ./
ENTRYPOINT ["java","-jar","spring-security-demo-0.0.1-SNAPSHOT.jar"]