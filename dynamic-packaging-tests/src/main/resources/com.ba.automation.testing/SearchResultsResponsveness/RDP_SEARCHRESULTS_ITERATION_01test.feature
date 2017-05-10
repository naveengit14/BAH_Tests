Feature: Iteration One - responsiveness validation for Search results page.
  Description: Validating the responsiveness of the following components in search result page.
  Components covered: Progress Bar, Sort BY and Twirly.

  Scenario Outline: Validate margin top and bottom of progress bar
    Given Navigate to home search results page
    Then verify progress bar margin differs with <padding top> top
    Then verify progress bar margin differs with <padding bottom> bottom

  @application-rdp_Desktop @application-rdp_Tablet @application-rdp_Custom
  Examples:
    | padding top | padding bottom |
    | 20px       | 40px            |

  @application-rdp_Mobile
  Examples:
    | padding top | padding bottom |
    | 10px       | 20px          |

  @application-rdp_Desktop @application-rdp_Tablet @application-rdp_Custom
  Scenario: validate Sort By options displays and on clicking following values should display
    Given Navigate to home search results page
    Then verify Sort By drop-down options displays
    When clicking on Sort By drop-down, displays following options
      | We recommend      |
      | Price low to high |
      | Star rating       |
      | Hotel name (A-Z)  |

  @application-rdp_Desktop @application-rdp_Tablet @application-rdp_Custom @application-rdp_Mobile
  Scenario: validate Twirly displays in clicking any option in Sort By drop down
    Given Navigate to home search results page
    When click Sort By drop-down and Select Price low to high option
    Then verify the Twirly displays
    When click Sort By drop-down and Select Star rating option
    Then verify the Twirly displays
    When click Sort By drop-down and Select Hotel name (A-Z) option
    Then verify the Twirly displays
    When click Sort By drop-down and Select We recommend option
    Then verify the Twirly displays