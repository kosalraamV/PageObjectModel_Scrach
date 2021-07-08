package Pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class User_Role_page_object {
	
  @FindBy(xpath="//b[text()='Admin']")	  
  public static WebElement Admin;
  
  @FindBy(xpath="//a[text()='User Management']")	  
  public static WebElement User_Management;
  
  @FindBy(xpath="//a[text()='Users']")	  
  public static WebElement Users;
  
  @FindBy(xpath="//input[@id='searchBtn']")	  
  public static WebElement Searchbutton;
  
  @FindBy(xpath="//label[text()='User Role']//following::select[@id='searchSystemUser_userType']")	  
  public static WebElement UserRole_dropdown;
  
  @FindBy(xpath="//label[text()='Status']//following::select[@id='searchSystemUser_status']")	  
  public static WebElement Status_dropdown;
  
  @FindBy(xpath="//table[@id='resultTable']//following::th//a[text()='User Role']//following::tr[1]//td[3]")	  
  public static WebElement UserRole_dropdown_value;
  
  @FindBy(xpath="//table[@id='resultTable']//following::th//a[text()='User Role']//following::tr[1]//td[5]")	  
  public static WebElement UserRole_dropdown_status;
  
  


}
