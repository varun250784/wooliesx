package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import objectrepository.CalendarHomePage_OR;

public class CalendarHomePage extends CalendarHomePage_OR{
	WebDriver driver;
	 public CalendarHomePage(WebDriver driver) {
	 this.driver = driver;
	 PageFactory.initElements(driver, this);
	 }
	private  final String meetingTitle = "Test Meeting with Varun";
	public  By MeetingDetails=MobileBy.xpath("//*[çontains(@content-desc,"+meetingTitle+"]"); 

	 public ChooseEventTypePage clickCreateEvent()
	 {
		 driver.findElement(CreateEvent).click();
		 return new ChooseEventTypePage(driver);
	 }
	 public String getMeetingDetails(String meetingTitle)
	 {
		 return driver.findElement(By.xpath("//*[contains(@content-desc,'"+meetingTitle+"')]")).getAttribute("content-desc");
	 }
}
