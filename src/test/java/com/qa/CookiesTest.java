package com.qa;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CookiesTest {
	
	boolean pass = true;
	WebDriver driver;
	ExtentTest testReport;
	ExtentReports report;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C://webDevelopment/chromedriver.exe");
		driver = new ChromeDriver();
		report = new ExtentReports("C://webDevelopment/CookiesTestReport.html", true);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void cookieTest() {
		
		login();
		createCookie();
		logout();
		
		driver.get("https://www.google.co.uk");
		
		loadCookie();
		
		assertTrue(pass);
	}

	private void login() {
		
		testReport = report.startTest("Login test");
		
		testReport.log(LogStatus.INFO, "Open browser and navigate to www.bbc.co.uk");
		driver.get(Constants.cookieUrl);
		
		testReport.log(LogStatus.INFO, "Navigate to login page");
		driver.findElement(By.xpath("//*[@id=\"idcta-link\"]")).click();
		
		testReport.log(LogStatus.INFO, "Input sign in details");
		driver.findElement(By.xpath("//*[@id=\"user-identifier-input\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"password-input\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
		
		testReport.log(LogStatus.INFO, "Attempted login");
		
		checkLogin();
		
		report.endTest(testReport);
		report.flush();
	}

	private void checkLogin() {
		
		driver.findElement(By.xpath("//*[@id=\"idcta-link\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"idcta-link\"]")).click();
		
		String bodyText = driver.findElement(By.tagName("body")).getText();
		
		testReport.log(LogStatus.INFO, "Check Login Success");
		if(bodyText.contains("Your account")) {
			testReport.log(LogStatus.PASS, "Login valid");
		} else {
			testReport.log(LogStatus.FAIL, "Login failed");
			pass = false;
		}
	}

	private void createCookie() {
		
		File f;
		BufferedWriter writer = null;
		
		try {
			f = new File("browser.data");
			f.delete();
			f.createNewFile();
			writer = new BufferedWriter(new FileWriter(f));
			
			for(Cookie cookie : driver.manage().getCookies()) {
				writer.write(cookie.getName() + "," + cookie.getValue() + "," + cookie.getDomain() + "," + cookie.getPath() + ","
						+ cookie.getExpiry() + "," + cookie.isSecure());
				writer.newLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer != null) {
					writer.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void logout() {
		
		testReport = report.startTest("Logout Test");
		
		driver.findElement(By.xpath("//*[@id=\"idcta-link\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"orb-modules\"]/div[1]/div/div/div[1]/div[1]/div/div/div/div/nav/ul/li[3]/a")).click();
		
		testReport.log(LogStatus.INFO, "Attempted logout");
		
		checkLogout();
		
		report.endTest(testReport);
		report.flush();
	}

	private void checkLogout() {
		
		String check = driver.findElement(By.xpath("//*[@id=\"idcta-username\"]")).getText();
		
		testReport.log(LogStatus.INFO, "Check Logout Success");
		if(check.equalsIgnoreCase("Sign in")){
			testReport.log(LogStatus.PASS, "Logout Successful");
		} else {
			testReport.log(LogStatus.FAIL, "Logout Failed");
			pass = false;
		}
	}

	private void loadCookie() {
		
		testReport = report.startTest("Load Cookie Test");
		
		BufferedReader reader = null;
		
		try {
			testReport.log(LogStatus.INFO, "Initialise File Reader");
			File f = new File("browser.data");
			reader = new BufferedReader(new FileReader(f));
			String line;
			
			testReport.log(LogStatus.INFO, "Get Cookie Data");
			while((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				String name = data[0];
				String value = data[1];
				String domain = data[2];
				String path = data[3];
				Date expiry = null;
				String dt;
				
				if(!(dt = data[4]).equals("null")) {
					expiry = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(dt);
				}
				
				boolean isSecure = new Boolean(data[5]).booleanValue();
				Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
				driver.manage().addCookie(cookie);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) {
					reader.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		testReport.log(LogStatus.INFO, "Attempted to login with cookie");
		
		driver.get(Constants.cookieUrl);
		checkLogin();
		
		report.endTest(testReport);
		report.flush();
	}

}
