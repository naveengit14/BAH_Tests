Feature: Iteration Four - responsiveness validation for Search results page.
  Description: Validating the responsiveness of the following components in search result page.
  Components covered: Footer and  Warning message.

  @application-rdp_Desktop @application-rdp_Tablet @application-rdp_Custom @application-rdp_Mobile
  Scenario: Validate the footer displays following site links and social icon links
    Given Navigate to home search results page
    Then Verify the footer displays following site links in footer
      | Help and contacts           |
      | Accessibility and site help |
      | Privacy policy              |
      | Legal                       |
      | About BA                    |
      | Media Centre                |
      | Careers                     |
      | Corporate Responsibility    |
      | Iberia com                  |
    Then Verify the following social icon links in footer
      | instagram |
      | facebook  |
      | twitter   |
      | google    |
      | linkedin  |

  @application-rdp_Desktop @application-rdp_Tablet @application-rdp_Custom @application-rdp_Mobile
  Scenario: Validate warning and information messages in search results page.
    Given Navigate to home search results page
    Then validate the warning message should show following text
      | Sorry, we are not able to find your accommodation. Please see below for other properties in this destination. |
    Then validate information message should show following texts
      | Your results are based on flights to and from Tampa Airport. Change to fly to Miami International Airport.   |
      | Sorry, we are not able to find your accommodation. Please see below for other properties in this destination |