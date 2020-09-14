package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class ExcelReader {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    private static String FILE_NAME;
    
    /*
	 * Fetch the test data from Excel
	 */
	public  HashMap<String,List<Map<String, String>>> getTestDataFromExcel() throws Exception {

		try {
			
			
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"/Config.properties");
			prop.load(ip);
	            // Open the Excel file
	            FileInputStream ExcelFile = new FileInputStream(prop.getProperty("filepath"));
	            // Access the required test data sheet
	            ExcelWBook = new XSSFWorkbook(ExcelFile);
	            ExcelWSheet = ExcelWBook.getSheet("Sheet1");
			
			int rowsUsed = getRowUsed();
			int colsUsed = getColumns();
			HashMap<String,List<Map<String, String>>> arrayMapList = new HashMap<>();
			List<Map<String, String>> returnMapList = new ArrayList<>();
			LinkedHashMap<String, String> map;

			for (int i = 1; i <= rowsUsed; i++) {
				map = new LinkedHashMap<String, String>();
				for (int j = 1; j < colsUsed; j++) {
					
					map.put(getCellData(0, j), getCellData(i , j));
					
				}
				returnMapList = new ArrayList<>();
				returnMapList.add(map);
				arrayMapList.put(getCellData(i, 0), returnMapList);
			}
			
		
			return arrayMapList;
		} catch (Exception e) {
			throw new Exception("Details could not be fetched from Excel");
		}
	}
	
	  public static int getRowUsed() throws Exception {
	        try {
	            int RowCount = ExcelWSheet.getLastRowNum();
	            return RowCount;
	        } catch (Exception e) {
	            throw (e);
	        }
	    }

	    public static int getColumns() throws Exception {
	        try {
	            int ColCount = ExcelWSheet.getRow(0).getLastCellNum();
	            return ColCount;
	        } catch (Exception e) {
	            throw (e);
	        }
	    }
	    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	    public static String getCellData(int RowNum, int ColNum) throws Exception {
	        try {
	            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	            String CellData = Cell.getStringCellValue();
	            return CellData;
	        } catch (Exception e) {
	            return "";
	        }
	    }
}
