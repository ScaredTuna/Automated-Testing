package com.qa.cucumber;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StackDefFile {
	
	Stack<Object> stack = null;
	
	@Given("^an empty stack$")
	public void newStack() {
		stack = new Stack<Object>();
	}
	
	@Given("^a stack containing three items$")
	public void newStackWithThreeItems() {
		stack = new Stack<Object>();
		Object obj = new Object();
		stack.push(obj);
		stack.push(obj);
		stack.push(obj);
	}
	
	@When("^I push an item into the stack$")
	public void pushToStack() {
		Object obj = new Object();
		stack.push(obj);
	}
	
	@When("^I pop two items from the stack$")
	public void popTwoFromStack() {
		stack.pop();
		stack.pop();
	}
	
	@Then("^the stack contains one item$")
	public void containsOneItem() {
		assertEquals(1, stack.size());
	}
	
	@Then("^the stack contains two items$")
	public void containsTwoItems() {
		assertEquals(2, stack.size());
	}
}
