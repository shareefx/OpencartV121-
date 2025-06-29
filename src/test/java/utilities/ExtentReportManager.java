package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass; // Assuming BaseClass exists in testBase package

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter  sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) 
	{
	/*	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp = df.format(dt);
	*/	
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups= testContext.getCurrentXmlTest().getIncludedGroups();
		if(! includeGroups.isEmpty()) 
		{
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display the groups in the report
		test.log(Status.PASS, result.getName() + " passed successfully.");
		
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display the groups in the report
		
		test.log(Status.FAIL, result.getName() + " got failed.");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try 
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	}
	
		public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // To display the groups in the report
		test.log(Status.SKIP, result.getName() + " got skipped.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
		
		public void onFinish(ITestContext context) 
	{
		extent.flush(); // Write the report to the file
		
		String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
		File extentReportFile = new File(pathOfExtentReport);
		
		if (Desktop.isDesktopSupported()) 
		{
			try 
			{
				Desktop.getDesktop().browse(extentReportFile.toURI()); // Open the report in the default browser
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
	/*	try
		{
			URL url = new URL("file:///" + System.getProperty("user.dir") + "/reports/" + repName);
			
			// Create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com"); // Set your SMTP server
			email.setSmtpPort(465); // Set your SMTP port
			email.setAuthenticator( new DefaultAuthenticator("dingabinga@email.com", "password")); // Set your email and password
			email.setSSLOnConnect(true); // Use SSL for secure connection
			email.setFrom(" dingabinga@email.com");
			email.setSubject("OpenCart Automation Test Report");
			email.setMsg("Please find the attached OpenCart Automation Test Report.");
			email.addTo(" bingadinga@email.com"); // Set the recipient email address
			email.attach(url, "extent report", " Please check report");
			email.send(); // Send the email
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		*/
	}
	

}
