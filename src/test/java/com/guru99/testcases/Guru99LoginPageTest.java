package com.guru99.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.guru99.pages.Guru99HomePage;
import com.guru99.pages.Guru99LoginPage;
import com.guru99.qa.base.TestBase;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;
public class Guru99LoginPageTest extends TestBase{
	Guru99LoginPage loginPage;
	Guru99HomePage homePage;
	
	public Guru99LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new Guru99LoginPage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Guru99 Bank Home Page");
	}
	
	@Test(priority=2)
	public void Guru99LogoImageTest(){
		boolean flag = loginPage.validateGuru99Bankmage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest(){
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
