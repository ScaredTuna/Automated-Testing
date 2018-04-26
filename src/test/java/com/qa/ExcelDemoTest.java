package com.qa;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class ExcelDemoTest {
	
	WebDriver driver;
	ExtentTest testReport;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C://webDevelopment/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		
		ExtentReports report = new ExtentReports("C://webDevelopment//ExcelDemoTestReport.html", true);
		
		testReport = report.startTest("Excel Data Test");
		
		testReport.log(LogStatus.INFO, "Opening Browser");
		
		driver.get(Constants.websiteURL);
		
		testReport.log(LogStatus.INFO,"Navigated to thedemosite.co.uk");
		
		FileInputStream file = null;
		try{
			file = new FileInputStream(Constants.pathTestData + Constants.fileTestData);
		} catch(FileNotFoundException e){
			e.printStackTrace();
			testReport.log(LogStatus.FAIL, "Opening File Input Stream");
			report.endTest(testReport);
			report.flush();
		}
		testReport.log(LogStatus.INFO, "Opened File input Stream");
		
		XSSFWorkbook workbook = null;
		try{
			workbook = new XSSFWorkbook(file);
		} catch(IOException e){
			e.printStackTrace();
			testReport.log(LogStatus.FAIL, "Initializing XSSF Workbook");
			report.endTest(testReport);
			report.flush();
		}
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFCell cellUser = sheet.getRow(5).getCell(0);
		XSSFCell cellPass = sheet.getRow(5).getCell(1);
		cellPass.setCellValue("notPass");
		testReport.log(LogStatus.INFO, "Got data from file - Username: " + cellUser.getStringCellValue() + " - Password: " + cellPass.getStringCellValue());
		
		driver.get(Constants.registerURL);
		testReport.log(LogStatus.INFO, "Clicked on link to addUser page");
		WebElement addUser = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
		addUser.click();
		addUser.sendKeys(cellUser.getStringCellValue());
		testReport.log(LogStatus.INFO, "Input Username");
		WebElement addPass = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
		addPass.click();
		addPass.sendKeys(cellPass.getStringCellValue());
		testReport.log(LogStatus.INFO,  "Input password");
		WebElement save = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
		save.click();
		testReport.log(LogStatus.INFO,  "Created New User");
		
		driver.get(Constants.loginURL);
		testReport.log(LogStatus.INFO, "Navigated to login page");
		WebElement username = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
		username.click();
		username.sendKeys(cellUser.getStringCellValue());
		testReport.log(LogStatus.INFO, "Input Username");
		WebElement password = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
		password.click();
		password.sendKeys(cellPass.getStringCellValue());
		testReport.log(LogStatus.INFO, "Password: " + cellPass.getStringCellValue());
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
