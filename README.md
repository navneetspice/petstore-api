## API tests for https://petstore.swagger.io/
## the GET and DELETE calls returns 404 at times, but passes upon subsequent executions. hence, the tests are reattempted to get the desired results ##
## RestAssured Response is NOT deseriealize using POJO. json response is used for assertion. ##
find example tests for Pet store swagger api.

Tool & Technologies used:

- Test automation framework: TestNG
- Reporting: TestNG Report
- API client/testing framework: rest-assured
- Build tool: Maven
- Java 1.8+

In order to run the tests execute below maven command:
- Prerequisite: maven should be configured properly
- download the code from git and build it using maven in an IDE. or simply unzip the code folder and run the mvn command in command prompt after navigating to the folder where POM is placed.
- if the parameters are not passed in maven command, default will be used.

mvn clean compile test "-Dbase_url=https://petstore.swagger.io" "-DendPoint=/v2/pet"

upon execution, target folder will be generated and report will be available in 
```target/surefire-reports/Pet Store API tests/index.html```
&
```target/surefire-reports/Pet Store API tests/emailable-report.html```



