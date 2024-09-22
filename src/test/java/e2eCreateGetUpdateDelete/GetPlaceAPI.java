package e2eCreateGetUpdateDelete;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.RestAssured;

public class GetPlaceAPI {

	@Test
	public void get_Place(ITestContext context) {
    	

    	RestAssured.baseURI="https://rahulshettyacademy.com";
    	// String placesId =  (String) context.getAttribute("place_id");
    	String placesId =  (String) context.getSuite().getAttribute("place_id"); // For testng XML2 - to run at suite level when classes are in different methods
    	
   Response rs= 	given().log().all()
    	.queryParam("key", "qaclick123").queryParam("place_id", placesId)
    	.header("Content-Type", "application/json")
    	.when().get("maps/api/place/get/json")
    	.then().assertThat().statusCode(200)
    	.extract().response();
   
   String getres= rs.asString();
   System.out.println("Get response is: " +""+getres);
    	
		
		
		
		
		
		
	}
	
}
