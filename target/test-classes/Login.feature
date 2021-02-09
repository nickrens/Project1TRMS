Feature: The Login page works as desired

	Scenario: Login attempt with incorrect email and password
		Given The user is on the login page
		When The user types in the incorrect email and password and cliks login
		Then The user should be on the same page and see a message

	Scenario: Login attempt with correct email and password
		Given The user is on the login page
		When The user types in correct email and password and clicks login
		Then The user should be on the employee page