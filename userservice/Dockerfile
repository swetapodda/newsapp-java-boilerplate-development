FROM java:8-jre
WORKDIR /usr/src
EXPOSE 9300
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
ENTRYPOINT ["java", "-jar", "userservice-1.0.jar"]