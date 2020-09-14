package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import objectrepository.EditMeetingDetailsPage_OR;

public class EditMeetingDetailsPage extends EditMeetingDetailsPage_OR {

	WebDriver driver;
	 public EditMeetingDetailsPage(WebDriver driver) {
		 
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
		 }
	
	
	
	public void enterMeetingTitle(String meetingTitle)
	{
		driver.findElement(MeetingTitle).sendKeys(meetingTitle);
	}

	public void clickStartDate()
	{
		driver.findElement(StartDate).click();
	}
	
	public CalendarHomePage clickSaveMMeeting()
	{
		driver.findElement(SaveMeeting).click();
		return new CalendarHomePage (driver);
	}
	
	public AddPeoplePage clickAddPeopleLink() 
	{
		return new AddPeoplePage(driver);
	}
	public CustomReferencePage setCustomRecurrence()
	{
		driver.findElement(RepeatMeeting).click();
		driver.findElement(ScheduleType).click();
		return new CustomReferencePage(driver);
	}

	public void fillMeetingHour(String hour)
	{
		driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc'"+hour+"']"));
	}
	public void fillMeetingMinute(String minute)
	{
		driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc'"+minute+"']"));
	}
	public void clickSaveTime()
	{
		driver.findElement(OKButton).click();
	}
}
