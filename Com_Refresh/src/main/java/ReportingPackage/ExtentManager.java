package ReportingPackage;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentManager {


	private static SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_HHmmss");  
	private static Date date= new Date();
	public static ExtentReports extent;
	private static String reportFileName = "TestExecutionReport_"+formatter.format(date)+".html";
	private static String reportFilepath = System.getProperty("user.dir") + "\\ExtentReports\\";
	private static String reportFileLocation =  reportFilepath + reportFileName;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	public static ExtentReports createInstance() {


		String fileName = getReportPath(reportFilepath);

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);
		htmlReporter.setAppendExisting(true);
		htmlReporter.loadXMLConfig("extent-config.xml");

		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		 //Set environment details
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("AUT", "QA");
		extent.setSystemInfo("Author", "Shany Mohan Dhas");
		extent.setAnalysisStrategy(AnalysisStrategy.SUITE);		

		return extent;
	}

	//Create the report path
	private static String getReportPath (String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!" );
				return reportFileLocation;
			} else {
				System.out.println("Failed to create directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
		return reportFileLocation;
	}
}


