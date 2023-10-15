Feature: Go Rest Api Tests

  Scenario Outline: Post users
    When I send a post request with name "<name>" , email "<email>" , gender "<gender>" , status "<status>"
    Then I should see the user created
    Examples:
      | name | email | gender | status |
      #| test | umut@gmail.com | male | active |
      | mustfa | mstfyr@gmail.com | male | active |


    Scenario: Post user again
      When I send a post request with same name "mustfa" , email "mstfyr@gmail.com" , gender "male" , status "active"
      Then I should see same user can not created again