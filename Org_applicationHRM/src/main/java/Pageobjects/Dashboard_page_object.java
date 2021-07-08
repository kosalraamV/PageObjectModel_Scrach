package Pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard_page_object {
	
	 @FindBy(xpath="//*[@id='panel_draggable_1_2']//following::td[1]")	  
	  public static WebElement pendingLeaveEequest;
	  

}
