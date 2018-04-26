package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class demoqaRegisterTest {
	
	WebDriver driver;
	ExtentTest testReport;
	Actions action;
	ExtentReports report;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C://webDevelopment/chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		report = new ExtentReports("C://webDevelopment/demoqaRegisterTestReport.html", true);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		
		testReport = report.startTest("demoqa.com registration test");
		
		testReport.log(LogStatus.INFO, "Opening Browser");
		driver.get(Constants.mouseActionsUrl);
		testReport.log(LogStatus.INFO, "On page demoqa.com");
		
		testReport.log(LogStatus.INFO, "Navigate to Registration page");
		driver.findElement(By.xpath("//*[@id='menu-item-374']/a")).click();
		testReport.log(LogStatus.INFO, "On page demoqa.com/registration/");
		
		
	}

}
