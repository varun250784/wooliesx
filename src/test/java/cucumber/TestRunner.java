package cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict=false,
plugin = {"pretty"}, monochrome = true,
features = "src/main/java/features",
glue={"stepdfn"},
tags="@api"
		)
public class TestRunner {

}
