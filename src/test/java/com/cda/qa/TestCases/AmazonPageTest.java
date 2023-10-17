package com.cda.qa.TestCases;

import java.time.Duration;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cda.dev.BasePage.BasePage;
import com.cda.dev.PageObjects.AmazonPage;
import com.cda.dev.PageObjects.LoginPage;

public class AmazonPageTest extends BasePage{
		
	LoginPage loginpage = new LoginPage();
	AmazonPage amazonpage = new AmazonPage();
	
	public AmazonPageTest(){
		super();
	}

	@BeforeMethod
	public void setUp() {
		
		driver = openBrowser();		
	}
	
	@Test
	public void amazonTestCase() {
		
		loginpage.navigateToMenu(driver.findElement(loginpage.Sign_in));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		loginpage.loginApplication(prop.getProperty("username") , prop.getProperty("password"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String actual = driver.getTitle();
		Assert.assertEquals( actual, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
		amazonpage.selectOptionByVisibleText(driver.findElement(amazonpage.selectSearchBtn), prop.getProperty("selectvalue"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Assert.assertEquals(prop.getProperty("selectvalue"),"Electronics");
		driver.findElement(amazonpage.enterValue).sendKeys(prop.getProperty("searchFor"));
		driver.findElement(amazonpage.submitBtnLocator).click();
		amazonpage.selectOptionByVisibleText(driver.findElement(amazonpage.sortBtn), prop.getProperty("sortByFeature"));
		String parentWindownId = driver.getWindowHandle();
		ArrayList<String> handleList = amazonpage.getMobileList(parentWindownId);
		amazonpage.switchToWindowAndAddtoCard(handleList,parentWindownId);
 		ArrayList<Integer> priceList = amazonpage.StringListToIntegerList(handleList);
 		Assert.assertEquals(handleList,priceList);
		
	}
		
	@AfterMethod
	public void tearDown() {
			
			driver.quit();
	}
	
	
}
