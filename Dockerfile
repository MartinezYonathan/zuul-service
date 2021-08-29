FROM openjdk:8-jdk-slim
RUN pwd
ADD ./target/zuul-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","app.jar"]
