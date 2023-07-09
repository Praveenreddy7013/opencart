package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	import pageObjects.HomePage;
	import pageObjects.LoginPage;
	import pageObjects.MyAccountPage;
	import testBase.BaseClass;
	import Utilities.ExcelUtility;

	public class TC_003_LoginDataDrivenTest extends BaseClass {

		@Test(dataProvider = "LoginData")
		public void test_LoginDDT(String email, String pwd, String exp) {
			logger.info(" Starting TC_003_LoginDataDrivenTest ");

			try {
				
				driver.get(rb.getString("appURL"));
				logger.info("Home page is displayed");
				
				driver.manage().window().maximize();
				
				HomePage hp = new HomePage(driver);
				hp.clickMyaccount();
				logger.info("clicked on my account");
				hp.clickLogin();
				logger.info("clicked on login");

				LoginPage lp = new LoginPage(driver);
				lp.setUsername(email);
				logger.info("provided email");

				lp.setPassword(pwd);
				logger.info("provided password");

				lp.clickLogin();
				logger.info("clicked on login");

				boolean targetpage=lp.isMyAccountPageExists();

				if (exp.equals("Valid")) {
					
					if (targetpage == true) {
						
						logger.info("login success");
						
						MyAccountPage macc=new MyAccountPage(driver);
						macc.clickLogout();
						Assert.assertTrue(true);
					}
					else
					{
						logger.error("Login failed");
						Assert.assertTrue(false);
					}
				}

				if (exp.equals("Invalid")) {
					
					if (targetpage == true) {
						
						MyAccountPage myaccpage = new MyAccountPage(driver);
						myaccpage.clickLogout();
						Assert.assertTrue(false);
					} 
					else
					{
						logger.error("Login failed");//eventhough login failed my test is passed here
						Assert.assertTrue(true);
					}
				}

			} catch (Exception e)
			{
				logger.fatal("login failed");
				Assert.fail();
			}

			logger.info(" Finished TC_003_LoginDataDrivenTest");

		}
		//dataprovider method
		@DataProvider(name="LoginData")
		public String [][] getData() throws IOException
		{
			String path=".\\testData\\Opencart_LoginData(2).xlsx";
			
			ExcelUtility xlutil=new ExcelUtility(path);
			
			int totalrows=xlutil.getRowCount("Sheet1");
			int totalcols=xlutil.getCellCount("Sheet1", 1);
			
			String logindata[][]=new String[totalrows][totalcols];
			
			for(int i=1;i<=totalrows;i++) //1
			{
				
				for(int j=0;j<=totalcols;j++)//0
				  {
					
					logindata[i-1][j]= xlutil.getCellData("Sheet1", i, j);  //1,0
					
				}
			}
			return logindata;
			 

	}

}
