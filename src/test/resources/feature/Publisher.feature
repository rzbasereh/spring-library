Feature: Test CRUD methods in Publisher REST API

  Scenario: Create a publisher
    When user adds "pub1" with name="pubName1"
    Then the "pub1" is exist with desired properties

  Scenario: Update a publisher
    Given user adds "pub1" with name="pubName1"
    When user updates "pub1" to name="pubName2"
    Then the "pub1" is exist with desired properties

  Scenario: Delete a publisher
    Given user adds "pub1" with name="pubName1"
    When user deletes "pub1"
    Then the "pub1" is deleted from system