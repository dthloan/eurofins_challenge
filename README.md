# EUROFINS_CHALLENGE

This repository contains the implementation for the Eurofins Automation Challenge using **RestAssured**, **Cucumber**, **TestNG**, and **Maven**.

## Features
There is one Cucumber feature file representing the API test cases:
- **QuoteCreation.feature**

The automation runs in a single environment:
- **HOST_API**: `http://localhost:54958`

For future enhancements, you will need to modify the value of `HOST_API` in the **env.properties** file.

## Setup and Running Tests

### IntelliJ IDEA:

1. Clone the project and import it into IntelliJ IDEA.
2. Ensure that your project bytecode version is set to the latest in the Java Compiler settings.
3. Install the **Cucumber** and **Gherkin** plugins for Java if not already installed.
4. Open the **TestRunner.java** class, and run it as a TestNG class.

To run specific features, update the tags in **TestRunner.java** as follows:
- **To run all tests for Quote Creation**: `@quote_creation`
- **To run a specific scenario**: `@ac1`, `@ac2`, ..., `@ac5`

### Command Line:

1. Clone the project and navigate to the project directory in the terminal.
2. Ensure that **Maven** is installed. Run the following command to check the Maven version:
    ```bash
    mvn --version
    ```
3. To run the tests, use the following command:
    ```bash
    mvn clean test
    ```

## Viewing the Test Report

After running the tests, the report can be found in the following directory:
- **target/cucumber-reports/**

Open the **html-report.html** file to view the results for the API test suite.