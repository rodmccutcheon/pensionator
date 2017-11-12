Feature: Client
  As a user
  I want to be able to add new clients and edit existing clients

  Scenario: Add Single Client
    Given I login in as a valid user
    When I add a new single client
    Then I should see the client listed

  Scenario: Add Couple
    Given I login in as a valid user
    When I add a new couple
    Then I should see the client listed