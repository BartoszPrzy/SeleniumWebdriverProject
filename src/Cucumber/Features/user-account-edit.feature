@user
Feature: User account edit

  Scenario Outline:
    Given I'm on mystore main page
    When I click sign in
    And I click on create new account
    And I pick gender enter name surname email password birthdate and select checkboxes
    Then I'm redirected to the homepage and logged in
    And I click on my name
    Then I go to add first address
    And I enter alias <alias> address <address> city <city> postal code <postal code> phone <phone>
    Then I can see new address
    And I verify created address
    And I close the browser
    Examples:
      | alias | address       | city      | postal code | phone       |
      | Home1 | Aleja Łużycka | Poznań    | 60-689      | 124-789-852 |
      | Home2 | Barska        | Piła      | 64-920      | 320-568-236 |
      | Home3 | Bzowa         | Bydgoszcz | 85-008      | 670-852-563 |
      | Home4 | Ciasna        | Kraków    | 30-003      | 607-520-416 |