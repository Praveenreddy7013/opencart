package testCases;

import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass {
	
	@Test(groups= {"regression","master"})
	public void test_account_Registration() throws IOException
	{
		try {
		logger.info("TC_001_AccountRegistration is started");
		driver.get(rb.getString("appURL"));//this will get value from prop file dynamically
		logger.info("Homepage is displayed");
		driver.manage().window().maximize();
		
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		logger.info("clicked on my account");
		hp.clickRegister();
		logger.info("clicked on my register");
		
		
		AccountRegistrationPage ap=new AccountRegistrationPage(driver);
		ap.setFirstname("praveen");
		logger.info("provided firstname");
		ap.setLastname("kumar");
		ap.setGmail(RandomString()+"gmail.com");//uhtyi@gmail.com
		ap.setPassword("Praveen1");
		ap.selectCheckbox();
		ap.clickOnContinue();
		boolean targetpage=ap.ConfirmationMsg();
		
		if(targetpage) {
			logger.info("Registration is successful");
			Assert.assertTrue(true);
		   }
		   else
		   {
		    logger.error("Registration is failed");
		    Assert.assertTrue(false);
			   
		   }
		
		
		}
		catch(Exception e)
		{
			logger.error("Account registration failed");
			captureScreen(driver,"test_account_Registration");
			Assert.fail();
		
		}
		logger.info("TC_001_AccountRegistration is completed");
	}
}