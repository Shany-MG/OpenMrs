package ReportingPackage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.github.dockerjava.transport.DockerHttpClient.Request.Method;

import BasePackage.Base_Class;

public class ExtentTestNGReportBuilder {


	public static ExtentReports extent;
	DateTimeFormatter datetime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	String reportname = "AutomationReport"+datetime+".html";
	static String screenshot ;
	public static ExtentTest  test ;
	static String destination;
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.createInstance();
	}



	@AfterMethod
	public  void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			loggerPackage.Log.error("Test execution got failed due to error: "+result.getThrowable().getMessage());
			
			ExtentTestNGReportBuilder.getScreenshot(Base_Class.driver, screenshot);
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(destination).build());		
			//BasePackage.Base_Class.driver.quit();
			
		}
			
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		//below line is just to append the date format with the screenshot name to avoid duplicate names
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		//Returns the captured file path
		return destination;
	}
	
	public static String screenshotname(String screenshotnames) {
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		screenshot = screenshotnames+"_"+dateName;
		return screenshot;
		
	}
	
	public static void createtests(String name) {
		
		test = extent.createTest(name);
		
	}
	

	@AfterSuite
	public synchronized void aftersuite() {

		extent.flush();
	}


}


