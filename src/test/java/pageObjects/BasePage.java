package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver) 
	{
		this.driver = driver; // Initialize the WebDriver instance
		PageFactory.initElements(driver, this); // Initialize the elements using PageFactory
	}
	
	// Created BasePage under pageObjects package which includes only constructor to avoid code duplication
	// This will be invoked by every page object class constructor that extends BasePage

}
