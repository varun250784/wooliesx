package stepdfn;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.List;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import managers.AndroidDriverFactory;
import pages.CustomReferencePage;
import pages.EditMeetingDetailsPage;
import utils.CommonFunctionLib;
import pages.AddPeoplePage;
import pages.ChooseEventTypePage;
import pages.CalendarHomePage;


public class GoogleCalendarSteps {
	private CommonFunctionLib cfl= new CommonFunctionLib();
	private CalendarHomePage chp;
	private ChooseEventTypePage cep;
	private AddPeoplePage app;
	private CustomReferencePage crp;
	private EditMeetingDetailsPage emd;
	private AndroidDriverFactory af;
	private AndroidDriver<AndroidElement> driver;
	List<String>meetingDays;
	String meetingTitle;


	@Given("^I have launched the Calendar App$")
	public void launchApp() throws IOException
	{
		driver = af.getDriver();
	}

	@Given("^it is not a \"([^\"]*)\"$")
	public void getWorkingDay(String args)
	{
		//No Need to use this argument as it is handled in getAlternativeDays
	}

	@And("^meeting is not repeated on successive days$")
	public void getAlternativeDays()
	{
		meetingDays=cfl.getAlternateDaysOfWeek();
	}
	@Then("^I want to book a meeting with the title \"([^\"]*)\"$")
	public void bookAMeeting(String val)
	{
		meetingTitle=val;
		chp=new CalendarHomePage(driver);
		cep = chp.clickCreateEvent();
		emd = cep.chooseEventType();
		emd.enterMeetingTitle(val);

	}
	@And("^Set Meeting duration as  \"([^\"]*)\" in the evening and I invite \"([^\"]*)\" people$")
	public void setMeetingDuration(String val1,String val2)
	{
		emd.fillMeetingHour(val1);
		emd.fillMeetingHour(val2);
		emd.clickSaveTime();
		crp=emd.setCustomRecurrence();
		crp.selectDays(meetingDays);
		emd=crp.saveCustomMeeting();
	}		

	@And("^I save the meeting$")
	public void saveMeeting(String val)
	{
		chp=emd.clickSaveMMeeting();
	}
	@Then("^I check if the meeting is created as expected$")
	public void verifyMeeting()
	{
		String title=chp.getMeetingDetails(meetingTitle);
		assertTrue(title.contains(meetingTitle));
	}

	@After("@calendar")
	public void screenShot(Scenario scenario) throws Throwable {

		
		if (scenario.isFailed())
			cfl.getScreenshot(System.getProperty("user.dir")+"/screenshots");
	}
}
