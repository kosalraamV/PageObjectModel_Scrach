package Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Pageobjects.Login_page_objects;


public class Base_Class_Common_functions {

	public static WebDriver driver;
	public static Properties properties;
	public static ExtentReports extentReport;
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentTest testcase;
	
	//factory design pattern
	static Logger logger =Logger.getLogger(Base_Class_Common_functions.class);
	
	// reading data from configuration reader
	public Properties loadProperFile() throws IOException {
		FileInputStream fis = new FileInputStream("./Config_reader/config.properties");
		properties = new Properties();
		properties.load(fis);
		return properties;
	}
	
	//login to application
	public static void login()
	{
		//testcase.log(Status.PASS, "User is log into application successfully");
		logger.info("loging into applicationc succesfully");
		PageFactory.initElements(driver, Login_page_objects.class);
		Login_page_objects.username.sendKeys(properties.getProperty("Username"));
		Login_page_objects.password.sendKeys(properties.getProperty("Password"));
		Login_page_objects.submitbutton.click();
	}

	
	//taking screen shot
	public static String getscreenshotpath(String TestCaseName,WebDriver driver) throws IOException
	{
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH.mm.ss");
		Date date = new Date();
		String actualDate = format.format(date);
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destpath=System.getProperty("user.dir") + "/ExtReports/Screenshots/" + actualDate + ".png";
		File file = new File(destpath);
		FileUtils.copyFile(src, file);
		return destpath;
	}
	
	//closing first tab
	public static void close_first_tab() 
	{
	ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs2.get(0));
    driver.close();
    driver.switchTo().window(tabs2.get(1));
	}
	
	public static ExtentReports setupExtentReport(){
		SimpleDateFormat format= new SimpleDateFormat("MM-dd-yyyy HH.mm.ss");
		Date date= new Date();
		String actualDate=format.format(date);
		
		String reportPath= System.getProperty("user.dir")+"/ExtReports/ExecutionReport_"+actualDate+".html";
		htmlreporter= new ExtentHtmlReporter(reportPath);
		htmlreporter.config().setEncoding("WebDriver-3");
		htmlreporter.config().setDocumentTitle("Automation Reports");
		htmlreporter.config().setReportName("Automation Test Result");
		htmlreporter.config().setTheme(Theme.STANDARD);
		
		//ExtentSparkReporter sparkReport=new ExtentSparkReporter(reportPath);
		 extentReport=new ExtentReports();
		//extentReport.attachReporter(sparkReport);
		//sparkReport.config().setDocumentTitle("DocumentTitle");
		//sparkReport.config().setTheme(Theme.STANDARD);
		//sparkReport.config().setReportName("ReportName");
		extentReport.attachReporter(htmlreporter);
		extentReport.setSystemInfo("Executed on Environment:", properties.getProperty("Url"));
		extentReport.setSystemInfo("Executed on Browser", properties.getProperty("Browser"));
		extentReport.setSystemInfo("Executed on OS", properties.getProperty("os.name"));
		extentReport.setSystemInfo("Executed on Username", properties.getProperty("Username"));
		extentReport.attachReporter(htmlreporter);
		
		return extentReport;
	}
	
	@BeforeSuite
	public void lauchBrowser() throws IOException {
		//configuration log4j properties file
		PropertyConfigurator.configure("./src/main/resources/log4j.properties");
		logger.info("Orange HRM Test Begins");
		
		logger.info("loading properties file");
		//loading properties file
		loadProperFile();
		
		//extent report configuration
		setupExtentReport();				
	}
	
	
	@BeforeMethod
	public void Browser_setup() {
		String browser = properties.getProperty("Browser");
		String url = properties.getProperty("Url");
		String driverlocation = properties.getProperty("Driverlocation");
		if (browser.equalsIgnoreCase("chrome")) {
			logger.info("lauching Chrome Browser succesfully");
			System.setProperty("webdriver.chrome.driver", driverlocation);
			driver = new ChromeDriver();
			} 
		else if (browser.equalsIgnoreCase("firefox")) {
			logger.info("lauching firefox Browser succesfully");
			System.setProperty("webdriver.chrome.driver", driverlocation);
			driver = new FirefoxDriver();
		}
		close_first_tab();
		driver.manage().window().maximize();
		logger.info("Navigating to Application Url");
		driver.get(url);
		//driver.manage().deleteAllCookies();
		//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		logger.info("Maximing UI Application");
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		
        login();
	}
	
	@AfterMethod
	public void teardown_BM() {
		
		logger.info("Execution Done.closing Browser succesfully");
        driver.quit();

	}

	@AfterSuite
	public void teardown() {
		
		//logger.info("Execution Done.closing Extent Report succesfully");
		//ExtentReportNG_config.endReport();
		//driver.quit();
	}

}
