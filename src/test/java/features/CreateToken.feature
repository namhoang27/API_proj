Feature: Create Token

  @CreateToken

  Scenario:Create token
    Given Create token with "admin" and "password123"
    When user calls "CreateToken" with "POST" http request
    Then the API call got success with status code 200



