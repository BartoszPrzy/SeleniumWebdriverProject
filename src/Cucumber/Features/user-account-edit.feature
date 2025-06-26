@user
Feature: User account edit

  Scenario Outline:
    Given I'm on mystore main page
    When I click sign in
    And I click on create new account
    And I pick gender enter name surname email password birthdate and select checkboxes
    Then I'm redirected to the homepage and logged in
    When I click on my name
    Then I go to add first address
    And I enter alias <alias> address <address> city <city> postal code <postal code> phone <phone>
    Then I can see new address
    And I verify created address
    When I go to the main page
    Then I click on Hummingbird printed sweater
    And I check if there is a discount
    And I choose size "<size>"
    And I set quantity <quantity>
    Then I add it to the cart
    And I click on proceed to checkout
    Then I delete the address
    And I check if address is successfully deleted
    Then I enter new alias <alias> address <address> city <city> postal code <postal code> phone <phone>
    And I choose pick up in-store
    And I choose pay by Check
    And I place order
    Then I take a screenshot of the order
    And I go to order history and details
    And I check if the order has correct amount and status
    And I close the browser
    Examples:
      | alias | address       | city   | postal code | phone       | size | quantity |
      | Home1 | Aleja Łużycka | Poznań | 60-689      | 124-789-852 | L    | 5        |
   #   | Home2 | Barska        | Piła      | 64-920      | 320-568-236 |   |          |
   # Home3 | Bzowa         | Bydgoszcz | 85-008      | 670-852-563 |
   # | Home4 | Ciasna        | Kraków    | 30-003      | 607-520-416 |