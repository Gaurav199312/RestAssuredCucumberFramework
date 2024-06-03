package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@delete")
	public void beforeScenario() throws IOException
	{
		StepDefinition s1=new StepDefinition();
		
		if(StepDefinition.place_id==null)
		{
			s1.add_place_payload_with("Baston", "Dutch", "London");
			s1.user_calls_with_http_request("AddPlaceAPI", "post");
			s1.verify_place_id_created_is_mapped_to_the_using("Baston", "getPlaceAPI");
		}
		
		
	}

}
