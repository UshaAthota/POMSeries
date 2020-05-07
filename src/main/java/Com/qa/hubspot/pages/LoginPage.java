package Com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Com.qa.hubspot.base.BasePage;
import Com.qa.hubspot.utils.Constants;
import Com.qa.hubspot.utils.ElementUtil;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//1.By locators
	
	By username=By.id("username");
	By password=By.id("password");
	By loginButton=By.id("loginBtn");
	By signUpLink=By.linkText("Sign up1");
	By showPassword=By.id("password-help");
	By ssoBTN=By.id("ssoBtn");
	
	//2.constructor of the page class
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		
		elementUtil=new ElementUtil(driver);
	
	}
	
	//3.page actions/methods
	
	@Step("get login page title...")
	public String getLoginPageTitile() {
		return elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("check sign up link on login page...")
	public boolean checkSignupLink() {
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	@Step("login with - username : {0} and pasword : {1}")
	public HomePage doLogin(String un,String pwd) {
	
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
	}
	
//	public boolean checkShowPassword() {
//		return elementUtil.doIsDisplayed(showPassword);
//	}
//	
//	public boolean checkLoginWithSsoBTN() {
//		return elementUtil.doIsDisplayed(ssoBTN);
//	}

}
