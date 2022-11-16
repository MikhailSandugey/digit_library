# Digital Library

Digital Library is a Java Web Application for dealing accounting for books in libraries.

## Description

This application keeps track of book lending to people. Also it has authentication for librarians so they can log in with their personal credentials and assign books to people. 

Stack of techonologies:
- Java 17
- Spring Framework (MVC, Data, Security) 
- PosgreSQL
- Thymeleaf
- Liquibase

## How to use

To clone and run this application you need to do the following steps:

```bash
# Clone this repository using Git Bash
$ git clone https://github.com/MikhailSandugey/digit_library.git
```

Set up `application.properties` and `liquibase.properties` files in `src/main/resources` directory for database.

Then the application can be run with following commands :

```bash
# Go into the repository using command line
cd digit_library

# For Linux/MacOS users use ./mvnw package instead
mvnw package 

# Run the app
java -jar target/digitlib-0.0.1-SNAPSHOT.jar
```
