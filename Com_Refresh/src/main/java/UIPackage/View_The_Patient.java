package UIPackage;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import BasePackage.Base_Class;
import ReportingPackage.ExtentTestNGReportBuilder;

public class View_The_Patient extends Base_Class {
	

	private static By L_findpatientrecord = By.partialLinkText("Find Patient Record");
	private static By L_filter = By.xpath("//input[@id='patient-search']");
	private static By L_logout = By.partialLinkText("Logout");
	private static By L_seacrchclick = By.xpath("//tbody[@role='alert']");
	private static By L_Home = By.xpath("//i[@class='icon-home small']");
	ExtentTestNGReportBuilder ExtentTestNGReportBuilder = new ExtentTestNGReportBuilder();
	
	public void logout() throws InterruptedException {
		click(L_logout);
	}



	public void findpatientrecord() throws InterruptedException {

		click(L_findpatientrecord);	

	}

	public void search(String search) throws InterruptedException {

		String[] value= search.split(",");

		for (String string : value) {

			input(L_filter, string);
			ExtentTestNGReportBuilder.test.log(Status.PASS, "The following patient record got displayed"+string);
			Thread.sleep(2000);
			click(L_seacrchclick);
			ExtentTestNGReportBuilder.test.log(Status.PASS, "The following patient record got viewed: "+string);
			click(L_Home);
			ExtentTestNGReportBuilder.test.log(Status.PASS, "Home icon got clicked");
			click(L_findpatientrecord);	
			
			
		}
	}


}
