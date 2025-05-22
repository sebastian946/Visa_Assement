Feature: Validate the update information of the pets

  Background:
    Given the system create a Pet with ID "10"

  Scenario Outline: Validate update the pet information
    When the system update the information "<ID>" "<Name>" "<status>"
    Then the status code should be <statusCode>

    Examples:
      | ID | Name | status    | statusCode |
      | 10 | Max  | available | 200        |
      | 10 | Fox  | sold      | 200        |