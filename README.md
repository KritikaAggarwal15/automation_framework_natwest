# NatWest QA Automation Framework

## Overview

This automation framework acts as the middleware layer between client applications and upstream services, facilitating robust quality assurance for NatWest's software products.

## Features

### Test Scenarios

Our comprehensive framework covers a wide range of test scenarios for the NatWest **Object** service, encompassing positive and negative cases. Key validations include:

1. **Validation of Creating New Objects:**
    - Validates the creation of new objects using a POST request with various data scenarios.
    - Ensures correct response status codes for successful creation.
    - Verifies the presence of specific fields in the response, such as the "id" field.
    - Validates the format of response data, particularly for the "createdAt" field.
    - Matches response data with the input data.
    - Matches input data with a GET request after creating a new object.

2. **Validation of Deleting an Existing Object:**
    - Validates the deletion of an existing object using a DELETE request.
    - Checks response status codes for successful deletion.
    - Verifies the presence of a specific success message in the response.
    - Ensures that the deleted object is no longer present on the server.

3. **Validation of Retrieving a List of Objects:**
    - Validates the retrieval of a list of objects using a GET request.
    - Checks the response status code for success (200 OK).
    - Ensures each JSON object in the response contains specific fields: "id," "name," and "data."
    - Validates that the "id" field in each JSON object is not blank.

4. **Validate Error Scenarios:**
    - Validates error scenarios for GET and DELETE requests with invalid object IDs.
    - Checks that the response status code is 404 (Not Found) for these scenarios.
    - Confirms error messages are as expected.
    - Verifies that the response contains an error message indicating the object was not found.
    - Validates error scenarios when creating a new object with invalid JSON data.
    - Sends a POST request with different types of invalid JSON data.
    - Ensures the response status code is 200 (OK) for all scenarios.
    - Validates that the response JSON objects contain specific fields: "id," "name," and "data."
    - Verifies that the "id" field in each JSON object is not blank.

### Technology Stack

- **Java**: The primary programming language used for test automation.
- **Cucumber**: Utilized for writing feature files and defining test scenarios.
- **REST Assured**: A popular library for testing RESTful APIs.

## Getting Started Instructions

Follow these steps to set up and run the automated tests:

1. **Navigate to the Project's Root Directory:**

   ```bash
   cd automation_framework_natwest
   ````

2. **Build the Project Using Maven:**

   ```bash
   mvn clean install
   ````
   
3. **Run All Tests:**
   ```bash
   mvn test
   ````

4. **Run Tests for a Specific Environment:**
   ```bash
   mvn test -Denv=<file.properties>
   ````
5. **Access Test Reports:**

   After completing the tests, detailed Serenity test reports can be found in the test/resources/spark/index.html file.




