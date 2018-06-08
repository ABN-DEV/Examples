
Used stack frames: 
- Spring Boot 2.8.0 (spring 5.0.6.RELEASE)
-- spring-boot-starter-web
-- spring-boot-starter-data-rest
-- spring-boot-starter-data-jpa
-- spring-boot-starter-test
-- spring-boot-actuator
-- spring-boot-devtools
-- spring-boot-starter-hateoas

- SWAGGER 2 ( springfox-swagger-ui, springfox-swagger2)
- H2

- MAVEN 3.3.9+
- JAVA 8 

HOW TO RUN 
you have to have installed maven 3.3.9+ version and JAVA 8 version.

1) clone repository into your computer. 

2) H2 database is embedded. H2 console at the url http://localhost:8080/h2-console/ 
JDBC URL: jdbc:h2:~/REST_EXAMPLE, 
user:sa, 
password:sa, 

3) Change a directory to the root of the test, and start to file run-web-app.cmd 
or execute the command in CMD : mvn spring-boot:run -Drun.jvmArguments="-Drunning.from.maven=true"

Open browser url : 


http://localhost:8080/swagger-ui.html
~ Swagger API Documentation (basic controller is UserController).

http://localhost:8080/v2/api-docs 
~ WADL for each endpoints 

http://localhost:8080/



 
