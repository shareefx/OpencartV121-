package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage  // BasePage is a class that extends PageFactory to initialize elements 

{
	WebDriver driver;

// Constructor to initialize the driver
    public AccountRegistrationPage(WebDriver driver) 
    {
		super(driver);
		
	}
    
    @FindBy(xpath="//input[@id='input-firstname']")
    WebElement txtFirstName;
    
    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement txtLastName;
    
    @FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//button[normalize-space()='Continue']")
	WebElement btnContinue;
    
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
    
	
	// Action Methods
	public void enterFirstName(String firstName) 
	{
		txtFirstName.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) 
	{
		txtLastName.sendKeys(lastName);
	}
	
	public void enterEmail(String email) 
	{
		txtEmail.sendKeys(email);
	}
	
	public void enterPassword(String password) 
	{
		txtPassword.sendKeys(password);
	}
	
	public void checkPrivacyPolicy() 
	{
		if (!chkPrivacyPolicy.isSelected()) 
		{
			chkPrivacyPolicy.click();
		}
	}
	
	public void clickContinue() 
	{	
		//Approach 1: Using WebElement directly
		btnContinue.click();
		
		//Approach 2: Using submit method
		//btnContinue.submit(); 
		
		//Approach 3: Using JavaScript Executor (if needed)
		// JavascriptExecutor js = (JavascriptExecutor) driver);
		// js.executeScript("arguments[0].click();", btnContinue);
		
		// Approach 4: Using Actions class (if needed)
		// Actions actions = new Actions(driver);
		// actions.moveToElement(btnContinue).click().perform();
		
		// Approach 5: using Keys class 
		// btmContinue.sendKeys(Keys.RETURN); // This can be used if the button is focused
		
		// Approach 6: Using ExpectedConditions (if needed)
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public String getConfirmationMessage() 
	{
		try {
		return msgConfirmation.getText();
		} 
		catch (Exception e) 
		{
			return (e.getMessage());
		}
	}



}
