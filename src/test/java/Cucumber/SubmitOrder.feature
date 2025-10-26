@tag
Feature: Purchase the order from Ecormmerce Website
 
 Background:
 Given I landed on Ecomerce Page
 
 @Regression
 Scenario Outline: Possitive test of submiting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

   Examples:
	|name			|password | productName|
	|Tw@gmail.com	|Tw@12345 | ZARA COAT 3|