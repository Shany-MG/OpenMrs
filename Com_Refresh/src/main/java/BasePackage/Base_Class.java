package BasePackage;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import io.github.bonigarcia.wdm.WebDriverManager;
import loggerPackage.Log;

public class Base_Class {

	public static RemoteWebDriver driver = null;
	
	private static By L_username = By.xpath("//input[@id='username']");
	private static By L_password = By.xpath("//input[@id='password']");
	private static By L_Location = By.xpath("//li[@id='Inpatient Ward']");
	private static By L_Submit = By.xpath("//input[@id='loginButton']");
	public static String Pagetitle;

	public static Properties configloader() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties properties = new Properties();
		properties.load(File);
		return properties;		
	}

	public  void setup() throws IOException, InterruptedException {

		String Browser = configloader().getProperty("Browser");
		String Url = configloader().getProperty("URL");
		String UserName = configloader().getProperty("UserName");
		String Password = configloader().getProperty("Password");
		String Location = configloader().getProperty("Password");

		switch (Browser.toUpperCase()) {

		case "CHROME":

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			driver = new ChromeDriver(options);			
			break;

		case "FIREFOX":

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();			
			break;
			
			

		default:
			System.err.println("The Driver is not defined");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		Log.info("Driver has initialized successful for "+Browser+"browser");
		driver.get(Url);		
		

		Thread.sleep(3000);
		
		input(L_username, UserName);
		input(L_password, Password);
		click(L_Location);
		click(L_Submit);


	}


	public static  void input(By element, String Value) throws InterruptedException {


		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(Value);


	}

	public static  void click(By element) throws InterruptedException {

		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		//Thread.sleep(2000);

	}
	
	public static  void INclick(By element) throws InterruptedException {

		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		//Thread.sleep(2000);

	}

	public void select(String value,By element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();

	}

	public static void clear(By element)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
		//Thread.sleep(2000);
	}


	public static void AcceptAlert()
	{
		driver.switchTo().alert().accept();
	}

	public static void SwitchToFrame(By frameName)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}
	
	public static void ScrollUntilElementVisible(By locator)
	{ 
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

}
