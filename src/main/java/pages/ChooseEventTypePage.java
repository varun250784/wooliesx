package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import objectrepository.ChooseEventTypePage_OR;

public class ChooseEventTypePage extends ChooseEventTypePage_OR{

	WebDriver driver;
	public ChooseEventTypePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public EditMeetingDetailsPage chooseEventType()
	{
		driver.findElement(chooseEventButton).click();
		return new EditMeetingDetailsPage(driver);
	}
}
