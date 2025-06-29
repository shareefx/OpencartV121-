package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage  // BasePage is a class that extends PageFactory to initialize elements
{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myAccountLink;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registerLink;
	
	@FindBy(xpath= "//a[@class='dropdown-item'][normalize-space()='Login']")
	WebElement linkLogin;
	
	public void clickMyAccount() {
		myAccountLink.click();
	}
	
	public void clickRegister() {
		registerLink.click();
	}
	
	public void clickLogin()
	{
		linkLogin.click();
	}

}
