Feature: Test CRUD methods in Publisher REST API

  Scenario: Create a publisher
  	When user create "pub1" with name "pubName1"
    Then "pub1" is exist

  Scenario: Update a publisher
    Given user create "pub1" with name "pubName1"
    When user change "pub1" name to "pubName2"
    Then name of "pub1" is "pubName2"

  Scenario: Delete a publisher
    Given user create "pub1" with name "pubName1"
    When user delete "pub1"
