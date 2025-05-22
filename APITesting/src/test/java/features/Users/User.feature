Feature: Validate the user endpoint is working properly

  Scenario Outline: Validate the create a new user
    When the manager creates a new user with "<id>" "<userStatus>"
    Then the request response <statusCode>
    And the user was created

    Examples:
      | id | userStatus | statusCode |  |
      | 1  | 1          | 200        |  |
      | 2b | 2          | 400        |  |
      | 3  | 3bg        | 400        |  |


  Scenario Outline: Validate create a list of new users
    When the manager upload a list <size> of users
    Then the request response <statusCode>
    And the list of new user was upload

    Examples:
        | size | statusCode |
        | 5    | 200        |
        | 13   | 200          |


  Scenario: Validate user login successfully
    When the user send login request
    Then the request response 200
    And the body must response the logged session


  Scenario: Validate the user logout
    When the user send logout request
    Then the request response 200
    And the request must return a message "User logged out"

  Scenario Outline: Validate the get information about an user
    When the user search the "<username>"
    Then the request response <statusCode>
    And must return the user information

    Examples:
      | username | statusCode |
      | default  | 200        |
      | testFail | 404        |

  Scenario: Valdiate the user delete
    When the user delete the "default"
    Then the request response 200


