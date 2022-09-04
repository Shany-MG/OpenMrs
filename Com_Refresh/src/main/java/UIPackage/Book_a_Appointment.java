package UIPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import BasePackage.Base_Class;
import ReportingPackage.ExtentTestNGReportBuilder;

public class Book_a_Appointment extends Base_Class {
	
	private static By L_findpatientrecord = By.partialLinkText("Find Patient Record");
	private static By L_filter = By.xpath("//input[@id='patient-search']");
	private static By L_logout = By.partialLinkText("Logout");
	private static By L_seacrchclick = By.xpath("//tbody[@role='alert']");
	private static By L_Home = By.xpath("//i[@class='icon-home small']");
	private static By L_bookappointment = By.partialLinkText("Request Appointment");
	private static By L_appointmenttype= By.xpath("//input[@id='appointment-type']");
	private static By L_provider= By.xpath("//textarea[@id='notes']");
	private static By L_notes= By.xpath("//input[@id='provider']");
	private static By L_confirm= By.xpath("//input[@id='save-button']");
	ExtentTestNGReportBuilder ExtentTestNGReportBuilder = new ExtentTestNGReportBuilder();

	public void logout() throws InterruptedException {
		click(L_logout);
	}



	public void findpatientrecord() throws InterruptedException {

		click(L_findpatientrecord);	

	}

	public void bookappointment(String search, String type, String Provider, String Notes) throws InterruptedException {

		String[] value= search.split(",");

		for (String string : value) {

			input(L_filter, string);
			Thread.sleep(2000);
			click(L_seacrchclick);
			click(L_bookappointment);
			input(L_appointmenttype, type+Keys.ENTER);
			Thread.sleep(3000);
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(""+type+"")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(""+type+"")));
			wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(""+type+""))).click();
			
			input(L_provider,Provider);
			input(L_notes, Notes);
			
			click(L_confirm);
			ExtentTestNGReportBuilder.test.log(Status.PASS, "Appointment for the following patient got booked"+string);
			
		}
	}


}
