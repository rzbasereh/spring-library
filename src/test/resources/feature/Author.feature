Feature: Test CRUD methods in Author REST API

  Background:
    Given clear database

  Scenario: User adds a new author and then checks that it must exist
    When user adds "aut1" author with firstname="fName1", lastname="lName1"
    Then the "aut1" author exists with firstname="fName1", lastname="lName1"

  Scenario: User adds multiple authors and then gets all of them
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    Given user adds "aut2" author with firstname="fName2", lastname="lName2"
    Given user adds "aut3" author with firstname="fName3", lastname="lName3"
    When user gets all authors with "auts" result
    Then all the "auts" authors exist as expected

  Scenario: User gets an author that does not exist and then gets an error
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    Given user deletes the "aut1" author
    When user gets the "aut1" author
    Then user gets an error with status=404

  Scenario: User update name of the existed author and then checks that it to be updated correctly
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    When user updates the "aut1" author to firstname="fName2", lastname="lName2"
    Then the "aut1" author exists with firstname="fName2", lastname="lName2"

  Scenario: User updates an author that does not exist and then gets an error
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    Given user deletes the "aut1" author
    When user updates the "aut1" author to firstname="fName2", lastname="lName2"
    Then user gets an error with status=404

  Scenario: User adds an author and deletes that and checks it to be deleted correctly
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    When user deletes the "aut1" author
    Then the "aut1" author does not exist