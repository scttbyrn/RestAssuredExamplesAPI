package deserializationPOJO;

import java.util.List;

public class CoursesPOJO { //This class is child of GetCoursePOJO
	
	private List<WebAutomation> webAutomation; //This return to Classes that link for nested JSON structure and set to this as List for input incrementing data.
	private List<API> api; //This return to Classes that link for nested JSON structure and set to this as List for input incrementing data.
	private List<Mobile> mobile; //This return to Classes that link for nested JSON structure and set to this as List for input incrementing data.

	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	public List<API> getApi() {
		return api;
	}

	public void setApi(List<API> api) {
		this.api = api;
	}

	public List<Mobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	
	

}
