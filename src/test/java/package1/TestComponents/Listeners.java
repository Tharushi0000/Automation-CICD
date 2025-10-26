package package1.TestComponents;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Package1.Resources.ExtentReporterNg;


public class Listeners extends BaseTest implements ITestListener{
   ExtentTest test;
	
	ExtentReports extent= ExtentReporterNg.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//Thread Safe
	
	
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test =extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // assign unique thread ID (ErrorValidationTest-->Test)
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		extentTest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
			.get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Take the screenshot and attach it to the report
		String filePath = null;
		try {
		   filePath =getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onFinish(ITestContext Context)
	{
		extent.flush();
	}
	
	
}
