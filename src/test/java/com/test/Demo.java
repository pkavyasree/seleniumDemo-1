package com.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Demo {

	private static WebDriver driver;
	private static String appUrl = "http://3.128.25.199:8080/JavaServletLogin/";

	
	public void driverInit() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\lib\\chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver=new ChromeDriver(options);
		driver.get(appUrl);
	}
	
	public void launchApp() {
		driver.get(appUrl);
		driver.manage().window().maximize();
	}
	
	public void login(String userName,String password) {
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("btn")).submit();
	}
	
	public String getURL() {
		return driver.getCurrentUrl();
	}

	public void loginToApp(String userName,String password) {
		driverInit();
		launchApp();
		login(userName, password);
		Assert.assertTrue("Login not successful", getURL().contains("sample.jsp"));
	}
	
	
	@Test
	public void loginSuccessCase() {
		loginToApp("admin", "admin");
	}
	
	@Test
	public void loginFailCase() {
		loginToApp("admin", " ");
	}
	
	@After
	public void closeApp() {
		driver.quit();
	}
	

}
