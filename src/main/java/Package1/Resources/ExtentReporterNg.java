package Package1.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {
	
	
	public static  ExtentReports getReportObject()
	{
		String path =System.getProperty("user.dir")+ "//reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		//To fix report names document names
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Tharushi Weerasekara");
		return extent;
		
		//extent.createTest(path);
		
		
	}

}
