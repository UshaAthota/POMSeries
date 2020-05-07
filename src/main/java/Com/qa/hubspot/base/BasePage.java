package Com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import Com.qa.hubspot.utils.OptionsManager;
import Com.qa.hubspot.utils.TimeUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	OptionsManager  optionsManager;
	
	public static ThreadLocal<WebDriver>tlDriver=new ThreadLocal<WebDriver>();
	
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is used to initialise the driver on the basis of given browser
	 * @param browser
	 * @return driver
	 */
	
	public WebDriver init_driver(Properties prop) {
		String browser=prop.getProperty("browser");
		System.out.println("browser name is "+ browser);
		
		optionsManager=new OptionsManager(prop);
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browser.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("please pass the correct browser "+ browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		getDriver().get(prop.getProperty("url"));
		TimeUtil.mediumWait();
		
		
		return getDriver();
	}
	
	/**
	 * This method is used to init/load the properties fromconfig file
	 * @return prop
	 */
	
	public Properties inti_prop() {
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("./src/main/java/Com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		return prop;
	}
		
		
		/**

		 * take screenshot util

		 */



		public String getScreenshot() {

			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";

			File destination = new File(path);



			try {

				FileUtils.copyFile(src, destination);

			} catch (IOException e) {

				e.printStackTrace();

			}



			return path;
	}
	

}
