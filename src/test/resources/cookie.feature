Feature: cookie login test
	Cucumber demo using the automated cookie login test for bbc website
	
Scenario: login with cookie
	Given the login details for my bbc account
	When logged in the accounts cookie is saved
	Then when the cookie is loaded it logs into my account
	