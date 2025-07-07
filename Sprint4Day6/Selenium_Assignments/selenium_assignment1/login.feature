Feature: User Login

  Scenario: Valid user login
    Given User is on the login page
    When User enters valid username and password
    And User clicks on login button
    Then User should be redirected to the dashboard

  Scenario Outline: Invalid login attempts
    Given User is on the login page
    When User enters "<username>" and "<password>"
    And User clicks on login button
    Then Login error message should be displayed

    Examples:
      | username    | password  |
      | invalidUser | wrongPass |
      | test123     | pass123   |
