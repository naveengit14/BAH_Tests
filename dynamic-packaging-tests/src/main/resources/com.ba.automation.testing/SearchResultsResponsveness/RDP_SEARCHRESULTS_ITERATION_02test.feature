Feature: Iteration Two - responsiveness validation for Search results page.
  Description: Validating the responsiveness of the following components in search result page.
  Components covered: Hotel Image Gallery and Pricing Pod.

  Scenario Outline: Validate user able to view the next and previous image by selecting the left and right arrows.
    Given Navigate to home search results page
    Then verify hotel image gallery link on following <screens> and click
    When Click next arrow on image gallery for <screens> breakpoint
    Then User should able to see the next image at <screens> breakpoint
    When Click previous arrow on image gallery for <screens> breakpoint
    Then User should able to see the previous image at <screens> breakpoint
  @application-rdp_Desktop
  Examples:
    | screens |
    | Desktop |
  @application-rdp_Tablet
  Examples:
    | screens |
    | Tablet  |
  @application-rdp_Custom
  Examples:
    | screens |
    | Custom  |
  @application-rdp_Mobile
  Examples:
    | screens |
    | Mobile  |

  @application-rdp_Desktop @application-rdp_Tablet @application-rdp_Custom
  Scenario: Validate user able select and view random images in image gallery
    Given Navigate to home search results page
    Then Click on hotel image gallery link
    Then Select 5 random images and view the selected image reflected in image gallery caption

  @application-rdp_Desktop @application-rdp_Tablet @application-rdp_Custom
  Scenario: Verify Choose and continue button shows loading image within the button while processing
    Given Navigate to home search results page
    When click on Choose and continue button
    Then verify the button shows loading image
