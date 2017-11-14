Feature: Client
  As a user
  I want to be able to create, view, edit and delete clients

  Scenario: Delete Client
    Given I login as a valid user
    And I view the list of clients
    When I delete a client
    Then I should no longer see the client listed

  Scenario: Add Single Client
    Given I login as a valid user
    And I view the list of clients
    When I add a new single client
    Then I should see the client listed