Feature: Application Login

  Scenario: Homepage default login
    Given User is on net banking login page
    When User login into app with "Jin" and Pwd "1234"
    Then Home page is populated
    And cards are displayed
    
    Scenario: Homepage default login
    Given User is on net banking login page
    When User login into app with "John" and Pwd "4321"
    Then Home page is populated
    And cards are displayed