Feature: Test CRUD methods in Publisher REST API

  Scenario: Create a publisher
  	When sending "POST" to "/publishers" with
  	  | name |
  	  | pub1 |
    Then the HTTP status code should be 200
    And the HTTP response body should be
      | id | name |
  	  | 1  | pub1 |

  Scenario: Retrieve all publisher
  	When sending "GET" to "/publishers"
    Then the HTTP status code should be 200

  Scenario: Retrieve a publisher
  	When sending "GET" to "/publishers/1"
    Then the HTTP status code should be 200
    
  Scenario: Update a publisher
  	When sending "PUT" to "/publishers/1" with
      | id | name |
      | 1  | pub2 |
    Then the HTTP status code should be 200
    And the HTTP response body should be
      | id | name |
  	  | 1  | pub2 |

  Scenario: Delete a publisher
  	When sending "DELETE" to "/publishers/1"
    Then the HTTP status code should be 200