package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import objectrepository.AddPeoplePage_OR;

public class AddPeoplePage extends AddPeoplePage_OR{

	WebDriver driver;
	 public AddPeoplePage(WebDriver driver) {
	 this.driver = driver;
	 PageFactory.initElements(driver, this);
	 }
	public void clickSaveAddPeople()
	{
	
		driver.findElement(SaveAddPeople).click();
	}
	
	public void fillAttendee(String attendeeEmail)
	{
		driver.findElement(AddPeople).sendKeys();
	}
	
	public void clickSuggestion()
	{
		driver.findElement(SelectFirstSuggestion).click();
	}
}
