package serializationPOJO;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilders {

	@Test
	public void specBuilderSamples() {
		
		/***************************Scenario 1*********************************************/
		// On this section is to optimize code for reusable of request and response using SpecBuilder.

		GoogleMapPOJO googleMapBody = new GoogleMapPOJO();

		Location location = new Location(); //create  Class object of child POJO class to set value of Nested JSON location. 
		location.setLat(-38.383494);
		location.setLng(33.427362);
		googleMapBody.setLocation(location);

		googleMapBody.setAccuracy(50);
		googleMapBody.setName("Scott");
		googleMapBody.setPhone_number("(+91) 983 893 3937");
		googleMapBody.setAddress("Brgy. Tibay");

		List<String> addList = new ArrayList<String>();//create List object of Nested Array. 
		addList.add("shoe park");
		addList.add("shop");
		googleMapBody.setTypes(addList);

		googleMapBody.setWebsite("http://google.com");
		googleMapBody.setLanguage("French");
		
		/********************************************************************************/
		//Create RequestSpecBuilder object to use reusable data
		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.build();

		//Create ResponseSpecBuilder object to use reusable data
		ResponseSpecification resSpec = new ResponseSpecBuilder() 
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

		Response response = given().spec(reqSpec).body(googleMapBody)
				.when().post("/maps/api/place/add/json")
				.then().spec(resSpec).extract().response().prettyPeek();

		String id = response.jsonPath().getString("id");
		System.out.println("Extract ID: " +id);
		
		
		/***************************Scenario 2*********************************************/
		// on this section its dissect in to two parts to connect actualReq on then() response
		
		RequestSpecification actualReq = given().spec(reqSpec).body(googleMapBody);
		
		Response response2 = actualReq.when().post("/maps/api/place/add/json")
				
				.then().spec(resSpec).extract().response();

		String reference = response2.jsonPath().getString("reference");
		System.out.println("Extract Reference: " +reference);

	}

}
