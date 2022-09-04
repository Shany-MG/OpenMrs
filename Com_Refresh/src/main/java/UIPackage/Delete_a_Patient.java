package UIPackage;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import BasePackage.Base_Class;
import ReportingPackage.ExtentTestNGReportBuilder;

public class Delete_a_Patient extends Base_Class{
	
	private static By L_findpatientrecord = By.partialLinkText("Find Patient Record");
	private static By L_filter = By.xpath("//input[@id='patient-search']");
	private static By L_logout = By.partialLinkText("Logout");
	private static By L_seacrchclick = By.xpath("//tbody[@role='alert']");
	private static By L_Home = By.xpath("//i[@class='icon-home small']");
	private static By L_deletepatient = By.partialLinkText("Delete Patient");
	private static By L_deletereason = By.xpath("//input[@id='delete-reason']");
	private static By L_deleteconfirm = By.xpath("(//button[@class='confirm right'])[6]");
	
	ExtentTestNGReportBuilder ExtentTestNGReportBuilder = new ExtentTestNGReportBuilder();
	
	public void logout() throws InterruptedException {
		click(L_logout);
	}



	public void findpatientrecord() throws InterruptedException {

		click(L_findpatientrecord);	

	}

	public void delete(String search) throws InterruptedException {

		String[] value= search.split(",");

		for (String string : value) {

			input(L_filter, string);
			Thread.sleep(2000);
			click(L_seacrchclick);
			click(L_deletepatient);
			input(L_deletereason, "No Longer Needed");
			click(L_deleteconfirm);
			ExtentTestNGReportBuilder.test.log(Status.PASS, "The following patient record got deleted: "+string);
			Thread.sleep(3000);
			
		}
	}

}
