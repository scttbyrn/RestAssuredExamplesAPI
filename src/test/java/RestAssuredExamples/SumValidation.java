package RestAssuredExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	/**README
	 * 
	 * In this class defines how to handle mock data to use when the API is undergoing of Development
	 * this class also help you to parallel doing testing while the API isnt finished in Development
	 * 
	 * This also show below how to handle complex JSON structure with computation of total of copies and sum of the Book sales.
	 * 
	 * 
	 * */
	
	@Test
	public void SumTotalCopiesAndSold() {
		
		JsonPath js = new JsonPath(payload.ComputeCourses());
		
		//get the count of courses
		int courses = js.getInt("courses.size()");
		System.out.println("Number of Courses: "+courses);
		
		//Iterate and Sum of all the total copies
				int sum = 0;
				int sales = 0;
				
				for ( int i = 0; i<courses; i++) {
					
					//Course Name
					String coursename = js.getString("courses["+i+"].title");
					System.out.println("Course Title: " + coursename);
					
					//Course Price
					int price = js.getInt("courses["+i+"].price");
					System.out.println("Course Copy: " + price);
					
					//Course Copy
					int copies = js.getInt("courses["+i+"].copies");
					System.out.println("Course Copy: " + copies);
					
					sales = price * copies; //Sum of total sales per course
					System.out.println("Total Copies Sold of "+coursename+": " +sales);
					
					sum = sum + sales; //Sum of total sales overall courses

				}
				
				System.out.println("Total Copies Sold in The Course: " +sum);
				
				//Get the amount of Purchase Amount
				int purchaseAmount = js.getInt("dashboard.purchaseAmount");
				System.out.println("Total of Purchase Amount: "+purchaseAmount);
				
				Assert.assertEquals(purchaseAmount, sum);
					
		
	}

}
