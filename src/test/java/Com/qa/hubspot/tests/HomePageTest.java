package Com.qa.hubspot.tests;

import java.util.Properties;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Com.qa.hubspot.base.BasePage;
import Com.qa.hubspot.pages.HomePage;
import Com.qa.hubspot.pages.LoginPage;
import Com.qa.hubspot.utils.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 102 : design home page features")
@Feature("US - 105 : design test cases for home page features")

public class HomePageTest {
	
	Properties prop;
	WebDriver driver;
	
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	
	
	@BeforeTest
	
	public void setup(){
		basePage=new BasePage();
		prop=basePage.inti_prop();
		driver=basePage.init_driver(prop);
		loginPage=new LoginPage(driver);
		homePage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

     }
	
	@Test(priority=1)
	@Description("verify home Page Title Test....")
    @Severity(SeverityLevel.NORMAL)
	public void verifyHomePageTitleTest() {
		String title= homePage.getHomePageTitle();
		System.out.println("home page title is :"+ title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	@Description("verify home Page header Test....")
    @Severity(SeverityLevel.NORMAL)
	
	public void verifyHomePageHeaderTest() {
		String header=homePage.verifyServiceDashBoard();
		System.out.println("Home page header is :"+ header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
		
	}
	
	
	
	
	
    @AfterTest
	
	public void tearDown() {
		driver.quit();
		
	}
	
		
}
	
