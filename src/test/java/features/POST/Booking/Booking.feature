Feature: Validate booking

  @CreateToken

  Scenario Outline:Create Booking successfully
    Given Create booking with "<firstname>","<lastname>",<totalprice>,"<additionalneeds>"
    When user calls "CreateBooking" with "POST" http request
    Then the API call got status code is <status_code>
    And verify booking_id created maps to lastname "<lastname>" using "GetBooking"

    Examples:
      | firstname | lastname | totalprice | additionalneeds | status_code |
      | Su        | Ran      | 3782       | cooktail+lunch  | 200         |


  Scenario Outline:Validate when creating booking with field firstname
    Given Create booking with "<firstname>","<lastname>",<totalprice>,"<additionalneeds>"
    When user calls "CreateBooking" with "POST" http request
    Then the API call got status code is <status_code>
    And verify booking_id created maps to lastname "<lastname>" using "GetBooking"

    Examples:
      | Testcase          | firstname | lastname | totalprice | additionalneeds | status_code |
      | firstname is null | null      | last1    | 3782       | cooktail+lunch  | 500         |
     | firstname >25 character | qwertyuiopasdfghjklzxcvbnm | last2    | 3782       | cooktail+lunch  | 200         |
      | firstname accented character | Nhâất                      | last3    | 3782       | cooktail+lunch  | 200         |
      | firstname special character  | N!@#$%^&*({}               | last4    | 3782       | cooktail+lunch  | 200         |
      | firstname is blank |           | last5    | 3782       | cooktail+lunch  | 200         |

  Scenario Outline:Validate when creating booking with field lastname
    Given Create booking with "<firstname>","<lastname>",<totalprice>,"<additionalneeds>"
    When user calls "CreateBooking" with "POST" http request
    Then the API call got status code is <status_code>
    And verify booking_id created maps to firstname "<firstname>" using "GetBooking"

    Examples:
      | Testcase          | firstname | lastname | totalprice | additionalneeds | status_code |
      | lastname is blank | first1    |          | 9090       | breakfast       | 200         |
      | lastname >25 character      | first2    | qwertyuiopasdfghjklzxcvbnm | 9090       | breakfast       | 200         |
      | lastname accented character | first3    | Nhâất                      | 9090       | breakfast       | 200         |
      | lastname special character  | first4    | N!@#$%^&*({}               | 9090       | breakfast       | 200         |

  Scenario Outline:Validate when creating booking with field totalprice
    Given Create booking with "<firstname>","<lastname>",<totalprice>,"<additionalneeds>"
    When user calls "CreateBooking" with "POST" http request
    Then the API call got status code is <status_code>


    Examples:
      | Testcase              | firstname | lastname | totalprice | additionalneeds | status_code |
      | totalprice with comas | June      | Junly    | 908        | breakfast       | 400         |
