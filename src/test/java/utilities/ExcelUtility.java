package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path) 
	{
		this.path = path; // Initialize the path to the Excel file
	}
	      public int getRowCount(String sheetName) throws IOException 
	{
		fi = new FileInputStream(path); // Open the Excel file
		workbook = new XSSFWorkbook(fi); // Create a workbook instance
		sheet = workbook.getSheet(sheetName); // Get the specified sheet
		int rowCount = sheet.getLastRowNum(); // Get the last row number
		workbook.close(); // Close the workbook
		fi.close(); // Close the file input stream
		return rowCount; // Return the row count
		
	}
	      
	      public int getCellCount(String sheetName, int rowNum) throws IOException
	      {
	    	  fi = new FileInputStream(path); // Open the Excel file
	    	  workbook = new XSSFWorkbook(fi); // Create a workbook instance
	    	  sheet = workbook.getSheet(sheetName); // Get the specified sheet
	    	  row = sheet.getRow(rowNum); // Get the specified row
	    	  int cellCount = row.getLastCellNum(); // Get the last cell number in the row
	    	  workbook.close(); // Close the workbook
	    	  fi.close(); // Close the file input stream
	    	  return cellCount; // Return the cell count
	      }
	      
	      public String getCellData(String sheetName, int rowNum, int colNum) throws IOException 
	      {
	    	  fi = new FileInputStream(path); // Open the Excel file
	    	  workbook = new XSSFWorkbook(fi); // Create a workbook instance
	    	  sheet = workbook.getSheet(sheetName); // Get the specified sheet
	    	  row = sheet.getRow(rowNum); // Get the specified row
	    	  cell = row.getCell(colNum); // Get the specified cell
	    	  
		    DataFormatter formatter = new DataFormatter(); // Create a DataFormatter instance
		    String data;
		    try
		    {
		    	data=formatter.formatCellValue(cell); // Format the cell value as a string
		    }
		    catch (Exception e)
		    {
		    	data = ""; // If an exception occurs, set cellData to an empty string
		    }
		    workbook.close(); // Close the workbook
		    fi.close(); // Close the file input stream
		    return data; // Return the cell data as a string
	      }
	      
	      public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException 
	      {
	    		    	  
	    	  File xlfile = new File(path); // Create a File object for the Excel file
	    	  if (!xlfile.exists()) // Check if the file exists
	    	  {
	    		  workbook = new XSSFWorkbook(); // If not, create a new workbook
	    		  fo= new FileOutputStream(path); // Create a file output stream to save the new workbook
	    		  workbook.write(fo); // Write the new workbook to the file
	    		  
	    	  }
	    	  
	    		  fi = new FileInputStream(path); // If it exists, open the file input stream
	    		  workbook = new XSSFWorkbook(fi); // Create a workbook instance
	    		  
	    		  
	    		  if(workbook.getSheetIndex(sheetName) == -1) // Check if the specified sheet exists
	    			  workbook.createSheet(sheetName); // If not, create a new sheet
	    			  sheet = workbook.getSheet(sheetName); // If not, create a new sheet
	    		  
	    		if(sheet.getRow(rowNum)==null)
	    			  sheet.createRow(rowNum); // Check if the specified row exists, if not, create a new row
	    		  row = sheet.getRow(rowNum); // Get the specified row
	    			  
	    		  cell = row.createCell(colNum);
	    		  cell.setCellValue(data); 
	    		  fo= new FileOutputStream(path); 
	    	
	    		  workbook.write(fo);                    //  Write workbook with new data
	    		  workbook.close();                      //  Close workbook
	    		  fi.close();     // Close the file input stream  
	    		  fo.close();    	// Close the file output stream	
	      }
	    public void fillGreenColor( String sheetName, int rowNum, int colNum) throws IOException 
	    {
	    		  fi = new FileInputStream(path); // Open the Excel file
	    		  workbook = new XSSFWorkbook(fi); // Create a workbook instance
	    		  sheet = workbook.getSheet(sheetName); // Get the specified sheet
	    		  row = sheet.getRow(rowNum); // Get the specified row
	    		  cell = row.getCell(colNum); // Get the specified cell
	    		  
	    		
	    		  style = workbook.createCellStyle(); // Create a new cell style
	    		
	    		  style.setFillForegroundColor(IndexedColors.GREEN.getIndex()); // Set the fill color to green using IndexedColors
	    		  style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Set the fill pattern to solid
	    		  
	    		  cell.setCellStyle(style); // Apply the style to the cell
	    		  
	    		  workbook.write(fo); // Write changes to the workbook
	    		  workbook.close(); // Close the workbook
	    		  fi.close(); // Close the file input stream
	    		  fo.close(); // Close the file output stream
	      }
	    	   
	    	  public void fillRedColor( String sheetName, int rowNum, int colNum) throws IOException 
	    	  {
	    		  fi = new FileInputStream(path); // Open the Excel file
	    		  workbook = new XSSFWorkbook(fi); // Create a workbook instance
	    		  sheet = workbook.getSheet(sheetName); // Get the specified sheet
	    		  row = sheet.getRow(rowNum); // Get the specified row
	    		  cell = row.getCell(colNum); // Get the specified cell
	    		  
	    		
	    		  style = workbook.createCellStyle(); // Create a new cell style
	    		
	    		  style.setFillForegroundColor(IndexedColors.RED.getIndex()); // Set the fill color to green using IndexedColors
	    		  style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Set the fill pattern to solid
	    		  
	    		  cell.setCellStyle(style); // Apply the style to the cell
	    		  
	    		  workbook.write(fo); // Write changes to the workbook
	    		  workbook.close(); // Close the workbook
	    		  fi.close(); // Close the file input stream
	    		  fo.close(); // Close the file output stream
	    	  
	      }	

}
