package deserializationPOJO;

public class GetCoursePOJO { //This class is Parent POJO
	
	/**
	 * README:
	 * 
	 * Deserialization is to convert payload JSON Response into Classes - POJO Classes.
	 * 
	 * Please include/download "jackson-databind" on pom.xml
	 * 
	 * ***************************************************
	 * 
	 * To create automatic getter and setter.
	 * 
	 * Step 1: Highlight the variables
	 * 
	 * Step 2: press Shift + Alt + S
	 * 
	 * This will generate getters and setters.
	 * 
	 * ***************************************************
	 * This Class also the parent of Nested JSON Structure
	 * 
	 * ***************************************************
	 *  
	 *  To fulfill this POJO function run the OAuthCourse.java
	 * 
	 * **/
	
	private String instructor, url, services, expertise, linkedIn;
	private CoursesPOJO courses; //This return to Classes that link for nested JSON structure

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public CoursesPOJO getCourses() {
		return courses;
	}

	public void setCourses(CoursesPOJO courses) {
		this.courses = courses;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	

}


