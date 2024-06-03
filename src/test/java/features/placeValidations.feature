Feature: Validation of place apis

@add
Scenario Outline: Verify if place is being added using AddPlace api
Given Add Place Payload with "<name>"  "<language>" "<address>"
When User calls "AddPlaceAPI" with "POST" Http request
Then the API call got success with status code 200
And "scope" in response body is "APP"
And "status" in response body is "OK"
And verify placeId created is mapped to the "<name>" using "getPlaceAPI"

Examples:

|name 	 | language |address		       |
|AAhouse |  English |World cross center|
|BBhouse | Spanish  |Sea cross center  |
|CChouse | French   |Blue cross center  |

@delete
Scenario: To verify the deletePlace functionality

Given deletePlace payload
When User calls "deletePlaceAPI" with "POST" Http request
Then the API call got success with status code 200
And "status" in response body is "OK"
