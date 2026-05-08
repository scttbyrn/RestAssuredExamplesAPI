package serializationPOJO;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class AddGoogleMap {

	@Test
	public void addGoogleMap() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

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

		/***************************************************************/
		//Serialization POJO

		Response google = given()
				.log().all()
				.queryParam("key", "qaclick123")
				.body(googleMapBody) //call Parent POJO Object class here

				.when()
				.post("/maps/api/place/add/json")

				.then().log().all()
				.assertThat().statusCode(200)
				.extract().response()
				.prettyPeek();

		/***************************************************************/
		


	}

}
