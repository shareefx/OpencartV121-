package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest  extends BaseClass
{
		
	@Test(groups={"Regression", "Master"}) // This test is part of the regression test suite
	public void verifyAccountRegistration() throws InterruptedException 
	{	
		logger.info("Starting TC_AccountRegistrationTest"); //log4j2 
		
		try
		{
		// Navigate to the registration page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		
		
		// Create an instance of AccountRegistrationPage
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing registration details");
		
		regpage.enterFirstName(randomString().toUpperCase());
		regpage.enterLastName(randomString().toUpperCase());
		regpage.enterEmail(randomString()+"@email.com"); // Generates a random email address
		regpage.enterPassword(randomAlphanumeric());
		
		Thread.sleep(2000);
		
		regpage.checkPrivacyPolicy();
		regpage.clickContinue();
		
		// Verify the confirmation message
		logger.info("Verifying confirmation message");
		String confirmationMessage = regpage.getConfirmationMessage();
		logger.debug("Actual confirmation message: " + confirmationMessage);
		
		if (confirmationMessage.equals("Your Account Has Been Created!")) 
		{
			
			Assert.assertTrue(true);
		} 
		else 
		{
			logger.error("Test Failed, Error during account registration: ");
			logger.debug("Debug logs: ");
			Assert.assertTrue(false);
		// Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!!!", "Account registration failed!");
		}
		}
		catch (Exception e) 
		{
			logger.error("Test Failed, Error during account registration: "+ e.getMessage());
			logger.debug("Debug logs: ", e);
			Assert.fail("Account registration test failed due to an exception.");
		}
		logger.info("*** Finished TC_AccountRegistrationTest ***"); 
	}
	
	
}
