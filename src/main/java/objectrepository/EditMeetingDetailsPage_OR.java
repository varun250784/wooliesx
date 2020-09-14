package objectrepository;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public class EditMeetingDetailsPage_OR {
	public  By MeetingTitle=MobileBy.id("com.google.android.calendar:id/title");
	public  By StartDate=MobileBy.xpath("//*[starts-with(@content-desc,'Start date:')]"); 
	public  By StartTime=MobileBy.xpath("//*[starts-with(@content-desc,'Start time:')]"); 
	public  By EndDate=MobileBy.xpath("//*[starts-with(@content-desc,'End date:')]"); 
	public  By EndTime=MobileBy.xpath("//*[starts-with(@content-desc,'End time:')]"); 
	public  By RepeatMeeting=MobileBy.id("com.google.android.calendar:id/first_line_text");
	public  By ClickToAddPeople=MobileBy.xpath("//android.widget.LinearLayout[@content-desc='Add people']");
	public  By ScheduleType=MobileBy.xpath("//*[normalize-space(text())='Custom…']");
	public  By SaveMeeting = MobileBy.id("com.google.android.calendar:id/save");
	public  By OKButton = MobileBy.id("android:id/button1");

}
