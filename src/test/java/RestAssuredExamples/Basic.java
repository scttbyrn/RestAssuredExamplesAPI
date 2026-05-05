package RestAssuredExamples;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*; //Static for given()
import static org.hamcrest.Matchers.*; // Static for equalTo()

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class Basic {
	
//	static JsonPath js;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		**README**
		//		given()  //all input details
		//		when() //Submit API Http Methods e.g.(Get, Post, Put, Delete)
		//		Then() //Validate the response, Output details
		//		.log().all(). // can be place in given() and Then()
		//		.assertThat() // use to assert validation, always use in then()


		// validate if Add Place API is workimg as expected 
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response

		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		/**********************************************************************************************/	
		//		//Response format:
		//		Response response = given()//gives all the elements need for request
		//						.log()
		//						.all()
		//						.queryParam("key", "qaclick123")
		//						.header("Content-Type","application/json")
		//						.body(payload.AddPlace())//input payload into body
		//
		//						//RESTful action to be done e.g.(GET,POST,PUT,PATCH,DELETE)
		//						.when()
		//						.post("maps/api/place/add/json")
		//						
		//						//validate the response
		//						.then().log().all()
		//						.assertThat()
		//						.statusCode(200)
		//						.body("scope", equalTo("APP"))
		//						.header("server", "Apache/2.4.52 (Ubuntu)")
		//						.extract().response();
		/**********************************************************************************************/

		//POST PLACE:
		//Response as String format:
		String response = given()//gives all the elements need for request
				.log()
				.all()
				.queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body(payload.AddPlace())//input payload into body

				//RESTful action to be done e.g.(GET,POST,PUT,PATCH,DELETE)
				.when()
				.post("maps/api/place/add/json")

				//validate the response
				.then()
				.assertThat()
				.statusCode(200)
				.body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().prettyPrint();
		//	System.out.println(response); //parse or convert the Json response into string

		/**********************************************************************************************/		
		//Convert String Json into JsonPath
		JsonPath js = new JsonPath(response); 
		String placeId = js.getString("place_id");
		System.out.println("Filter the place_id on the Response: "+placeId);
		/**********************************************************************************************/

		//UPDATE PLACE:
		String newAddress = "Summer Walk, Africa";

		String updatePlace = given()
				.log().all()
				.queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.UpdatePlace(placeId, newAddress))

				.when()
				.put("maps/api/place/update/json")

				.then().log().all()
				.assertThat()
				.statusCode(200)
				.body("msg", equalTo("Address successfully updated"))
				.extract()
				.response().prettyPrint();
		//		System.out.println(updatePlace); 		


		//GET PLACE:
		String getPlace = given()
		.queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		
		.when()
		.get("maps/api/place/get/json")
		
		.then()
		.assertThat()
		.log().all()
		.statusCode(200)
		.extract()
		.response().prettyPrint();
		
		JsonPath gplace = new JsonPath(getPlace); 
		String gplaceID = gplace.getString("address");
		System.out.println("Filter Validate Place: "+gplaceID);
		
		Assert.assertEquals(newAddress, gplaceID); //Validate if the Successfully Update the address on the E2E process.
		

		//		//Get Place
		//
		//		String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
		//				.queryParam("place_id",placeId)
		//				.when().get("maps/api/place/get/json")
		//				.then().assertThat().log().all().statusCode(200).extract().response().asString();
		//		JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse);
		//		String actualAddress =js1.getString("address");
		//		System.out.println(actualAddress);
		//		Assert.assertEquals(actualAddress, "Pacific ocean");
		//		//Cucumber Junit, Testng




	}

}
