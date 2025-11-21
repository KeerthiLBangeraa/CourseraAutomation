Feature: Coursera Automation Scenarios
 
  Scenario: Search beginner web development courses
    Given User is on Coursera homepage
    When User searches for beginner web development courses in English
    Then Display first two course names, hours and ratings
 
  Scenario: Extract languages and levels
    Given User is on Coursera homepage
    When User searches for language learning courses
    And User clicks on the Language and Level filter dropdowns
    Then Extract and display all languages and levels with count
 
  Scenario: Fill form with invalid email
    Given User is on Coursera homepage
    And User navigates to Coursera for Enterprise Campus form
    When User fills the form with invalid email
    Then Capture and display the error message
 