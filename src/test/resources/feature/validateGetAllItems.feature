Feature: Validation of retrieval List of Objects

  Scenario: Allowing user to retrieve a list of all objects
    Given a user wants to retrieve all available objects
    When a user sends a GET request to retrieve objects
    Then the response status code should be 200
    And the response should contain a list of JSON objects
    And each JSON objects should have the following Fields:
    |id   |
    |name |
    |data |
    And "id" field value should not be blank for each JSON Object