# NewsApp - A Case Study

## Problem Statement

Build a system to search for news article, open the article, add article to favourite list and recommend most liked/favourite articles to user.

## Requirements

1. The application needs to fetch news article from the following API.
https://newsapi.org/

Example API:
https://newsapi.org/v2/everything?q=bitcoin&from=2019-01-06&sortBy=publishedAt&apiKey=b170738ea8d145159da715566679a48fea

2. A frontend where the user can register/login to the application, search for article, open interested article, add article to favourite list and view recommended articles.

  - On launching the application the user should get the login page. The login page should have a link for registration using which the user should be able to register. On successful registration the user should be taken to the login page. Upon login, the user should be taken to the home page.
  - Proper navigation links for all the pages should be available within pages.
  - Error handling should be implemented across pages. Appropriate messages should be    displayed for the same. Success messages should be displayed for the User Registration.
  - Logout feature should be implemented.

3. User can add an article to favourite list and should be able to view the favourite list.
4. A recommendation service should be able to store all the unique favourite articles from all the users and maintain counter for number of users added a particular article into favourite list. 
### An option to view recommended articles should be available on frontend. 

## Microservices/Modules

- UI (User interface) -  should be able to
    1. Search for a news article
    2. View or open an article 
    3. Add an article into favourite list
    4. View favourite articles
    5. View recommended articles
    5. UI should be responsive which can run smoothly on various devices 
- UserService - should be able to manage user accounts.
- FavouriteService - should be able to store all the favourite articles for a user
- ArticleRecommendationService - should be able to store all the unique favourite         articles from all the users and maintain counter for number of users added a            particular article into favourite list.

## Tech Stack
- Spring Boot
- MySQL, MongoDB
- Zuul API Gateway
- Eureka Server
- Message Broker (RabbitMQ)
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose

## Flow of Modules

### Building frontend:
- Building responsive views:
    1. Register/Login
    2. Search for an article
    3. Article list - populating from external API
    4. Build a view to show favourite articles
    5. Build a view to show recommended articles
- Using Services to populate these data in views
- Stitching these views using Routes and Guards
- Making the UI Responsive
- Unit Tests should be created for the Components and Services
- E2E scripts using protractor should be created for the functional flows
- Implement CI - continuous Integration ( use .gitlab-ci.yml)
- Dockerize the frontend (create dockerfile, docker-compose.yml and get it executed through docker compose)

### Note: FrontEnd and all the backend services should be dockerized and run through docker-compose

### Building the UserService
- Creating a server in Spring Boot to facilitate user registration and login with MySQL as backend. Upon login, JWT token has to be generated. It has to be used in the Filters set for other services.
- Writing swagger documentation
- Unit Testing of the entire code which involves the positive and negative scenarios
- Implement continuous Integration CI ( use .gitlab-ci.yml)
- Dockerize the UserService (create dockerfile, docker-compose.yml and get it executed through docker compose)

### Create an API Gateway (Zuul) which can serve UI and API Request from same host. 

### Apply JWT Authentication in the API Gateway for all the incoming requests

### Building the Favourite Service
- Building a server in Spring Boot to facilitate CRUD operation over favourite articles   stored in MongoDB
- Writing Swagger Documentation
- Build a Producer for RabbitMQ which
  - i. Produces events like what user added to favourite list
- Write Unit Test Cases and get it executed.
- Implement CI - continuous Integration ( use .gitlab-ci.yml)
- Dockerize the service(create dockerfile and update the docker-compose.yml)

### Building the Article Recommendation Service
- Building a Consumer for RabbitMQ
  - i. On a new event generated Update the recommendations in the system.  Store the         recommendation list in MongoDB.
  - ii. Maintain list of unique recommended articles based on what user added into            favourite list and keep counter for number of users added a particular article        into favourite list
- Build an API to get Recommendations
- Writing Swagger Documentation
- Write Unit Test Cases and get it executed.
- Implement CI - continuous Integration ( use .gitlab-ci.yml)
- Dockerize the service(create dockerfile and update the docker-compose.yml)
- Update the API Gateway

### Create Eureka server and make other services as client

### Demonstrate the entire application
    1. Make sure all the functionalities are implemented completely
    2. Make sure both the UI (Component and Services) and server side (For all layers) codes are unit tested. 
    3. All the Services are up and running using docker (Dockercompose should be used for running them)
    4. Eureka server should be running and all the required services are registered and started
    5. Rabbit-MQ service should be running ( check whether exchanges are created)
    6. E2E tests should have been created and can be executed successfully
    7. Application is completely responsive in nature