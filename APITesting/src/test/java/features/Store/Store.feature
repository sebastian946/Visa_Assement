Feature: Validate all the store endpoints

  Scenario Outline: Validate status <status> of create endpoint
    When the user created a new order with "<id>" and "<petId>"
    Then the user receive a <status>
    And the response body response is "<match>"

    Examples:
      | id | petId  | status | match |
      | 1  | 1234   | 200    | true  |
      | 2  | 123b56 | 400    | false |
      | 3b |83043   | 400    | false |


  Scenario: Validate get Inventory
    When the user send request to get inventory
    Then the user get status 200
    And the response body with "approved" "delivered"


  Scenario Outline: Validate get specific order <id>
    When the user send request to get the order "<id>"
    Then the user get status <statuscode>
    And the user get body response

    Examples:
      | id | statuscode |
      | 1  |200         |
      | 1b |400         |

    Scenario Outline: Validate erase an order
      When the user send request to delete an order "<id>"
      Then the user receive status <statuscode>

      Examples:
        | id | statuscode |
        | 1  |200         |
        | 1b |400         |