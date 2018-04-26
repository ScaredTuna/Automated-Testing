Feature: stack example
	Simple demo of using Cucumber

Scenario: add item to stack
	Given an empty stack
	When I push an item into the stack
	Then the stack contains one item

Scenario: remove item from stack
	Given a stack containing three items
	When I pop two items from the stack
	And I push an item into the stack
	Then the stack contains two items
	