package ExtentReport;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Testcases.Base_Class_Common_functions;


public class ListenersImplementation extends Base_Class_Common_functions implements ITestListener {

	public static ExtentReports extentReport;
	public  static ExtentTest testcase;
	
	
	public void onTestStart(ITestResult result) {
		// Before each test case start
		testcase = extentReport.createTest(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(testcase);
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentFactory.getInstance().getExtent().log(Status.PASS,"Test case:" + result.getMethod().getMethodName() + " is Passed.");
		
		//adding Screen shots for pass test case
		try {
			ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(getscreenshotpath(result.getMethod().getMethodName(),driver),
					"Test case Success Screenshots");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentFactory.getInstance().removeExtentObject();
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		ExtentFactory.getInstance().getExtent().log(Status.FAIL,"Test case:" + result.getMethod().getMethodName() + " is Failed.");
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());
		//ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(imagePath)
		
		// adding Screen shots for failed Test case
		
		//Common_functions.getscreenshotpath(TestCaseName)
		
		/*File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH.mm.ss");
		Date date = new Date();
		String actualDate = format.format(date);

		String ScreenshotPath = System.getProperty("user.dir") + "/ExtReports/Screenshots/" + actualDate + ".jpeg";
		File dest = new File(ScreenshotPath);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		try {
			ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(getscreenshotpath(result.getMethod().getMethodName(),driver),
					"Test case Failure Screenshots");
			ExtentFactory.getInstance().removeExtentObject();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.SKIP,"Test case:" + result.getMethod().getMethodName() + " is Skipped.");
		ExtentFactory.getInstance().removeExtentObject();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		
		try {
			//extentReport=ExtentReportNG.setupExtentReport();
			extentReport=Base_Class_Common_functions.setupExtentReport();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onFinish(ITestContext context) {
		extentReport.flush();

	}

}
