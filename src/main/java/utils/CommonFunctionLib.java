package utils;

import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonFunctionLib {

	private static WebDriver driver;
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	/*
	 * Click the Web Element
	 */
	public void webdriverClick (WebElement webElement)
	{
		webElement.click();
	}
	/*
	 * Return the title of page
	 */
	public String getPageTitle(WebDriver driver)
	{
		return driver.getTitle();
	}
	/*
	 * Navigate to specific URL
	 */

	public void navigate_to_url(String url)
	{
		driver.get(url);
	}

	/**
	 * @param noOfDaysToIncrement
	 * @return
	 */
	public String getFutureDate(int noOfDaysToIncrement)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); 
		c.add(Calendar.DATE, noOfDaysToIncrement);
		return c.toString();
	}

	/**
	 * @param date
	 * @param workdays
	 * @return list of thursday Date in the supplied week 
	 * @throws Exception
	 */
	public List<LocalDate> getThursdayDate( ) throws Exception {
		
		LocalDate result = LocalDate.now();
		result.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));
		List<LocalDate>thuDates=new ArrayList<>();
		int addedDays = 0;
		while (addedDays < 2) {
			result = result.plusDays(1);
			if ((result.getDayOfWeek() == DayOfWeek.THURSDAY)) {
				thuDates.add(result);
				++addedDays;
			}
		}
		return thuDates;
	}

	public Map<String,String> getTodayDateAndDay()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");  
		LocalDateTime dd = LocalDateTime.now();  
		Map<String,String>resultMap=new HashMap<String, String>();
		resultMap.put(dtf.format(dd), dd.getDayOfWeek().toString());
		return resultMap;
	}
	/**
	 * @return
	 */
	public boolean isDateAWeekend()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd"); 
		LocalDate dd = LocalDate.now();  
		return (dd.getDayOfWeek().toString().equalsIgnoreCase("Saturday")||dd.getDayOfWeek().toString().equalsIgnoreCase("Sunday"));
	}

	/**
	 * @return
	 */
	public List<String> getAlternateDaysOfWeek()
	{
	List<String>resultDays= new ArrayList<String>();
		LocalDateTime dd = LocalDateTime.now();  
		String day = dd.getDayOfWeek().toString();
		if(day.equalsIgnoreCase("tuesday")||day.equalsIgnoreCase("thursday"))
		{
		resultDays.add("Tuesday");
		resultDays.add("Thursday");
		}
		else  {
			resultDays.add("Monday");
			resultDays.add("Wednesday");
			resultDays.add("Friday");
		}
		return resultDays;
	}

	/**
	 * @return date for next monday
	 */
	public String getNextMondayDate()
	{
		Calendar now = Calendar.getInstance();
		int weekday = now.get(Calendar.DAY_OF_WEEK);
		if (weekday != Calendar.MONDAY)
		{
			int days = (Calendar.SATURDAY - weekday + 2) % 7;
			now.add(Calendar.DAY_OF_YEAR, days);
		}
		Date date = now.getTime();
		return new SimpleDateFormat("dd MMMM yyyy").format(date);
	}
	
	public String getTodaysDay()
	{
		LocalDateTime dd = LocalDateTime.now();  
		return dd.getDayOfWeek().toString();
	}
	
	public void getScreenshot(String path_screenshot) throws IOException{
	    File srcFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String filename=UUID.randomUUID().toString(); 
	    File targetFile=new File(path_screenshot + filename +".jpg");
	    FileUtils.copyFile(srcFile,targetFile);
	}

}



