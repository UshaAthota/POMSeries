package Com.qa.hubspot.utils;

    import java.util.ArrayList;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class ElementUtil {
		WebDriver driver;
		

		public ElementUtil(WebDriver driver) {
			this.driver=driver;
		}
		
		public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		return element;
		}
		
		public void doSendKeys(By locator,String value) {
			getElement(locator).sendKeys(value);
		}
		
		public void doActionsSenKeys(By locator,String value) {
			Actions actions=new Actions(driver);
			WebElement element=getElement(locator);
			actions.sendKeys(element, value).build().perform();
			
		}
		
		public void doClick(By locator) {
			getElement(locator).click();
		}
		
		public void doActionsClick(By locator) {
			Actions actions=new Actions(driver);
			WebElement element=getElement(locator);
			actions.click(element).build().perform();
		}
		
		public String dogetText(By locator) {
			return getElement(locator).getText();
		}
		
		public boolean doIsDisplayed(By locator) {
			return getElement(locator).isDisplayed();
		}
		
		/**
		 * This method is used to get total values and all the values from dropdown
		 * @param locator
		 */
		
		public  void getAllvaluesfromdropdown(By locator) {
			Select select=new Select(getElement(locator));
			List<WebElement> optionsList=select.getOptions();
			System.out.println("total values in the dropdown is :"+optionsList.size());
			for(int i=0;i<optionsList.size();i++) {
			   System.out.println(optionsList.get(i).getText());
		   }
	    }
		
		/**
		 * This method is used to select dropdown values by visibleText in Selenium
		 * @param locator
		 * @param value
		 */
		
		public void selectDropdown(By locator,String value) {
			Select select=new Select(getElement(locator));
			select.selectByVisibleText(value);
		}
		
		/**
		 * This method is used to select drop down values by index in Selenium
		 * @param locator
		 * @param index
		 */
			
		public void selectDropdown(By locator,int index) {
			Select select=new Select(getElement(locator));
			select.selectByIndex(index);
			
		}
		
		/**
		 * This method is used to get total values in the drop down and select patricular value from drop down
		 * @param locator
		 * @param value
		 */
		
		public  void SelectDropDownValue(By locator,String value) {
			Select select=new Select(getElement(locator));
			
			List<WebElement> valuesList=select.getOptions();
			System.out.println(valuesList.size());
		    for(int i=0;i<valuesList.size(); i++) {
		    	if(valuesList.get(i).getText().equals(value)) {
		    	valuesList.get(i).click();
		    	break;
		    
		      }
	        }
	     }
		/**
		 * This method is used to get all the drop down values list
		 * @param locator
		 * @return
		 */
		
		public  List<String> getDropDownvaluesList(By locator) {
			List<String> ar=new ArrayList<String>();
			
			Select select=new Select(getElement(locator));
			List<WebElement> optionsList=select.getOptions();
			for(int i=0;i<optionsList.size();i++) {
				ar.add(optionsList.get(i).getText());
				
			}
			return ar;
			
		}
		
		/**
		 * this method is user to select the value from the drop down-with out using Select class in selenium 
		 * @param locator
		 * @param locatorValue
		 * @param value
		 */
		
		public  void doSelectValueFromDropDownWithoutSelect(String locator,String locatorValue,String value) {
			List<WebElement> valuesList=null;
			if(locator.equals("xpath")) {
			 valuesList= driver.findElements(By.xpath(locatorValue));
			}
			else if(locator.equals("css")) {
			 valuesList= driver.findElements(By.cssSelector(locatorValue));
				
			}
	        System.out.println(valuesList.size());
	        for(int i=0;i<valuesList.size();i++) {
	      	 if(valuesList.get(i).getText().equals(value)) {
	      		 valuesList.get(i).click();
	      		 break;
	      	 }
	       }
		
		}
		
		
		//wait custom method:wait utilities
		
		public void visibilityOfAllElements(List<WebElement> elements,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			
			
		}
		
		public WebElement waitForElementToBePresent(By locator,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return getElement(locator);
		}
		
		public WebElement waitForElementToBeClickable(By locator,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return getElement(locator);
		}
		
		public WebElement waitForElementToBeVisible(By locator,int timeout) {
			WebElement element=getElement(locator);
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element;
		}
		
		public WebElement waitForElementToBeVisibilityLocated(By locator,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return getElement(locator);
		}
		
		public String waitForUrl(String url,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.urlContains(url));
			return driver.getCurrentUrl();
		}
		
		public boolean waitForAlerttoBePresent(By locator,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
			
		}
		
		public void clickWhenReady(By locator,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			getElement(locator).click();
			
		}
		
		
		public String waitForTitleToBePresent(String title,int timeout) {
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.titleContains(title));
			return driver.getTitle();
		}
		
		

	


}
