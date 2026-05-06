package RestAssuredExamples;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 * README:
		 * 
		 * In this class help you to create and validate mock payload data while the real API is undergoing on 
		 * development, this will help you to do parallel development of automation API with the Devs.
		 * 
		 * This Class also defines how to handle Complex Json structure.
		 * 
		 */
		
		JsonPath js = new JsonPath(payload.ComputeCourses());
		
		//get the count of courses
		int courses = js.getInt("courses.size()");
		System.out.println("Number of Courses: "+courses);
		
		//extract the purchase amount in the dashboard
		int purchaseamount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount: "+purchaseamount);
		
		//extract the course price in the course
		int courseprice = js.getInt("courses[0].price");
		System.out.println("Course Price: "+courseprice);
		
		//extract the course title in the course
		String coursename = js.getString("courses[0].title");
		System.out.println("Course Title: "+coursename);
		
		/****************************************************************************/
		
		//Iterate the content of courses
		for ( int i = 0; i<courses; i++) {
			
			//extract the course title in the course
			String totalcoursename = js.getString("courses["+i+"].title");
			System.out.println("Course Title: "+totalcoursename);
			
			//extract the course price in the course
			int totalprice = js.getInt("courses["+i+"].price");
			System.out.println("Course Price: "+totalprice);
			
		}
		
		/****************************************************************************/
		
		//Iterate and filter the Specific course title
		System.out.println("This is section of Filtering Specific Course Title: ");
		for ( int i = 0; i<courses; i++) {
			
			String totalcoursename = js.getString("courses["+i+"].title");
					
			if (totalcoursename.equalsIgnoreCase("RPA")) {
				
				
				
				//extract the course title in the course
				System.out.println("Course Title: "+totalcoursename);
				
				//extract the course price in the course
				int totalprice = js.getInt("courses["+i+"].price");
				System.out.println("Course Price: "+totalprice);
				
				break;
				
			}
			

			
		}
		
		/****************************************************************************/
		
		
		
		
			
	}

}
