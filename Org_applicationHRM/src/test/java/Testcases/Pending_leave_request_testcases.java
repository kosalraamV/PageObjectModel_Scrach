package Testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pageobjects.Dashboard_page_object;
import Pageobjects.Login_page_objects;

public class Pending_leave_request_testcases extends Base_Class_Common_functions{
	
	
	 Logger logger = Logger.getLogger(Pending_leave_request_testcases.class); 
	
	public void validatePendingleaverequest()
	{
		//testcase.log(Status.PASS, "User verifying the pending leave request details-succesfully");
		logger.info("verification the pending leave request details");
		PageFactory.initElements(driver, Dashboard_page_object.class);
		String actual_message=Dashboard_page_object.pendingLeaveEequest.getText();
		Assert.assertEquals(actual_message, "No Records are Available");
	}	
	
	
@Test
	public void verifypendingleaverequest() throws Exception
	{
	//extent report
	//testcase=extentReport.createTest("verify pending leave request");

	//testcase.log(Status.PASS, "User getting the pending leave request details-succesfully");
	logger.info("Getting the pending leave request details");
	validatePendingleaverequest();
	
	//Thread.sleep(10000);
	//testcase.log(Status.PASS, "Ending the pending leave request details testcase sucessfully");
	logger.info("Ending the pending leave request details testcase sucessfully");
	}
	
}
