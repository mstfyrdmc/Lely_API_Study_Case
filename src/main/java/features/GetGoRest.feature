Feature: Go Rest Api Tests

  Scenario: Check all of the data.id values are 4 digit integers and none of them are null
    When I send a get request
    Then I should see all of data.id values are 4 digit integers and none of them are null