Serialization - is to set JSON data on the payload request body using Serialization POJO classes.

Serialization POJO Heirachy
**Parent Class:
-GoogleMapPOJO.java

**Child Class:
-Location.java

**To Execute Serialization POJO Function
-AddGoogleMap.java


Keypoints:
-private Location location; //if nested JSON, create return Class object (Location.java)

		Location location = new Location(); //create  Class object of child POJO class to set value of Nested JSON location. 
		location.setLat(-38.383494);
		location.setLng(33.427362);
		googleMapBody.setLocation(location);


-List<String> types; //if nested Array, create return List object

		List<String> addList = new ArrayList<String>();//create List object of Nested Array. 
		addList.add("shoe park");
		addList.add("shop");
		googleMapBody.setTypes(addList);
		
		
/********************************************************************************************/

SpecBuilder - its use to optimize the reusable similar(generic) data for given() and then() datas.

Examples:

-Resquest

		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.build();

-Response

		ResponseSpecification resSpec = new ResponseSpecBuilder() 
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();



- Output

	given().spec(reqSpec).body(googleMapBody)
	
	.when().post("/maps/api/place/add/json")
	
	.then().spec(resSpec).extract().response().prettyPeek();





		
