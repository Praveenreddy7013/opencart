package testBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public WebDriver driver;
	public Logger logger;//for logging
	public ResourceBundle rb;//import from java.util pacakge
	
	@BeforeClass(groups= {"sanity","master","regression"})
	@Parameters({"browser"})
	public void setup(String br) {
		//to load config.properties file
		rb=ResourceBundle.getBundle("config");
		
		
		//for logging
		logger=LogManager.getLogger(this.getClass());//for logging
		
		if(br.equals("chrome"))
		{
	driver=new ChromeDriver();
	logger.info("Launched Chrome Browser");
	}
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
			logger.info("Launched Edge Browser");
		}
		else if(br.equals("firefox"))
		{
			driver=new FirefoxDriver();
			logger.info("Launched Edge Browser");
		}
		
		
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
	}
	
	//to capture screenshots
	
	public void captureScreen(WebDriver driver, String tname) throws IOException{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+ "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
	}
	
	
	
	//to generate random strings dynamically...commons lang dependencies useful here to import this class"RandomStringUtils"
	public String RandomString() {
		
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
		
	}
	
	//to generate random numbers dynamically
		public int RandomNumbers() {
			
			String generatedNumber=RandomStringUtils.randomNumeric(6);
			return (Integer.parseInt(generatedNumber));
			
		}
	
}



