package apireqests;

import java.io.FileInputStream;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiBase {

	public int responseCode;
	private String Base_URL = "http://api.weatherbit.io/v2.0/forecast/daily?";//key=6029d0acab83492e874cd4bdd71aaa5e&lat=40.730610&lon=-73.935242";

	public void prepareAPI(String queryParams ) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"/Config.properties");
		prop.load(ip);
		String key = prop.getProperty("key");
		Base_URL=Base_URL+"key="+key+"&city="+queryParams;
	}
	public Response triggerRequest(String queryParams) throws IOException, JSONException
	{
		prepareAPI(queryParams);
		RestAssured.baseURI=Base_URL;
		Response s=  given().get(Base_URL);
		s.then().assertThat().statusCode(200).body(("city_name"), equalTo(queryParams.split(",")[0]));
		return s;
	} 
}
