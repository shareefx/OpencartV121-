package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;
import pageObjects.MyAccountPage;

//This class provides data for Data-Driven Testing (DDT), particularly for login functionality.

	/*
	 * Data is valid - login successful - test pass - logout
	 * Data is valid - login unsuccessful - test fail
	 * 
	 * Data is invalid - login successful - test fail - logout
	 * Data is invalid - login unsuccessful - test pass
	 */

public class TC003_LoginDDT  extends BaseClass
{
		
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataDriven") // getting data provider from DataProviders class
	
	public void verifyLoginDDT( String email, String password, String exp)
	{
		logger.info("**** Starting TC003_LoginDDT  ***");
try {		
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	// Create an instance of LoginPage
	LoginPage lp = new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(password);
	lp.clickLogin();
	
	//MyAccount
	
	MyAccountPage myacc = new MyAccountPage(driver);
	boolean isTargetPage = myacc.isMyAccountPageExists();
	
	if(exp.equalsIgnoreCase("Valid")) 
	{
		if(isTargetPage==true) 
		{
			myacc.clickLogout(); // Logout after successful login
			Assert.assertTrue(true, "Login successful with valid credentials.");
			
		}
		else 
		{
			Assert.assertTrue(false);
		}
	}
	
	
	if (exp.equalsIgnoreCase("Invalid")) 
	{
		if(isTargetPage==true) 
		{
			myacc.clickLogout(); // Logout after successful login
			Assert.assertTrue(false, "Login successful with invalid credentials.");
		}
		else 
		{
			Assert.assertTrue(true, "Login failed with invalid credentials.");
		}
	}
		
} 	catch (Exception e)
	{
		Assert.fail("An exception occurred during login: " + e.getMessage());
	}
		 logger.info("**** Test completed for TC003_LoginDDT  ***");
	}
}
