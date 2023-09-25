Feature: Validation of error scenarios for invalid requests

  Scenario Outline: Validate error scenarios with invalid objects Ids for GET and DELETE Methods
    Given the "<condition>" Id "<Id>"
    When a user calls "<HTTPMethod>" service to "HTTPMethod" Object with "<Id>"
    Then the response status code should be 404
    And the response should contain error message "Object with id=<id> was not found"


    Examples:
      | HTTPMethod | condition                  | Id     |
      | GET        | invalid                    | 366666 |
      | GET        | invalid                    | 800000 |
      | GET        | invalid                    | 900000 |
      | GET        | Id with special characters | #%#$#  |
      | GET        | Id with alphabets only     | abc    |
      | DELETE     | invalid                    | 366666 |
      | DELETE     | invalid                    | 800000 |
      | DELETE     | invalid                    | 900000 |
      | DELETE     | Id with special characters | #%#$#  |
      | DELETE     | Id with alphabets only     | abc    |


  Scenario Outline: Validate error scenarios when user creates a new object with invalid data
    Given an invalid JSON having "<condition>"
    And a user wants to create a new object with invalid JSON
    When the user sends a POST request to create a new object
    Then the response status code should be 200
    And JSON objects should have the following Fields:
      | id   |
      | name |
      | data |
    And "id" field value should not be blank for JSON Object

    Examples:
      | condition    |
      | blank        |
      | invalid Keys |
      | extra Keys   |
      | invalid Type |

