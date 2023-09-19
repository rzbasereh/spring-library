Feature: Test CRUD methods in Publisher REST API

  Background:
    Given clear database

  Scenario: User adds new publisher and them we check it to be added successfully
    When user adds "pub1" with name="pubName1"
    Then the "pub1" is exist with desired properties

  Scenario: User adds multiple publishers and then get all of them
    Given user adds "pub1" with name="pubName1"
    Given user adds "pub2" with name="pubName2"
    Given user adds "pub3" with name="pubName3"
    When user gets all publishers with "pubs" result
    Then all "pubs" publishers are exist as expected

  Scenario: User gets a publisher that does not exist and then we check status code
    Given user adds "pub1" with name="pubName1"
    Given user deletes "pub1"
    When user gets "pub1" publisher
    Then user gets an error with status=404

  Scenario: User update name of publisher and then we check it to be updated correctly
    Given user adds "pub1" with name="pubName1"
    When user updates "pub1" to name="pubName2"
    Then the "pub1" is exist with desired properties

  Scenario: User update a publisher that does not exist and then we check status code
    Given user adds "pub1" with name="pubName1"
    Given user deletes "pub1"
    When user updates "pub1" to name="pubName2"
    Then user gets an error with status=404

  Scenario: User add publisher and deletes that and we check it to be deleted correctly
    Given user adds "pub1" with name="pubName1"
    When user deletes "pub1"
    Then the "pub1" is deleted from system