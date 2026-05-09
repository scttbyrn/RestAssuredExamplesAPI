package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath rawToJson(String response){

		/**
		 * This method is to convert JSON easily the whole payload into String value
		 * This is very useful for multiple validation of the payload body
		 * 
		 * */
		
		JsonPath js1 =new JsonPath(response);
		return js1;
	}


}
