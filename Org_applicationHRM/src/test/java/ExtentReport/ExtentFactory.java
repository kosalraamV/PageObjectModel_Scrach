package ExtentReport;

import com.aventstack.extentreports.ExtentTest;


public class ExtentFactory {
	
	//singleton design pattern
	//private constructor so that no one else can create object of this class
	private ExtentFactory()
	{
		
	}
	
	private static ExtentFactory instance = new ExtentFactory();
	
	public static ExtentFactory getInstance(){
		return instance;
	}
	
	//factory design pattern ---> define separate factory methods for creating objects and create objects by calling that methods
	ThreadLocal<ExtentTest> testcase = new ThreadLocal<ExtentTest>();
	
	public ExtentTest getExtent()
	{
		return testcase.get();
	}
	
	public void setExtent(ExtentTest extentTestObject)
	{
		testcase.set(extentTestObject);
	}
	
	public void removeExtentObject()
	{
		testcase.remove();
	}
	
}
