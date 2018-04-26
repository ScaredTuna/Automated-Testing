package com.qa.cucumber;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ArrayDefFile {

	int[] array = null;
	
	@Given("^an empty integer array of size two$")
	public void newIntegerArraySizeTwo() {
		array = new int[2];
	}
	
	@When("^I add an integer of value two to the first position of the array$")
	public void addTwoToFirstIndex() {
		array[0] = 2;
	}
	
	@Then("^the value of the first position of the array is two$")
	public void firstPositionIsTwo() {
		assertEquals(2, array[0]);
	}
}
