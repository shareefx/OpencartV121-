package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage
{
		public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//h1[normalize-space()='My Account']")
	WebElement myAccountHeader;
	
	@FindBy(xpath = "//li[@class='list-inline-item']//a[@class='dropdown-toggle']")
	WebElement myAccountDropdown;
	
	@FindBy(xpath="//a[@class='dropdown-item'][normalize-space()='Logout']")
	WebElement logoutLink;
	
	public boolean isMyAccountPageExists() 
	{	
		try 
		{
		return (myAccountHeader.isDisplayed());
		} 
		catch (Exception e) 
		{
			return false; // If the element is not found, return false
		}
	}
	
	public void clickLogout() {
	    try {
	    	
	    	 myAccountDropdown.click();
	    	 
	  //  	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   //         wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
	        System.out.println("Attempting to click Logout...");
	        logoutLink.click();
	        System.out.println("Logout clicked successfully.");
	    } catch (Exception e) {
	        System.out.println("Error during logout: " + e.getMessage());
	    }
	}
}
