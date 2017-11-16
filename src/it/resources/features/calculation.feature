Feature: Duplicate Calculation
  As a user
  I want to be able to duplicate calculations to save time (not have to re-enter assets and income streams)

  @wip
  Scenario: Create Calculation
    Given I login as a valid user
    And I view a client
    When I create a new calculation
    Then I should see the results of the calculation
    And I should see the new calculation in the list of calculations

  Scenario: Duplicate Calculation
    Given I login as a valid user
    And I view a client with calculations
    When I duplicate a calculation
    Then I should see the duplicate calculation in the list of calculations

  Scenario: Delete Calculation
    Given I login as a valid user
    And I view a client with calculations
    When I delete a calculation
    Then I should no longer see the calculation in the list of calculations