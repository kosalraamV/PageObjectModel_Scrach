package Testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pageobjects.User_Role_page_object;


public class User_Role_testcase extends Base_Class_Common_functions {
	static Logger logger =Logger.getLogger(User_Role_testcase.class); 
	
	
	public void moveToUserpage()
	{
		//testcase.log(Status.INFO, "User is navigating to user page-succesfully");
		logger.info("Navigating to user page");
		Actions actions = new Actions(driver);
		actions.moveToElement(User_Role_page_object.Admin);
		actions.moveToElement(User_Role_page_object.User_Management);
		actions.moveToElement(User_Role_page_object.Users);
		actions.click().build().perform();
	}
	
	public void Select_UserRole()
	{
	//testcase.log(Status.INFO, "User is Selecting the User_role_dropdown-succesfully");	
	logger.info("Selecting the User_role");
    Select selectRole=new Select(User_Role_page_object.UserRole_dropdown);
    selectRole.selectByIndex(1);
	}
	
	public void Select_status()
	{
   // testcase.log(Status.INFO, "User is navigating to user_status_drop_down-succesfully");
	logger.info("Navigating to User_status");
    Select selectstatus=new Select(User_Role_page_object.Status_dropdown);
    selectstatus.selectByIndex(1);
	}
	
	
	@Test
	public void checkuserRole() 
	{
		//testcase=extentReport.createTest("verify UserRole");
		PageFactory.initElements(driver, User_Role_page_object.class);
		moveToUserpage();
		Select_UserRole();
		Select_status();
		User_Role_page_object.Searchbutton.click();
		
		//testcase.log(Status.PASS, "User is Verifying the User_role-succesfully"); 
		logger.info("Verifying the User_role");
		String actualRole=User_Role_page_object.UserRole_dropdown_value.getText();
		Assert.assertEquals(actualRole, "Admin");
		
		//testcase.log(Status.PASS, "User is Verifying the User_status-succesfully"); 
		logger.info("Verifying  the User_status");
		String actualstatus=User_Role_page_object.UserRole_dropdown_status.getText();
		Assert.assertEquals(actualstatus, "Enabled");
		
		//testcase.log(Status.PASS, "User is Verifying the User_status-succesfully"); 
		logger.info("Ending the User Role testcase");
		//Assert.assertTrue(false);
		
	}
	
}
