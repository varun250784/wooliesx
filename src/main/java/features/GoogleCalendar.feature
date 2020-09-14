Feature: Create and verify Google meeting

@calendar
Scenario Outline: Get the weather condition
				Given I have launched the Calendar App
				When it is not a "non working day" 
				And meeting is not repeated on successive days
				Then I want to book a meeting with the title "Recurring-Team Catch Up"
				And Set Meeting duration as "3.15" in the evening and I invite "2" people
				And I save the meeting
				Then I check if the meeting is created as expected