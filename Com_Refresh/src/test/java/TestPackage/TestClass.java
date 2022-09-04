package TestPackage;

import java.io.IOException;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.sun.tools.sjavac.Log;

import BasePackage.Base_Class;
import ReportingPackage.ExtentTestNGReportBuilder;
import UIPackage.Book_a_Appointment;
import UIPackage.Delete_a_Patient;
import UIPackage.Find_Patient_Record;
import UIPackage.Register_a_Patient;
import UIPackage.View_The_Patient;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.htmlunit.corejs.javascript.ast.SwitchCase;

public class TestClass extends ExtentTestNGReportBuilder {

	Base_Class baseclass = new Base_Class();
	DataDrivenPackage.ExcelReader ExcelReader = new DataDrivenPackage.ExcelReader("Google");
	Register_a_Patient registerapatient = new Register_a_Patient();
	Find_Patient_Record findpatient = new Find_Patient_Record();
	View_The_Patient viewpatient = new View_The_Patient();
    Delete_a_Patient delete = new Delete_a_Patient();
    Book_a_Appointment appointment = new Book_a_Appointment();

	@Test(dataProvider = "TestData")
	public void launch(Map <Object,Object> testdata) throws IOException, InterruptedException  {	
	
		
		if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		
		switch (testdata.get("TestScenario").toString()) {

		case "TC_OpenMrs_Register_a_Patient":
			loggerPackage.Log.info("Stated to Execute the Test Scenario: "+testdata.get("TestScenario").toString());
			
			createtests(testdata.get("TestScenario").toString());
			test.assignCategory(testdata.get("TestCategory").toString());
			screenshotname("TC_OpenMrs_Register_a_Patient");	
			
			baseclass.setup();	
			
			test.log(Status.PASS, "Browser Launched and initialized sucessfully");
                
			registerapatient.registerpatient();
			test.log(Status.PASS, "Clicked on Register a Patient");
			registerapatient.Name(testdata.get("FirstName").toString(), 
					testdata.get("MiddleName").toString(), 
					testdata.get("FamilyName").toString());
			test.log(Status.PASS, "Details on patient name got added");
			registerapatient.Gender(testdata.get("Gender").toString());
			registerapatient.birthdate(testdata.get("Day").toString(), 
					testdata.get("Year").toString(), 
					testdata.get("Month").toString());
			test.log(Status.PASS, "Details on Patient date of birth got added");
			registerapatient.address(testdata.get("Address1").toString(), 
					testdata.get("Adddress2").toString(), 
					testdata.get("City").toString(), 
					testdata.get("State").toString(), 
					testdata.get("Country").toString(), 
					testdata.get("Postalcode").toString());
			test.log(Status.PASS, "Details on patient address got added");
			registerapatient.phonenumber(testdata.get("Phonenumber").toString());
			test.log(Status.PASS, "Details on Patient Phone number got added");
			registerapatient.relatives(testdata.get("Relatives_Type").toString(), 
					testdata.get("Relatives_Name").toString());
			test.log(Status.PASS, "Details on Patient relatives number got added");
			registerapatient.submit();
			test.log(Status.PASS, "Patient Details got created sucessfully");
			registerapatient.logout();
			test.log(Status.PASS, "Application got logged out");
			
			break;

		case "TC_OpenMrs_Find_a_Patient":
			loggerPackage.Log.info("Stated to Execute the Test Scenario: "+testdata.get("TestScenario").toString());
			
			createtests(testdata.get("TestScenario").toString());
			test.assignCategory(testdata.get("TestCategory").toString());
			screenshotname("TC_OpenMrs_Find_a_Patient");	
			
			baseclass.setup();	
			test.log(Status.PASS, "Browser Launched and initialized sucessfully");
			findpatient.findpatientrecord();
			test.log(Status.PASS, "View a patient got clicked");
			findpatient.search(testdata.get("FirstName").toString());
			findpatient.logout();
			test.log(Status.PASS, "Application got logged out");
			break;
			
		case "TC_OpenMrs_View_a_Patient":
			loggerPackage.Log.info("Stated to Execute the Test Scenario: "+testdata.get("TestScenario").toString());
			
			createtests(testdata.get("TestScenario").toString());
			test.assignCategory(testdata.get("TestCategory").toString());
			screenshotname("TC_OpenMrs_View_a_Patient");	
			
			baseclass.setup();		
			test.log(Status.PASS, "Browser Launched and initialized sucessfully");
			viewpatient.findpatientrecord();
			test.log(Status.PASS, "View a patient got clicked");
			viewpatient.search(testdata.get("FirstName").toString());
			viewpatient.logout();
			test.log(Status.PASS, "Application got logged out");
			break;
			
		case "TC_OpenMrs_Delete_a_Patient":
			loggerPackage.Log.info("Stated to Execute the Test Scenario: "+testdata.get("TestScenario").toString());
			
			createtests(testdata.get("TestScenario").toString());
			test.assignCategory(testdata.get("TestCategory").toString());
			screenshotname("TC_OpenMrs_Delete_a_Patient");	
			
			baseclass.setup();	
			test.log(Status.PASS, "Browser Launched and initialized sucessfully");
			delete.findpatientrecord();
			test.log(Status.PASS, "View a patient got clicked");
			delete.delete(testdata.get("FirstName").toString());
			delete.logout();
			test.log(Status.PASS, "Application got logged out");
			break;
			
		case "TC_OpenMrs_Book a Appointment":
			loggerPackage.Log.info("Stated to Execute the Test Scenario: "+testdata.get("TestScenario").toString());
			
			createtests(testdata.get("TestScenario").toString());
			test.assignCategory(testdata.get("TestCategory").toString());
			screenshotname("TC_OpenMrs_Book a Appointment");	
			
			baseclass.setup();	
			test.log(Status.PASS, "Browser Launched and initialized sucessfully");
			appointment.findpatientrecord();
			appointment.bookappointment(testdata.get("FirstName").toString(), 
					testdata.get("Appoiintment Type").toString(), 
					testdata.get("Provider").toString(), 
					testdata.get("Notes").toString());
			test.log(Status.PASS, "Appointment for the patient got created");
			appointment.logout();
			test.log(Status.PASS, "Application got logged out");
			break;
			
			

		default:
			System.err.println("The Driver is not defined");
		}
		
		baseclass.driver.quit();
		test.log(Status.PASS, "Driver got closed sucessfully");
		loggerPackage.Log.info("Test Execution completed for Test Scenario: "+testdata.get("TestScenario").toString());
		
		}
	}

	
	@DataProvider(name = "TestData")
	public static Object[][] gettestdate() throws IOException{

		Object[][] objectarry=null;
		java.util.List<Map<String,String>> completedata=DataDrivenPackage.ExcelReader.getdata();

		objectarry=new Object[completedata.size()][1];

		for(int i=0;i<completedata.size();i++) {
			objectarry[i] [0]= completedata.get(i);
		}
		return objectarry;

	}


}
