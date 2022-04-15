Feature: VideoGame DataBase of Swagger domain

@skip
Scenario: In Swagger DB, user ar able to prform Different HTTP Method GET, POST, PUT DELETE
    Given I am on Swagger portal to get all video games
    When I added new videoGame in database 
    Then I verify with response message
    Then I update a record on specific ID 
    Then verify it has updated with a response message 
    Then I delete any record from database 
    Then verify the record has deleted with response message
 