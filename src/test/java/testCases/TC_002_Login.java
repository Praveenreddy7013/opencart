package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_002_Login extends BaseClass{
	
	
	@Test(groups={"sanity","master"})
	public void test_Login() {
		
		logger.info("Starting TC_002_Login");
		try {
		
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
		
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		logger.info("clicked on my account");
		hp.clickLogin();
		logger.info("clicked on  login");
		
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(rb.getString("username"));
		logger.info("provided username");
		lp.setPassword(rb.getString("password"));
		logger.info("provided password");
		lp.clickLogin();
		logger.info("clicked on  login");
		
		boolean targetPage=lp.isMyAccountPageExists();
		
		if(targetPage) {
			logger.info("Login success");
			Assert.assertTrue(true);
		}
		else {
			logger.error("Login failed");
			captureScreen(driver,"test_Login");
			Assert.assertTrue(false);
		}
	}
		
		catch(Exception e)
		{
			logger.fatal("Login failed");
			Assert.fail();
		}
		
		logger.info("Finished TC_002_Login");

}
}
