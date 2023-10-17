package com.cda.dev.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.cda.dev.BasePage.BasePage;

public class LoginPage extends BasePage{
	
	public By helloSignLocator = By.xpath("//span[contains(@class,'nav-line') and contains(text(),'Hello, sign in')]");
	public By Sign_in  = By.xpath("//span[contains(text(),'Hello, s')]");
	public By userNameLocator = By.xpath("//input[contains(@name,'email')]");
	public By continueBtnLocator =  By.xpath("//input[contains(@id,'continue')]");
	public By passwordLocator = By.xpath("//input[contains(@id,'password')]");
	public By submitBtnLocator = By.xpath("//input[contains(@type,'submit')]");
	public By sendOtpLocator = By.xpath("");
	public By inValidUserMessageLocator =By.xpath("//span[contains(text(),'We cannot find an account')]");
	public By inValidPasswordMessageLocator = By.xpath("//span[contains(text(),'Your password is incorrect')]");
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToMenu(WebElement menu) {

		Actions action = new Actions(driver);
		try {
			if (menu.isDisplayed()) {
				action.moveToElement(menu).build().perform();
				menu.click();
			} else {
				System.out.println("Unable to Navigate Signpage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Sign In is displayed");
		
	}
	
	public void loginApplication(String username,String password) {
		
		WebElement userName = driver.findElement(userNameLocator);
		WebElement continueBtn = driver.findElement(continueBtnLocator);
		
		if(userName.isDisplayed()) {
			userName.sendKeys(username);
			continueBtn.click();
		}
		else {
			System.out.println("Invalid userName entered is--->"+username);
			WebElement incorrectUserMess = driver.findElement(inValidUserMessageLocator);
			System.out.println(incorrectUserMess.getAttribute("textContent"));
		}
		WebElement passwordBtn = driver.findElement(passwordLocator);
		WebElement submitBtn = driver.findElement(submitBtnLocator);
		
		if(passwordBtn.isDisplayed()) {
			passwordBtn.sendKeys(password);
			submitBtn.click();
		}
		else{
			WebElement incorrectPasswordMess = driver.findElement(inValidPasswordMessageLocator);
			System.out.println("Invalid password entered is--->"+password);
			System.out.println(incorrectPasswordMess.getAttribute("textContent"));
		}
		
	}
}
