FROM java:8-jre
WORKDIR /usr/src
EXPOSE 9500
ADD ./target/articleRecommendationService-1.0.jar /usr/src/articleRecommendationService-1.0.jar
ENTRYPOINT ["java", "-jar", "articleRecommendationService-1.0.jar"]