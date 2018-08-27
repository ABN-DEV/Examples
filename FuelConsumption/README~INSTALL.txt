
1. How to run the APP: use a file run-web-app.cmd 
(at WINDOWS OS open CMD, change a directory to the project. Enter the command run-web-app.cmd and press enter)

2. The REST service. 
- It has swagger description. Open URL http://localhost:8080/swagger-ui.html You can see there all information about REST services and you can try some of them.
- API DOCS url is http://localhost:8080/v2/api-docs

3. Database. 
- It has default data in file /FuelConsumption/src/main/resources/data.sql
- web interface H2 database url is http://localhost:8080/h2-console/

4. The TESTS 

4.1 The `/register` tests are 
4.1.1 Check violation for Duplicate values.
4.1.2 Violation empty records. 
4.1.1 Register single object.
4.1.1 Register array of objects.

4.2 The Repository tests are
4.2.1 The test for statistics.
4.2.1 The test for list fuel cosumption records.
4.2.1 The test for total spent amount.
 

