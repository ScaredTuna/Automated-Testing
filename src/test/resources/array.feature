Feature: array example
	basic cucumber demo with arrays
	
Scenario: add to an array
	Given an empty integer array of size two
	When I add an integer of value two to the first position of the array
	Then the value of the first position of the array is two
	