package com.qa;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class demoqaDraggableTest {
	
	WebDriver driver;
	ExtentTest testReport;
	Actions action;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C://webDevelopment/chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		ExtentReports report = new ExtentReports("C://webDevelopment/demoqaDraggableTestReport.html", true);
		testReport = report.startTest("demoqa.com Draggable Test");
		testReport.log(LogStatus.INFO, "Opening Browser");
		
		driver.get(Constants.mouseActionsUrl);
		
		testReport.log(LogStatus.INFO,"On page demoqa.com");
		testReport.log(LogStatus.INFO, "Navigate to draggable page");
		driver.findElement(By.xpath("//*[@id='menu-item-140']/a")).click();
		testReport.log(LogStatus.INFO, "On page demoqa.com/draggable/");
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Select and drag box");
		WebElement element = driver.findElement(By.xpath("//*[@id='draggable']"));
		int xPosition = element.getLocation().getX();
		int yPosition = element.getLocation().getY();
		action.dragAndDropBy(element, 200, 100).perform();
		Thread.sleep(1000);
		action.dragAndDropBy(element, -50, 100).perform();
		Thread.sleep(1000);
		if(xPosition + 150 == element.getLocation().getX() && yPosition + 200 == element.getLocation().getY()){
			testReport.log(LogStatus.PASS, "Completed default functionality task successfully");
		} else {
			testReport.log(LogStatus.FAIL, "Failed to complete default functionality task correctly");
		}
		assertEquals(xPosition + 150, element.getLocation().getX());
		assertEquals(yPosition + 200, element.getLocation().getY());
		
		testReport.log(LogStatus.INFO, "Change task to constrain movement");
		driver.findElement(By.xpath("//*[@id='ui-id-2']")).click();
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Drag vertical only box");
		element = driver.findElement(By.xpath("//*[@id='draggabl']"));
		xPosition = element.getLocation().getX();
		yPosition = element.getLocation().getY();
		action.dragAndDropBy(element, 100, 100).perform();
		if(xPosition != element.getLocation().getX() || yPosition + 100 != element.getLocation().getY()){
			testReport.log(LogStatus.FAIL, "Failed to complete constrain movement task correctly");
		}
		assertEquals(xPosition, element.getLocation().getX());
		assertEquals(yPosition + 100, element.getLocation().getY());
		Thread.sleep(1000);
		
		
		testReport.log(LogStatus.INFO, "Drag horizontal only box");
		element = driver.findElement(By.xpath("//*[@id='draggabl2']"));
		xPosition = element.getLocation().getX();
		yPosition = element.getLocation().getY();
		action.dragAndDropBy(element, 100, 100).perform();
		if(xPosition + 100 != element.getLocation().getX() || yPosition != element.getLocation().getY()){
			testReport.log(LogStatus.FAIL, "Failed to complete constrain movement task correctly");
		}
		assertEquals(xPosition + 100, element.getLocation().getX());
		assertEquals(yPosition, element.getLocation().getY());
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Drag contained in box box");
		element = driver.findElement(By.xpath("//*[@id='draggabl3']"));
		action.dragAndDropBy(element, 200, 1000).perform();
		Thread.sleep(1000);
		action.dragAndDropBy(element, -800, -500).perform();
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Drag contained in parent box");
		element = driver.findElement(By.xpath("//*[@id='draggabl5']"));
		action.dragAndDropBy(element, 500, 500).perform();
		Thread.sleep(1000);
		testReport.log(LogStatus.PASS, "Completed constrain movement task successfully");
		
		testReport.log(LogStatus.INFO, "Change task to cursor style");
		driver.findElement(By.xpath("//*[@id='ui-id-3']")).click();
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Drag relative to center box");
		element = driver.findElement(By.xpath("//*[@id='drag']"));
		action.dragAndDropBy(element, 0, 150).perform();
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Drag cursor at top left box");
		element = driver.findElement(By.xpath("//*[@id='drag2']"));
		action.dragAndDropBy(element, 0, 150).perform();
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Drag cursor at center bottom box");
		element = driver.findElement(By.xpath("//*[@id='drag3']"));
		action.dragAndDropBy(element, 0, 150).perform();
		testReport.log(LogStatus.PASS, "Completed cursor style task");
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Change task to events");
		driver.findElement(By.xpath("//*[@id='ui-id-4']")).click();
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Drag event box");
		element = driver.findElement(By.xpath("//*[@id='dragevent']"));
		xPosition = element.getLocation().getX();
		yPosition = element.getLocation().getY();
		action.clickAndHold(element).moveByOffset(100, 100).release(element).perform();
		if(xPosition + 100 != element.getLocation().getX() || yPosition + 100 != element.getLocation().getY()){
			testReport.log(LogStatus.FAIL, "Failed to complete events task correctly");
		}
		assertEquals(xPosition + 100, element.getLocation().getX());
;		assertEquals(yPosition + 100, element.getLocation().getY());
		Thread.sleep(1500);
		action.clickAndHold(element).moveByOffset(-50, -50).release(element).perform();
		if(xPosition + 50 != element.getLocation().getX() || yPosition + 50 != element.getLocation().getY()){
			testReport.log(LogStatus.FAIL, "Failed to complete events task correctly");
		}
		assertEquals(xPosition + 50, element.getLocation().getX());
		assertEquals(yPosition + 50, element.getLocation().getY());
		Thread.sleep(1500);
		testReport.log(LogStatus.PASS, "Completed events task successfully");

		testReport.log(LogStatus.INFO, "Change task to Draggable + Sortable");
		driver.findElement(By.xpath("//*[@id='ui-id-5']")).click();
		Thread.sleep(1000);
		
		testReport.log(LogStatus.INFO, "Drag list draggable item");
		element = driver.findElement(By.xpath("//*[@id='draggablebox']"));
		action.dragAndDropBy(element, 0, 50).perform();
		testReport.log(LogStatus.PASS, "Completed draggable and sortable task");
		Thread.sleep(1000);
		
		testReport.log(LogStatus.PASS, "Finished all tasks in draggable");
		report.endTest(testReport);
		report.flush();
	}

}
