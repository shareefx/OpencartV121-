package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass

{	
	@Test (groups={"sanity", "Master"})
	public void verifyLogin() throws InterruptedException 
	{
		logger.info("*** Starting TC002_Login_Test ***");
		
		try
		{
			// Navigate to the login page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account link");
			hp.clickLogin();
			logger.info("Clicked on Login link");
			
			// Create an instance of LoginPage
			LoginPage lp = new LoginPage(driver);
			logger.info("Providing login details");
			
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
		
				
		//MyAccount
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean targetpage= myacc.isMyAccountPageExists();
	//	Assert.assertEquals(targetpage, true, "My Account page does not exist after login.");
		// Above assert or below assert can be used, both are valid.
		Assert.assertTrue(targetpage, "My Account page does not exist after login.");
		}
		catch (Exception e) 
		{
			logger.error("Error during login: " + e.getMessage());
			Assert.fail("Login test failed due to an exception: " + e.getMessage());
		}
		logger.info("*** Finished TC002_Login_Test ***");
	}
}
