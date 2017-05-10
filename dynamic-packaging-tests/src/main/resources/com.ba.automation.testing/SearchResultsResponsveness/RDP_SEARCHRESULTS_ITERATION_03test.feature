Feature: Iteration Three - responsiveness validation for Search results page.
  Description: Validating the responsiveness of the following components in search result page.
  Components covered: Flight Details, Page Title, Page Header, Check-in Check-Out and Standalone Links.

  @application-rdp_Desktop @application-rdp_Tablet
  Scenario: Validate page header responsiveness at large and medium breakpoints
    Given Navigate to home search results page
    Then verify page header displays left margin as 15px pixel and right margin as 15px pixel
    Then verify Home link should display
    Then verify Telephone number appears within the header

    Scenario Outline: Validate page header responsiveness at custom and small breakpoints
    Given Navigate to home search results page
    Then verify page header displays left margin as <pixel left> pixel and right margin as <pixel right> pixel
    Then verify Home icon should display
    Then verify Telephone number NOT appears within the header
    Then verify Telephone number appears bottom of the page
  @application-rdp_Custom
  Examples:
    | pixel left | pixel right |
    | 15px       | 15px        |
  @application-rdp_Mobile
  Examples:
    | pixel left | pixel right |
    | 5px        | 5px         |

  @application-rdp_Desktop @application-rdp_Tablet @application-rdp_Custom
  Scenario: Validate flight details tab appears open on Hotel Information component for large, medium, and custom breakpoint.
    Given Navigate to home search results page
    Then Verify flight details tab is display
    Then Verify flight details tab appears open on Hotel Information component by default
    Then Validate in-bound and out-bound flight details display under flight details tab

  @application-rdp_Mobile
  Scenario:Validate flight details accordion collapsed by default Hotel Information component for small breakpoint.
    Given Navigate to home search results page
    Then Verify flight details accordion appears collapsed by default
    When Click on flight details accordion
    Then Verify flight details accordion expends
    Then Validate in-bound and out-bound flight details display under flight details accordion


    Scenario Outline: Validate on selecting flight details link, expands flight summary view to reveal the full flight details
    Given Navigate to home search results page
    Then Validate Details link displays under flight details for following <break point>
    When Click out-bound details link at <break point> to expand
    Then Verify out-bound flight details summery displays at <break point>
    And Click out-bound details link again at <break point> to collapse
    When Click in-bound details link at <break point> to expand
    Then Verify in-bound flight details summery displays at <break point>
    And Click in-bound details link again at <break point> to collapse
  @application-rdp_Desktop
  Examples:
    | break point |
    | Desktop     |
  @application-rdp_Tablet
  Examples:
    | break point |
    | Tablet      |
  @application-rdp_Custom
  Examples:
    | break point |
    | Custom      |
  @application-rdp_Mobile
  Examples:
    | break point |
    | Mobile      |