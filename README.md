# FeedReader
**RSS Feed Reader service with RESTful API**

## Technology stack:
 - Java [1.8+](http://www.oracle.com/technetwork/java/javase/overview/index.html)
 - Maven [3+](https://maven.apache.org)
 - Spring Boot [2.1.4.RELEASE](https://spring.io/projects/spring-boot)
 - Spring Data Rest
 - Rome [1.12.0] (https://rometools.github.io/rome/)
 - Spring JDBC
 - H2 Database [1.4.199](http://www.h2database.com)
 - Slf4J [included](https://www.slf4j.org)
 - Spring Test
 - JUnit [4.12](https://junit.org/junit4)
 - Awaitility [3.1.6](https://github.com/awaitility/awaitility)
 
## Build and Run
Maven Clean & Build
```sh
./mvnw clean install
```
```sh
# all tests will run during build by default
# if you want, you can run the tests independently
./mvnw test
```

Maven Run
```sh
./mvnw spring-boot:run
```

JAR Run
```sh
java -jar target/FeedReader-1.0.jar
```

Application starts on 
```sh 
http://localhost:8080/
```

H2 Console
```sh
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:feed
User Name: sa
Password:
```

RSS Feed URL
```sh
You can change it in application.properties file, property - rss.feed.url
```

## Endpoints
| HTTP METHOD | PATH | USAGE | EXAMPLE REQUEST |
| ----------- | ------ | ------ | ------ |
| GET | / | health check | curl -X GET http://localhost:8080/ |
| GET | /rss | get last 10 RSS entries | curl -X GET http://localhost:8080/rss |