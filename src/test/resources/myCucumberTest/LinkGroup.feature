Feature: Check for Searched Results
    Check for Searched Results
Scenario: Smoke test
    When I open the home page
    Then the page is displayed
Scenario: Search results
    Given I have opened the home page
    And I have agreed to the cookie policy
    When I search for "Leeds"
    Then the search results are displayed for "Leeds"
    
Scenario Outline: Investment managers
    Given I have opened the Fund Solutions page
    When I view Funds
    Then I can select the investment managers for "<Jurisdiction>" investors
Examples:
    | Jurisdiction |
    | UK |
    | Irish |
    | Swiss |