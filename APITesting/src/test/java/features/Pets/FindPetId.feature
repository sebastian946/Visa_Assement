Feature: Validate search pet with ID

  Background:
    Given the system create a Pet with ID "12"


    Scenario Outline: Validate search a pet with ID <ID>
      When the system searches for pets with ID "<ID>"
      Then the response status code should <statusCode>

      Examples:
        | ID | statusCode |
        | 10 | 200        |
        | 0  | 404        |
