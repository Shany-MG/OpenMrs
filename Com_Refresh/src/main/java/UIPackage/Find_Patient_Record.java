package UIPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.Status;

import BasePackage.Base_Class;
import ReportingPackage.ExtentTestNGReportBuilder;

public class Find_Patient_Record extends Base_Class {

	private static By L_findpatientrecord = By.partialLinkText("Find Patient Record");
	private static By L_filter = By.xpath("//input[@id='patient-search']");
	private static By L_logout = By.partialLinkText("Logout");
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
			Thread.sleep(2000);
			clear(L_filter);
			ExtentTestNGReportBuilder.test.log(Status.PASS, "The following patient record got displayed: "+string);

		}

	}


}
