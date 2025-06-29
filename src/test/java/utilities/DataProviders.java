package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	// DataProviders class provides test data to TestNG tests using @DataProvider.
	// Currently supports Excel-based data via ExcelUtility. Can be extended for databases, APIs, etc.

	
	//DataProvider 1
	
	@DataProvider (name = "LoginData")
	public String [][] getData() throws IOException 
	{
		String path = ".\\testData\\Book1.xlsx";

	    ExcelUtility xlutil = new ExcelUtility(path);
	    
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][]  = new String[totalrows][totalcols]; // created 2D array to store login data
		
		for (int i = 1; i <= totalrows; i++)  // row 0 is used for headers , hence The actual data starts from row 1 in Excel. Read data from excel storing two dimensional array
		{ //  i is rows and j is columns
			for (int j = 0; j < totalcols; j++) //0 Unlike rows, columns usually don't have headers, or headers are not skipped in data reads.
			{
				loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // 1 ,  0
			} // The loginData array index starts from 0 in Java.


		}
		
		return loginData; // return the 2D array containing login data
	}
	
	//DataProvider 2
	
	//DataProvider 3
		
}
