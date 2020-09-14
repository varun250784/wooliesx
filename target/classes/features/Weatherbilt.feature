Feature: Verify weatherbilt apis

@api
Scenario Outline: Get the weather condition
	Given I like to holiday in "<city>"
	And I only like to holiday on Thursday
	When I look up the weather forecast for the next "<x>" days
	And Check if it has rained previous days	
	Then I can see the temperature is between "<min>" to "<max>" degrees in Bondi Beach
	And I can see that it won't be snowing for the next "<x>" days	
	
		Examples:
		|city|x|min|max|
		|Bondi,AU|14|20|30|  


