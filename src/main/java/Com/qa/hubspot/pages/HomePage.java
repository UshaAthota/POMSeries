package Com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Com.qa.hubspot.base.BasePage;
import Com.qa.hubspot.utils.Constants;
import Com.qa.hubspot.utils.ElementUtil;
import io.qameta.allure.Step;

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	 
	By header=By.xpath("//h1[text()='Service Dashboard']");
	By contacts=By.className("primary-link");
	
	By contactsLinkPrimary=By.id("nav-primary-contacts-branch");
	By contactsLinSecondary=By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		
		elementUtil=new ElementUtil(driver);
		
	}
	@Step("get Home Page Title....")
	public String getHomePageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
		
	}
	
	@Step("get Home Page header....")
	public String verifyServiceDashBoard() {
		if(elementUtil.doIsDisplayed(header)) {
			return elementUtil.dogetText(header);
		}
	    return null;
	}
	
//	public String verifyContactsTab() {
//		if(driver.findElement(contacts).isDisplayed()) {
//			return driver.findElement(contacts).getText();
//		}
//		return null;
//	}
	
	@Step("navigate to contacts page....")
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
		
		}
	
	
	private void clickOnContacts() {
		elementUtil.waitForElementToBePresent(contactsLinkPrimary, 10);
		elementUtil.doClick(contactsLinkPrimary);
		elementUtil.waitForElementToBePresent(contactsLinSecondary, 5);
		elementUtil.doClick(contactsLinSecondary);
	}

}
