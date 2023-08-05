Feature: RSA Login API

Scenario Outline: Login API
    Given RSA login API "<email>" "<password"
    When User calls "login" API with POST http request
    Then the call got success with status code "200"
    Then "message" in response body is "Login Successfully"
    

Example: 
			|email|            |password|
			|nov9@yopmail.com| |Asdfg1@34|    