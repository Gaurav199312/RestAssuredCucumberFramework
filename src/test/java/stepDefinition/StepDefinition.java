package stepDefinition;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.AddPlace;
import pojoClasses.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepDefinition extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	JsonPath js;

	
	
	
	@Given("Add Place Payload with {string}  {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		res=given().spec(requestSpecification())
				.body(data.addPlacePayload(name, language, address));
	    
	}
	
	
	@When("User calls {string} with {string} Http request")
	public void user_calls_with_http_request(String resource, String method) 
	{
		APIResources resourceAPI=APIResources.valueOf(resource);
	   
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
			 response =res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			 response =res.when().get(resourceAPI.getResource());
		
	}
	
	
	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer code) 
	{
		
		assertEquals(response.getStatusCode(),200);        
		
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    
		
		assertEquals(getJsonPath(response,keyValue),expectedValue);
	  
	}
	
	@Then("verify placeId created is mapped to the {string} using {string}")
	public void verify_place_id_created_is_mapped_to_the_using(String expectedName, String resource) throws IOException
	{
		place_id=getJsonPath(response,"place_id").toString();
		res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName=getJsonPath(response,"name").toString();
		assertEquals(actualName,expectedName);
		System.out.println(place_id);

	}
	
	
	
	
	//deletePlace
	@Given("deletePlace payload")
	public void delete_place_payload() throws IOException {
		System.out.println(place_id);
		res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));

	}
	
	
	


}
