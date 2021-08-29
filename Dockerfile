FROM openjdk:8-jdk-slim
RUN pwd
ADD ./target/zuul-server.jar app.jar
EXPOSE 8798
ENTRYPOINT ["java","-jar","app.jar"]
