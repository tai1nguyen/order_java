# Order-Java
A simple **Spring Boot** CRUD application for ordering coffee beans. A personal project to help with learning **Spring** technologies, **Hibernate**, **SQL** and **Vue**.

## Todo
- Server
    - [ ] create initial application
        - [X] create database
        - [X] create api document
        - [X] create CRUD rest APIs
        - [X] add unit tests
        - [X] add integration tests
        - [ ] add front-end client
    - [ ] improve tests
        - [ ] update tests to use separate database data
    - [ ] improve database
        - [ ] convert in-memory database to read and store data to a static file.
- Client
    - [ ] create intial application
        - [ ] create landing page
        - [ ] create inventory browsing page
        - [ ] create a checkout page
        - [ ] add unit tests

## Running the App
- Start the server with `./gradlew bootRun`. The application will be hosted at `http://localhost:8080/`
- Run all tests with `./gradlew test`