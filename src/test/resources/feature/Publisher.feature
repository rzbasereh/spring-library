Feature: Test CRUD methods in Publisher REST API

  Background:
    Given clear database

  Scenario: User adds a new publisher and then checks it must exist
    When user adds the "pub1" publisher with name="pubName1"
    Then the "pub1" publisher exists with name="pubName1"

  Scenario: User adds multiple publishers and then gets all of them
    Given user adds the "pub1" publisher with name="pubName1"
    Given user adds the "pub2" publisher with name="pubName2"
    Given user adds the "pub3" publisher with name="pubName3"
    When user gets all publishers with "pubs" result
    Then all "pubs" publishers exist as expected

  Scenario: User gets a publisher that does not exist and then gets an error
    Given user adds the "pub1" publisher with name="pubName1"
    Given user deletes the "pub1" publisher
    When user gets "pub1" publisher
    Then user gets an error with status=404

  Scenario: User updates name of the existed publisher and then checks it to be updated correctly
    Given user adds the "pub1" publisher with name="pubName1"
    When user updates the "pub1" publisher to name="pubName2"
    Then the "pub1" publisher exists with name="pubName2"

  Scenario: User updates a publisher that does not exist and then gets an error
    Given user adds the "pub1" publisher with name="pubName1"
    Given user deletes the "pub1" publisher
    When user updates the "pub1" publisher to name="pubName2"
    Then user gets an error with status=404

  Scenario: User adds publisher and deletes that and then checks it to be deleted correctly
    Given user adds the "pub1" publisher with name="pubName1"
    When user deletes the "pub1" publisher
    Then the "pub1" publisher does not exist