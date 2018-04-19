package com.guru99.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

//import com.guru99.qa.util.Log4jTest;
import com.guru99.qa.util.TestUtil;
import com.guru99.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	//static Log4jTest log=new Log4jTest();
	public TestBase(){
		
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\MY\\eclipse-workspace\\GURU99_BANKING_PROJECT\\src\\main\\java\\com\\guru99\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "D:\\BABU\\DRIVERS\\chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(Platform.WINDOWS);
			System.setProperty("webdriver.gecko.driver", "D:\\BABU\\DRIVERS\\geckodriver.exe");
			driver = new FirefoxDriver(capabilities);
			

			/*System.setProperty("webdriver.gecko.driver", "D:\\BABU\\DRIVERS\\geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
*/
			/*System.setProperty("webdriver.gecko.driver", "D:\\BABU\\DRIVERS\\geckodriver.exe");	
			driver = new FirefoxDriver(); */
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		//log.info("url of the page"+prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
	
	}
	
	
	
	
	
	
	
	

}
