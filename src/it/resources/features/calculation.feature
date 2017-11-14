Feature: Duplicate Calculation
  As a user
  I want to be able to duplicate calculations to save time (not have to re-enter assets and income streams)

  Scenario: Duplicate Calculation
    Given I login as a valid user
    And I view a user with calculations
    When I duplicate a calculation
    Then I should see the duplicate calculation
