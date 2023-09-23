Feature: Test CRUD methods in Book REST API

  Background:
    Given clear database
    And user adds the "p1" publisher with name="pubName1"
    And user adds the "p2" publisher with name="pubName2"
    And user adds "a1" author with firstname="fName1", lastname="lName1"
    And user adds "a2" author with firstname="fName2", lastname="lName2"

  Scenario: User adds a new book and then checks it must exist
    When user adds "b1" book with name="name1", publisher="p1", authors=a1, a2
    Then the "b1" book exists with name="name1", publisher="p1", authors=a1, a2

  Scenario: User adds multiple books and then gets all of them
    Given user adds "b1" book with name="name1", publisher="p1", authors=a1, a2
    Given user adds "b2" book with name="name2", publisher="p2", authors=a1, a2
    Given user adds "b3" book with name="name3", publisher="p1", authors=a1
    When user gets all books with "books" result
    Then all the "books" books exist as expected

  Scenario: User gets a book that does not exist and then gets an error
    Given user adds "b1" book with name="name1", publisher="p1", authors=a1, a2
    And user deletes the "b1" book
    When user gets the "b1" book
    Then user gets an error with status=404

  Scenario: User update a book and then checks it to be updated correctly
    Given user adds "b1" book with name="name1", publisher="p1", authors=a1, a2
    When user updates the "b1" book to name="name2", publisher="p2", authors=a1
    Then the "b1" book exists with name="name2", publisher="p2", authors=a1

  Scenario: User updates a book that does not exist and then gets an error
    Given user adds "b1" book with name="name1", publisher="p1", authors=a1, a2
    And user deletes the "b1" book
    When user updates the "b1" book to name="name2", publisher="p2", authors=a1
    Then user gets an error with status=404

  Scenario: User adds book and deletes that and checks it to be deleted correctly
    Given user adds "b1" book with name="name1", publisher="p1", authors=a1, a2
    When user deletes the "b1" book
    Then the "b1" book does not exist