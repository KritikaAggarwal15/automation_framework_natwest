Feature: Validation of creation of new object with details

  Scenario Outline: Allowing a user to create a new object with details
    Given a user wants to create a new object with the following details at row <index>
      | name                 | color     | capacity | price | currency | generation | year | CPU model     | Strap Colour | Case Size |
      | Apple MacBook Pro 16 | Blue      | 128 GB   | 1100  | GB       |            |      | Intel Core i9 |              |           |
      | Iphone 15 pro        | Grey      | 256 GB   | 999   | GB       |            |      |               |              | 41mm      |
      | Iphone 15            | Black     | 128 GB   | 900   | GB       | 3rd        |      |               | Blue         |           |
      | Samsung Galaxy  Plus | Rose Gold | 258 GB   | 1000  | GB       |            | 2019 |               |              |           |
      | Lenovo laptop        |           |          |       |          |            |      |               |              |           |

    When the user sends a POST request to create a new object
    Then the response status code should be 200
    And the json response should generate "id" field with a value
    And the json response should generate "createdAt" field with a value
    And "createdAt" should be in date time format
    And the JSON response data should match the data used for sending the request
    And verify that the item has been created with the same values provided in post request

    Examples:
      | index |
      | 0     |
      | 1     |
      | 2     |
      | 3     |
      | 4     |

