package e2eCreateGetUpdateDelete;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DeletePlaceAPI {

	 @Test
	    public void delete_Place(ITestContext context) {

	        // Set the base URI
	        RestAssured.baseURI = "https://rahulshettyacademy.com";
	      //  String placesId =  (String) context.getAttribute("place_id");
	    	String placesId =  (String) context.getSuite().getAttribute("place_id");//For testng XML2 - to run at suite level when classes are in different methods
	        
	        // Create the JSON payload for the DELETE request
	        String deletePayload = "{\n" +
	                "\"place_id\":\""+placesId+"\"\n" +
	                "}";

	        
	    	 

	        // Perform the DELETE request
	        Response responsed = given().log().all()
	                .queryParam("key", "qaclick123")
	                .header("Content-Type", "application/json")
	                .body(deletePayload) // Add the payload
	                .when().delete("maps/api/place/delete/json") // DELETE method
	                .then().log().all()
	                .assertThat().statusCode(200) // Assert that the status code is 200
	                .extract().response(); // Extract the response

	        // Print the response body
	        String responseString = responsed.asString();
	        System.out.println("Response Body: " + responseString);
	    }
	
}
