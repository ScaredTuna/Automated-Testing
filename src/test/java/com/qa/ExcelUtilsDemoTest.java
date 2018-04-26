package com.qa;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExcelUtilsDemoTest {

	WebDriver driver;
	ExtentTest testReport;

	@Before
	public void setUp() throws Exception {
		ExcelUtils.setExcelFile(Constants.pathTestData + Constants.fileTestData, 0);
		String browser = ExcelUtils.getCellData(5, 2);
		if(browser.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C://webDevelopment/chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equals("Firefox")){
			System.setProperty("webdriver.gecko.driver", "C://webDevelopment/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if(browser.equals("Edge")){
			System.setProperty("webdriver.edge.driver", "C://webDevelopment/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		} else if(browser.equals("IE")){
			System.setProperty("webdriver.ie.driver", "C://webDevelopment/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		
		ExtentReports report = new ExtentReports("C://webDevelopment//ExcelUtilsDemoTestReport.html", true);
		
		testReport = report.startTest("Excel Data Test");
		
		testReport.log(LogStatus.INFO, "Opening Browser");
		
		Thread.sleep(1000);
		
		driver.get(Constants.websiteURL);
		
		testReport.log(LogStatus.INFO,"Navigated to thedemosite.co.uk");
		
		
		testReport.log(LogStatus.INFO, "Set up Excel Utils path - Opened file stream");
		
		driver.get(Constants.registerURL);
		testReport.log(LogStatus.INFO, "Clicked on link to addUser page");
		WebElement addUser = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
		addUser.click();
		addUser.sendKeys(ExcelUtils.getCellData(1, 0));
		testReport.log(LogStatus.INFO, "Input Username");
		WebElement addPass = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
		addPass.click();
		addPass.sendKeys(ExcelUtils.getCellData(1, 1));
		testReport.log(LogStatus.INFO,  "Input password");
		WebElement save = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
		save.click();
		testReport.log(LogStatus.INFO,  "Created New User");
		
		driver.get(Constants.loginURL);
		testReport.log(LogStatus.INFO, "Navigated to login page");
		WebElement username = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
		username.click();
		username.sendKeys(ExcelUtils.getCellData(1, 0));
		testReport.log(LogStatus.INFO, "Input Username");
		WebElement password = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
		password.click();
		password.sendKeys(ExcelUtils.getCellData(1, 1));
		testReport.log(LogStatus.INFO,  "Input password");
		WebElement loginButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
		loginButton.click();
		testReport.log(LogStatus.INFO, "Logged In");
		
		
		Thread.sleep(1000);
	
		String title = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText();
		String expected = "**Successful Login**";
		//String expected = "dfghj";
		
		if(!title.equals(expected)) {
			testReport.log(LogStatus.FAIL, "Demo Site Login Test");
			report.endTest(testReport);
			report.flush();
			ExcelUtils.setCellData("Fail", 1, 3);
		}
		
		assertEquals(expected, title);
		
		ExcelUtils.setCellData("Pass", 1, 3);
		testReport.log(LogStatus.PASS, "Demo Site Login Test");
		report.endTest(testReport);
		report.flush();
	}

}
