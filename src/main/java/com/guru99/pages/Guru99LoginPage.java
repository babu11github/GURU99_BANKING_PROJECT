package com.guru99.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guru99.qa.base.TestBase;



public class Guru99LoginPage extends TestBase{

	//OR
	@FindBy(name="uid")
	WebElement UserId;
	
	@FindBy(name="password")
	WebElement PassWord;
	
	@FindBy(name="btnLogin")
	WebElement LoginBtton;
	
	@FindBy(name="btnReset")
	WebElement ResetButton;
	
	@FindBy(className="barone")
	WebElement Guru99Text;
	
	//Initializing the Page Objects:
	public Guru99LoginPage()
	{
		PageFactory.initElements(driver,this);
	}
	

	//Actions
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	public boolean validateGuru99Bankmage(){
		return Guru99Text.isDisplayed();
	}

	public Guru99HomePage login(String un, String pwd){
		UserId.sendKeys(un);
		PassWord.sendKeys(pwd);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", LoginBtton);
    	
    	return new Guru99HomePage();
}
}
