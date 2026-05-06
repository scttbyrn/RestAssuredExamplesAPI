package RestAssuredExamples;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*; // Static for equalTo()

import files.payload;


public class DynamicJson {
	
	
	@Test (dataProvider = "getBookData")
	public void addBook(String isbn, String aisle) throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		//Input Book Data
		Response addbook = given()
		.header("Content-Type","application/json")
		.body(payload.addBook(isbn, aisle))
				
		.when()
		.post("Library/Addbook.php")
		
		.then()
		.log().all()
		.assertThat().statusCode(200)
		.extract().response();
		
		String id = addbook.jsonPath().getString("ID");
		System.out.println("Generate Book ID: "+id);
		
		//Delete Book Data
		given()
		.headers("Content-Type","application/json")
		.body(payload.deleteBook(id))
		
		.when()
		.post("/Library/DeleteBook.php")
		
		.then()
		.assertThat()
		.statusCode(200)
		.body("msg", equalTo("book is successfully deleted"));
		
		
///************************************************************************************************/
//		/**README
//		 * 
//		 * This section is to get the created json payload from external of the system with .json files
//		 * 
//		 * Examples:
//		 * Notepad ++
//		 * 
//		 * 
//		 * */
//		
//		Response addbook = given()
//		.header("Content-Type","application/json")
//		.body(new String (Files.readAllBytes(Paths.get("C:\\User\\Documents\\addplace.json"))))//This line is to get the payload from external .json files of the system
//				
//		.when()
//		.post("Library/Addbook.php")
//		
//		.then()
//		.log().all()
//		.assertThat().statusCode(200)
//		.extract().response();
//		
//		String id = addbook.jsonPath().getString("ID");
//		System.out.println(id);
//		/************************************************************************************************/		
	}
	
	
//	@Test(dependsOnMethods = "addBook")
//	public void deleteBook(String id) {
//		
//		//Delete Book Data
//		given()
//		.headers("Content-Type","application/json")
//		.body(payload.deleteBook(id))
//		
//		.when()
//		.post("/Library/DeleteBook.php")
//		
//		.then()
//		.assertThat()
//		.statusCode(200)
//		.body("msg", equalTo("book is successfully deleted"));
//		
//	}
	
	@DataProvider
	public Object[][] getBookData() {
		
		return new Object [][] {
			{"qwerty", "12345"},
			{"ytrewq", "54321"},
			{"poiuyt", "09876"}
		};
		
		
	}
	
//	@DataProvider
//	public Object[] deleteBookData() {
//		
//		return new Object [] {
//				"qwerty12345",
//				"ytrewq54321",
//				"poiuyt09876"
//				};
//		
//	}
	
	
	
	

}
