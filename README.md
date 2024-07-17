# RBAC
Contains the Role Based Access Control (RBAC) persistence and service layer 
for an application. It can be imported to an existing object. You will have to 
create the necessary objects, but this is self contained and all tests are
performed using an in-memory database (H2).

## Usage
    mvn verify
    --build project locally and run all unit/integration tests

    mvn install
    --build project locally and make it available for other projects

## Build
You will need the following to build this project
1. Java 17+
2. Maven 3.8+
3. Install parent pom project and/or use access token to build via settings.xml

