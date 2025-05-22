Feature: Validate creation a pet


Feature: Validate creation of a pet

  Scenario Outline: Validate successful creation of a pet
    Given the pet with ID "<petID>" does not exist in the system
    When a POST request is sent to the endpoint "/pet" with the following data
      | tags        |
      | Color white |
      | 12f         |
    Then the response status code should be <status>

    Examples:
      | petID  | status |
      | 1      | 200    |
      | 1e1e1e | 400    |