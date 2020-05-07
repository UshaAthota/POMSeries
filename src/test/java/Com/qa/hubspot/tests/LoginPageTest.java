package Com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Com.qa.hubspot.base.BasePage;
import Com.qa.hubspot.pages.LoginPage;
import Com.qa.hubspot.utils.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

//pre condition --> Test --> ac vs exp --> post step
//@BeforeTest -->   @Test --> Assert   --> @AfterTest
//browser, url --> test google title --> Google vs Google --> close browser


@Epic("Epic - 101 : design login feature")
@Feature("US - 105 : design test cases for login page feature")
public class LoginPageTest {
	
	Properties prop;
	WebDriver driver;
	
	BasePage basePage;
	LoginPage loginPage;
	
	
	@BeforeTest
	
	public void setup(){
		basePage=new BasePage();
		prop=basePage.inti_prop();
		driver=basePage.init_driver(prop);
		loginPage=new LoginPage(driver);
		
		
	}
    
	@Test(priority=1)//if we write enabled=false that particular test case is not going to execute
	@Description("verify login page title test..")
	@Severity(SeverityLevel.NORMAL)
	
	public void verifyLoginPageTitleTest() {
		String title=loginPage.getLoginPageTitile();
		System.out.println("login page title is : "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE );
		
		
	}
	
	@Test(priority=2,enabled=true)
	@Description("verify signup link test..")
	@Severity(SeverityLevel.CRITICAL)
	
	public void verifySignUpLinkTest() {
		boolean flag=loginPage.checkSignupLink();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	@Description("verify user is able to login-feature test..")
	@Severity(SeverityLevel.BLOCKER)
	
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
//	
//	@Test(priority=3)
//	
//	public void verifyShowPasswordTest() {
//		Assert.assertTrue(loginPage.checkShowPassword());
//	}
//	
//	@Test(priority=4)
//	
//	public void verifyLoginWithSSO() {
//		loginPage.checkLoginWithSsoBTN();
//	}
	
	@AfterTest
	
	public void tearDown() {
		driver.quit();
		
	}
	
}
