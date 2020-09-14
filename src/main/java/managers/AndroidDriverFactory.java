package managers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidDriverFactory {

	/**
	 * Prepare Appium Driver
	 * @return
	 * @throws IOException 
	 */
	public static AndroidDriver<AndroidElement> getDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"/Config.properties");
		prop.load(ip);
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("device", prop.getProperty("device"));
		caps.setCapability("os_version", prop.getProperty("os_version"));
		caps.setCapability("project", prop.getProperty("project"));
		caps.setCapability("build", prop.getProperty("build"));
		caps.setCapability("name", prop.getProperty("name"));
		caps.setCapability("app", prop.getProperty("key"));
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("https://"+prop.getProperty("userName")+":"+prop.getProperty("accessKey")+"@hub-cloud.browserstack.com/wd/hub"), caps);
		return driver;
	}

}
