Feature: Test CRUD methods in Sample Publisher REST API testing

#  Background:
#    Given a database
#    And a publisher named "pubName1"
#    And a publisher named "pubName2"

  Scenario: client makes call to POST /publishers
    When I try to create "pub1" with name "pub-name"
    Then I should get publisher in response