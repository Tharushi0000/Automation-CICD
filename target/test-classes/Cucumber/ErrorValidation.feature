@tag
Feature: Error Validation 
 

 @ErrorValidation
 Scenario Outline: Possitive test of submiting the order
    Given I landed on Ecomerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

   Examples:
	|name			|password | 
	|Tw@gmail.com	|Tw@1234  | 