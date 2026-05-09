package deserializationPOJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
public class OAuthCourse {

	@Test
	public void GetCourseDetails() {

		/**README
		 * 
		 * on this class will do the full Deserialization POJO function
		 * 
		 * **/

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


		/**Inject POJO Function**/
		//Input access_token to GetCourseDetails : GET
		GetCoursePOJO getCourseDetails = given()// This line convert the response into POJO structure
				.queryParam("access_token", accessToken )

				.when().log().all()
				.get("getCourseDetails").as(GetCoursePOJO.class);

		//Get instructor:
		String instructor = getCourseDetails.getInstructor();
		System.out.println("Instructor: " +instructor);

		//Get URL:
		String url = getCourseDetails.getUrl();
		System.out.println("URL: " +url);


		//Get Course Web Automation Title and Price:
		String webAutomationCourse = getCourseDetails.getCourses().getWebAutomation().get(2).getCourseTitle();
		String webAutomationPrice = getCourseDetails.getCourses().getWebAutomation().get(2).getPrice();
		System.out.println("Course Title: " +webAutomationCourse);
		System.out.println("Course Price: " +webAutomationPrice);

		//Get Course API Title and Price:
		String apiCourse = getCourseDetails.getCourses().getApi().get(1).getCourseTitle();
		String apiPrice = getCourseDetails.getCourses().getApi().get(1).getPrice();

		System.out.println("Course Title: " +apiCourse);
		System.out.println("Course Price: " +apiPrice);

		/***************************************************************************************************/	

		//Iterate data of Title and Price of Web Automation Course:	
		for (int i = 0; i<getCourseDetails.getCourses().getWebAutomation().size(); i++ ) {

			if (getCourseDetails.getCourses().getWebAutomation().get(i).getCourseTitle().equalsIgnoreCase("Cypress")) {

				System.out.println("Iterate data of Title and Price of Web Automation Courses.");

				String iterateWebAutomation = getCourseDetails.getCourses().getWebAutomation().get(i).getCourseTitle();
				String iterateWebAutomationPrice = getCourseDetails.getCourses().getWebAutomation().get(i).getPrice();
				System.out.println("Course Title: " +iterateWebAutomation);
				System.out.println("Course Price: " +iterateWebAutomationPrice);

				break;
			}

		}

		/***************************************************************************************************/		

		//Iterate data of Title and Price of API Course w/assertion using Array and ArrayList:


		//this Array is only to stored fixed number of data and it cannot be increment during run.
		String[] courseTitles = {"Rest Assured Automation using Java","SoapUI Webservices testing"}; 

		//This object is to store dynamically increment of API courses title of Response.
		ArrayList<String> storeApiCourse = new ArrayList<String>(); 

		//This List is to get all of the API courses title of the Response and the return value of this List is API class.
		List<deserializationPOJO.API> getCurrentCourse = getCourseDetails.getCourses().getApi();

		for(int j=0;j < getCurrentCourse.size(); j++)//iterate
		{

			//this line is to add all data of the API courses title response and store in storeApiCourse ArrayList
			storeApiCourse.add(getCurrentCourse.get(j).getCourseTitle());
		}

		//this object List is to convert the courseTitles Array to asList to easy validate with the getCurrentCourse List
		List<String> expectedList =	Arrays.asList(courseTitles);

		System.out.println("Expected Default Courses Title: "+expectedList);
		System.out.println("Retrieve data from response during run execution: "+storeApiCourse);

		Assert.assertTrue(storeApiCourse.equals(expectedList));

	}

}
