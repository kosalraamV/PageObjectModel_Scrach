package ExtentReport;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
		
		//singleton design pattern
		//private constructor so that no one else can create object of this class
		private DriverFactory()
		{
			
		}
		
		private static DriverFactory instance = new DriverFactory();
		
		public static DriverFactory getInstance(){
			return instance;
		}
		
		//factory design pattern ---> define separate factory methods for creating objects and create objects by calling that methods
		
		ThreadLocal<WebDriver>  driver= new ThreadLocal<WebDriver>();
		
		
		public WebDriver getDriver()
		{
			return driver.get();
		}
		
		public void setDriver(WebDriver driverparm)
		{
				driver.set(driverparm);
		}
		
		public void closeBrowser()
		{
			driver.get().close();
			driver.remove();
			
		}
		
}





