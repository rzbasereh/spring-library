Feature: Test CRUD methods in Author REST API

  Background:
    Given clear database

  Scenario: User adds new author and them we check it to be added successfully
    When user adds "aut1" author with firstname="fName1", lastname="lName1"
    Then the "aut1" author is exist with desired properties

  Scenario: User adds multiple authors and then get all of them
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    Given user adds "aut2" author with firstname="fName2", lastname="lName2"
    Given user adds "aut3" author with firstname="fName3", lastname="lName3"
    When user gets all authors with "auts" result
    Then all the "auts" authors are exist as expected

  Scenario: User gets a author that does not exist and then we check status code
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    Given user deletes the "aut1" author
    When user gets the "aut1" author
    Then user gets an error with status=404

  Scenario: User update name of author and then we check it to be updated correctly
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    When user updates the "aut1" author to firstname="fName2", lastname="lName2"
    Then the "aut1" author is exist with desired properties

  Scenario: User update a author that does not exist and then we check status code
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    Given user deletes the "aut1" author
    When user updates the "aut1" author to firstname="fName2", lastname="lName2"
    Then user gets an error with status=404

  Scenario: User add author and deletes that and we check it to be deleted correctly
    Given user adds "aut1" author with firstname="fName1", lastname="lName1"
    When user deletes the "aut1" author
    Then the "aut1" author is deleted from system