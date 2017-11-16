Feature: Client
  As a user
  I want to be able to create, view, edit and delete clients

  Scenario: Create Single Client
    Given I login as a valid user
    And I view the list of clients
    When I add a new single client
    Then I should see the client in the list of clients

  Scenario: Create Couple
    Given I login as a valid user
    And I view the list of clients
    When I add a new couple
    Then I should see both clients in the list of clients

  Scenario: Delete Client
    Given I login as a valid user
    And I view the list of clients
    When I delete a client
    Then I should no longer see the client in the list of clients

  Scenario: Delete Couple
    Given I login as a valid user
    And I view the list of clients
    When I delete a client who has a partner
    Then I should no longer see either client in the list of clients
