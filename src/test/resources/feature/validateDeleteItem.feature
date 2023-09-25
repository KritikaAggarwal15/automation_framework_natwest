Feature: Validation of deleting an existing object created earlier

  Scenario Outline: Allowing a user to delete an existing object
    Given an object with details
    And a user wants to delete this object
    When the user sends a "DELETE" request to the server <index>
    Then the response status code should be 200
    And the JSON response should contain "Object with id = <id> has been deleted" message
    And deleted object should have been deleted from the server

    Examples:
      | index |
      | 0     |
      | 1     |
      | 2     |
      | 3     |
