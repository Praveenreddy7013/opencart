package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.exec.OS;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//will return date&Time in string format
		repName="Test-Report-"+timeStamp+".html";//Test-Report-2022.06.12.11.06.34
	
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
	    
		sparkReporter.config().setDocumentTitle("opencart Automation Report");//title of report
		sparkReporter.config().setReportName("opencart functional Testing");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Frontend");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Operating system", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "praveen");
	
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed" );
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed" );
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		try {
			String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";//we can use any extension supporting for images like .jpg etc..
			test.addScreenCaptureFromPath(screenshotPath);
	        }
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName()); 
	    test.log(Status.SKIP, "Test Skipped");
	    test.log(Status.SKIP, result.getThrowable().getMessage());
	
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	

}

