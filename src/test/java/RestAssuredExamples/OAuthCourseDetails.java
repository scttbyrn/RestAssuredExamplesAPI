package RestAssuredExamples;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
public class OAuthCourseDetails {
	
	@Test
	public void GetCourseDetails() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/";
		
		
		//Get Access Token from OAuth Server: POST
		Map<String, String> authorizationData = new HashMap<>();

		authorizationData.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
		authorizationData.put("client_secret", "erZOWM9g3UtwNRj340YYaK_W");
		authorizationData.put("grant_type", "client_credentials");
		authorizationData.put("scope", "trust");
		
		Response authorization = given()
		.formParams(authorizationData)
		
		.when().log().all()
		.post("oauth2/resourceOwner/token").prettyPeek();
		
		String accessToken = authorization.jsonPath().getString("access_token");
		
		
		//Input access_token to GetCourseDetails : GET
		Response getCourseDetails = given()
		.queryParam("access_token", accessToken )
		
		.when().log().all()
		.get("getCourseDetails").prettyPeek();
		
	}

}
