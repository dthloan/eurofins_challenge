@quote_creation
Feature: Quote creation

  @ac1
  Scenario: Successfully create a new quote with one item for a customer
    Given a customer JohnAC1 with a list of items as below
      | item | quantity | price |
      | cake | 10       | 3.2   |
    When I create a quote for that customer
    Then the Api responds with statusCode 200
    And the quoteCreation Api responds with the correct details and total line price calculated correctly

  @ac2
  Scenario:  Successfully create a new quote with one item with discount for a customer
    Given a customer JohnAC2 with a list of items as below
      | item   | quantity | price | discount |
      | cookie | 3        | 1.2   | 0.15     |
    When I create a quote for that customer
    Then the Api responds with statusCode 200
    And the quoteCreation Api responds with the correct details and total line price calculated correctly

  @ac3
  Scenario:  Successfully create a new quote with two item for a customer
    Given a customer JohnAC3 with a list of items as below
      | item   | quantity | price | discount |
      | latte  | 10       | 3     | 0.2      |
      | cookie | 3        | 1.2   | 0.15     |
    When I create a quote for that customer
    Then the Api responds with statusCode 200
    And the quoteCreation Api responds with the correct details and total line price calculated correctly

  @ac4 @ac5
  Scenario Outline:  create a new quote with customer/items field set to null or missing
    Given a customer <customer> with a list of items as below
      | item   | quantity   | price   | discount   |
      | <item> | <quantity> | <price> | <discount> |
    When I create a quote for that customer
    Then the Api responds with statusCode <errorCode>
    And the Api responds with error message '<errorMsg>'
    Examples:
      | customer | item  | quantity | price | discount | errorCode | errorMsg         |
      | null     | latte | 10       | 3     | 2        | 400       | MISSING_CUSTOMER |
      | JohnAC5  |       |          |       |          | 200       | MISSING_ITEMS    |