package stepdfn;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.fasterxml.jackson.databind.ObjectMapper;

import apireqests.ApiBase;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.CommonFunctionLib;


public class GetWeather {

	CommonFunctionLib cfl= new CommonFunctionLib();
	public ApiBase ab= new ApiBase();
	String verificationText;
	String city;
	Response s;
	List<Map<String,String>>resultList=new ArrayList();
	Map<String,String>tempMap=new HashMap<String,String>();
	List<Map<String,String>>dateAndTemp= new ArrayList();
	List<LocalDate>thursdayDates= new ArrayList();
	Map<LocalDate,String>transientMap=new HashMap<LocalDate,String>();
	List<LocalDate> outingDate= new ArrayList();;
	List<LocalDate>noRainWednesday= new ArrayList();
	ObjectMapper oMapper = new ObjectMapper();
	DecimalFormat df = new DecimalFormat("#");
	List<LocalDate>wednesdayDatesDates= new ArrayList();

	Map<String, String> weatherMap = new HashMap<String, String>();


	@Given("^I like to holiday in \"([^\"]*)\"$")
	public void setHolidayDestination(String val) throws IOException
	{
		city=val;
	}

	@And("^I only like to holiday on Thursday$")
	public void getThursdayDate() throws Exception
	{
		thursdayDates=cfl.getThursdayDate();
	}

	@And("^Check if it has rained previous days$")
	public void getPreviousDayRainResult() throws IOException, JSONException
	{
		boolean noRain=true;
		for(LocalDate s:thursdayDates)
		{
			wednesdayDatesDates.add(s.minusDays(1));
		}
		for(LocalDate d:wednesdayDatesDates)
		{
			for(Map<String,String> m:resultList )
			{
				if(m.get("datetime").equalsIgnoreCase(d.toString()))
				{
					Object obj = m.get("weather");
					weatherMap = oMapper.convertValue(obj, Map.class);
					if(!((weatherMap.get("description").contains("rain"))||(weatherMap.get("description").contains("snow"))))
					{
						noRain=false;
						noRainWednesday.add(d);
					}
				}
			}
		}
		assertFalse("Rain or snow is happening on wednesday, cannot go on vacation",noRain);
	}

	@When("^I look up the weather forecast for the next \"([^\"]*)\" days$")
	public void getWeatherForecast(String val) throws IOException, JSONException
	{
		s=ab.triggerRequest(city);
		Map<String, String>result;
		df.setRoundingMode(RoundingMode.CEILING);
		//		for(Map<String,String> m:resultList )
		//		{
		for(int i=0;i< s.jsonPath().getList("data").size()-2;i++)
		{
			result= new HashMap<String, String>();
			result.putAll((Map<? extends String, ? extends String>) s.jsonPath().getList("data").get(i));
			resultList.add(result);
		}
	}

	@Then("^I can see the temperature is between \"([^\"]*)\" to \"([^\"]*)\" degrees in Bondi Beach$")
	public void getTemperature(String val1, String val2) throws IOException
	{
		df.setRoundingMode(RoundingMode.CEILING);
		for(Map<String,String> m:resultList )
		{
			for(LocalDate d:thursdayDates)
			{ 
				if( m.get("datetime").equalsIgnoreCase(d.toString()) )
				{
					if((Integer.parseInt(df.format(m.get("low_temp")))<Integer.parseInt(val1))&&
							(Integer.parseInt(df.format(m.get("max_temp")))<Integer.parseInt(val2)))
					{
						outingDate.add(d);
					}
				}
			}
		}
		//verify that atleast one thursday exist in the specified temperature range
		assertFalse("No Thursday with specified temperature found", outingDate.isEmpty());

		for(LocalDate e :noRainWednesday)
		{
			noRainWednesday.set(noRainWednesday.indexOf(e), e.plusDays(1));
		}
		outingDate.retainAll(noRainWednesday);
		//Verify that consecutive wednesday and Thursday exist under specified range
		assertFalse("No Thursday with specified temperature found", outingDate.isEmpty());
	}

	@And("^I can see that it won't be snowing for the next \"([^\"]*)\" days$")
	public void verifyResult(String val)
	{
		for(Map<String,String> m:resultList )
		{
			Object obj = m.get("weather");
			weatherMap = oMapper.convertValue(obj, Map.class);
			//verify no rain in next 14 days
			assertFalse("It is either raining or snowing on "+m.get("valid_date"),(weatherMap.get("description").contains("rain"))||(weatherMap.get("description").contains("snow")));
		}

	}
}
