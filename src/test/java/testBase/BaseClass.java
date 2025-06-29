package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public static WebDriver driver;
	public Logger logger; // for Log4j Logger for logging messages, can be used for debugging or information purposes
	public Properties p; // Properties object to hold configuration properties, if needed
	
	@BeforeClass (groups={"Sanity", "Master", "Regression"})
	@Parameters({"os", "browser"})
	public void setUp( String os, String br) throws IOException 
	{
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources/config.properties");
		p=new Properties();
		p.load(file); // Load the properties from the file
		
		logger=LogManager.getLogger(this.getClass()); // Initialize the logger for this class
		
		if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//OS
			if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC); // Set the platform to Mac
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX); // Set the platform to Linux
			}
			else if(os.equalsIgnoreCase("windows") || os.equalsIgnoreCase("win11"))
			{
				capabilities.setPlatform(Platform.WIN11); // Set the platform to Windows 10
			}
			else
			{
				System.out.println("Invalid OS name provided: " + os); // Handle invalid OS name
				return;
			}
			
			//Browser
			
			switch(br.toLowerCase())
			{
				case "chrome" : capabilities.setBrowserName("chrome"); break; // Set the browser name to Chrome
				case "firefox": capabilities.setBrowserName("firefox"); break; // Set the browser name to Firefox
				case "edge"   : capabilities.setBrowserName("MicrosoftEdge"); break; // Set the browser name to Edge
				default: 
					System.out.println("Invalid browser name provided: " + br); // Handle invalid browser name
					return; // Exit if the browser name is invalid
			}
			
			driver = new RemoteWebDriver(URI.create("http://192.168.0.5:4444").toURL(), capabilities);
 // Initialize the RemoteWebDriver with the hub URL and capabilities
			
			
	/*		if(br.equalsIgnoreCase("chrome"))
			{
				capabilities.setBrowserName("chrome"); // Set the browser name to Chrome
			}
			else if(br.equalsIgnoreCase("firefox"))
			{
				capabilities.setBrowserName("firefox"); // Set the browser name to Firefox
			}
			else if(br.equalsIgnoreCase("edge"))
			{
				capabilities.setBrowserName("edge"); // Set the browser name to Edge
			}
			else
			{
				System.out.println("Invalid browser name provided: " + br); // Handle invalid browser name
				return;
			}
	*/		
			
		}
		
		
		if (p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "firefox": driver=new FirefoxDriver(); break;
		case "edge"   : driver=new EdgeDriver(); break;
		default: System.out.println("Invalid browser name provided: " + br); return; // Handle invalid browser name
		}
		}	
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); // Navigate to the URL from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity", "Master", "Regression"})
	public void tearDown() 
	{
		// Close the WebDriver, clean up resources, etc.
		driver.quit();
	}
	
	
	// Method to generate a random string for email address
	
		public String randomString()
		{
			RandomStringGenerator generator = new RandomStringGenerator.Builder()
	                .withinRange('a', 'z')
	                .filteredBy(Character::isLetter)
	                .build();
	        return generator.generate(5); // 5 alphabetic characters
		}
		
		public String randomNumber()
		{
			 RandomStringGenerator generator = new RandomStringGenerator.Builder()
		                .withinRange('0', '9')
		                .build();
		        return generator.generate(8); // 8 numeric characters
		}
		
		public String randomAlphanumeric()
		{
			RandomStringGenerator alphaGen = new RandomStringGenerator.Builder()
	                .withinRange('a', 'z')
	                .filteredBy(Character::isLetterOrDigit)
	                .build();

	        RandomStringGenerator numGen = new RandomStringGenerator.Builder()
	                .withinRange('0', '9')
	                .build();

	        String alpha = alphaGen.generate(3);
	        String num = numGen.generate(3);
	        return alpha + "@" + num;
	        }
		
		public String captureScreen(String testName) throws IOException 
		{
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			
			TakesScreenshot ts = ((TakesScreenshot) driver);
			File sourceFile= ts.getScreenshotAs(OutputType.FILE);
			
			String targetFilepath= System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timeStamp + ".png";
			File targetFile= new File(targetFilepath);
			
			sourceFile.renameTo(targetFile); // Rename the screenshot file to include test name and timestamp
			
			return targetFilepath; // Return the path of the captured screenshot
		}
}
