package UIPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePackage.Base_Class;

public class Register_a_Patient extends Base_Class{
	
	
	private static By L_Registerpatient = By.partialLinkText("Register a patient");
	private static By L_firstname = By.xpath("//input[@name='givenName']");
	private static By L_middlename = By.xpath("//input[@name='middleName']");
	private static By L_familyname = By.xpath("//input[@name='familyName']");
	private static By L_next = By.xpath("//button[@id='next-button']");
	
	private static By L_birthdate = By.xpath("//input[@name='birthdateDay']");
    private static By L_birthyear = By.xpath("//input[@name='birthdateYear']");
    
    private static By L_address1 = By.xpath("//input[@id='address1']");
    private static By L_address2 = By.xpath("//input[@id='address2']");
    private static By L_city = By.xpath(" //input[@id='cityVillage']");
    private static By L_state = By.xpath("//input[@id='stateProvince']");
    private static By L_country = By.xpath("//input[@id='country']");
    private static By L_postalcode = By.xpath("//input[@id='postalCode']");
    
    private static By L_phone = By.xpath("//input[@name='phoneNumber']");
    
    private static By L_addnewrelation = By.xpath("//a[@ng-click='addNewRelationship()']");
    
    private static By L_submit = By.xpath("//input[@id='submit']");
    
    private static By L_logout = By.partialLinkText("Logout");
    
    //div[@class='float-sm-right']//span
  //  (//div[@class='float-sm-right']//em[text()='Patient ID']//following::span)[1]
    
	public void registerpatient() throws InterruptedException {
		
		click(L_Registerpatient);		
	}
	
	public void Name(String firstname, String Middlename, String familyname) throws InterruptedException {
		input(L_firstname, firstname);
		input(L_middlename, Middlename);
		input(L_familyname, familyname);
		click(L_next);
	}
	
	public void Gender( String gender) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='"+gender+"']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+gender+"']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='"+gender+"']"))).click();
		
		click(L_next);
		
	}
	
	public void birthdate(String date, String year, String month) throws InterruptedException {
		
		input(L_birthdate, date);
		input(L_birthyear, year);
		
		Select objSelect = new Select(driver.findElement(By.xpath("//select[@id='birthdateMonth-field']")));	
		objSelect.selectByVisibleText(month);	
		click(L_next);
	}
	
	public void address(String address1, String adress2, String city, String state, String Country, String postal) throws InterruptedException {
		
		input(L_address1, address1);
		input(L_address2, adress2);
		input(L_city, city);
		input(L_state, state);
		input(L_country, Country);
		input(L_postalcode, postal);
		click(L_next);
	}
	
	public void phonenumber(String phone) throws InterruptedException {
		input(L_phone, phone);
		click(L_next);
	}
	
	public void relatives(String relationtype, String personname) throws InterruptedException {
		
	String[] relation_type =relationtype.split(",");
	String[] person_name=  personname.split(",");
	
	int i=1;
	
	for (String string : relation_type) {
		
		Select objSelect = new Select(driver.findElement(By.xpath("(//select[@id='relationship_type'])["+i+"]")));	
		objSelect.selectByVisibleText(string);
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='Person Name'])["+i+"]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='Person Name'])["+i+"]"))).sendKeys(person_name);

		click(L_addnewrelation);
		
		i++;	
	}
	click(L_next);		
	}
	
	
	
	public void submit() throws InterruptedException {
		click(L_submit);
	}
	
	
	public void logout() throws InterruptedException {
		click(L_logout);
	}

}
