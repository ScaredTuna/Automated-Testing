package com.qa;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WebDriverTest {

	WebDriver driver;
	ExtentTest testReport;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "C:\\webDevelopment\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void demoSiteLoginTest() {
		
		ExtentReports report = new ExtentReports("C://webDevelopment//demoSiteLoginReport.html", true);
		
		testReport = report.startTest("Demo Site Login Test");
		
		testReport.log(LogStatus.INFO, "Browser started");
		
		driver.get("http://thedemosite.co.uk/");
		testReport.log(LogStatus.INFO,  "Navigated to www.thedemosite.co.uk");
		
		WebElement addUserPage = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]"));
		addUserPage.click();
		testReport.log(LogStatus.INFO, "Clicked on link to addUser page");
		WebElement addUser = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
		addUser.click();
		addUser.sendKeys("User");
		testReport.log(LogStatus.INFO, "Input Username");
		WebElement addPass = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
		addPass.click();
		addPass.sendKeys("pass");
		testReport.log(LogStatus.INFO,  "Input password");
		WebElement save = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
		save.click();
		testReport.log(LogStatus.INFO,  "Created New User");
		
		WebElement loginPage = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
		loginPage.click();
		testReport.log(LogStatus.INFO, "Navigated to login page");
		WebElement username = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
		username.click();
		username.sendKeys("User");
		testReport.log(LogStatus.INFO, "Input Username");
		WebElement password = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
		password.click();
		password.sendKeys("pass");
		testReport.log(LogStatus.INFO,  "Input password");
		WebElement loginButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
		loginButton.click();
		testReport.log(LogStatus.INFO, "Logged In");
		
		String title = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText();
		String expected = "**Successful Login**";
		//String expected = "dfghj";
		
		if(!title.equals(expected)) {
			testReport.log(LogStatus.FAIL, "Demo Site Login Test");
			report.endTest(testReport);
			report.flush();
			driver.quit();
		}
		
		assertEquals(title, expected);
		
		testReport.log(LogStatus.PASS, "Demo Site Login Test");
		report.endTest(testReport);
		report.flush();
		driver.quit();
	}

}
