package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import objectrepository.CustomReferencePage_OR;
import utils.CommonFunctionLib;

public class CustomReferencePage extends CustomReferencePage_OR {
	
	WebDriver driver;
	CommonFunctionLib cfl = new CommonFunctionLib();
	 public CustomReferencePage(WebDriver driver) {
	 this.driver = driver;
	 PageFactory.initElements(driver, this);
	 }
	 
	 /**
	 * @param caseType
	 * @param dayList
	 * Select the days for repetition
	 */
	public void selectDays(List<String>dayList)
	 {
		uncheckDayIfWeekened();
			 	for(String s :dayList)
			 	{
			 		 driver.findElement(By.xpath("//android.view.View[@content-desc=\""+s+ "\"]")).click();
			 	}
			 	
	 }
	 
	 /**
	 * Uncheck Saturday and Sunday if tests are being run on Weekend
	 */
	public void uncheckDayIfWeekened()
	 {
		 if(cfl.isDateAWeekend())
		 {
			 driver.findElement(By.xpath("//android.view.View[@content-desc=\""+cfl.getTodaysDay()+ "\"]")).click();
		 }
	 }
	
	/**
	 * Press Save and return to Edit Meeting details page
	 */
	public EditMeetingDetailsPage saveCustomMeeting()
	{
		driver.findElement(DoneCustomRecurrence).click();
		return new EditMeetingDetailsPage(driver);
	}

}
