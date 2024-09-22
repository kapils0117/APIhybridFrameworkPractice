package e2eCreateGetUpdateDelete;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class PutAPI {
	
	@Test
	public void putPlace(ITestContext context) {
	// Initialize Faker instance
    Faker faker = new Faker();
	RestAssured.baseURI="https://rahulshettyacademy.com";
    // Create JSON Object for location with hardcoded values
    JSONObject location = new JSONObject();
    location.put("lat", -40.383494);
    location.put("lng", 44.427362);

    // Create String array for types
    String[] types = {"Shastri park", "rent"};

    // Convert the string array to JSONArray
    JSONArray typesArray = new JSONArray(types);

    // Create JSON Object for the entire request payload using Faker for dynamic data
    JSONObject requestBody = new JSONObject();
    requestBody.put("location", location);
    requestBody.put("accuracy", 60);
    requestBody.put("name", faker.company().name()); // Random company name
    requestBody.put("phone_number", faker.phoneNumber().phoneNumber()); // Random phone number
    requestBody.put("address", faker.address().fullAddress()); // Random address
    requestBody.put("types", typesArray); // Adding the string array as a JSONArray
    requestBody.put("website", faker.internet().url()); // Random website URL
    requestBody.put("language", faker.country().name() + "-" + faker.country().countryCode2()); // Random language and country code

    // Print the request body to verify
    System.out.println(requestBody.toString());
    
    
	// String placesId =  (String) context.getAttribute("place_id");
		String placesId =  (String) context.getSuite().getAttribute("place_id");//For testng XML2 - to run at suite level when classes are in different methods
	 
Response responses = given().log().all()
.queryParam("key", "qaclick123")
.header("Content-Type", "application/json")
.body(requestBody.toString())
.when().post("maps/api/place/update/json")
.then().assertThat().statusCode(200)
.extract().response();
  
//Print the response as a string
String responseString = responses.asString();
System.out.println("Response Body: " + responseString);

JsonPath js= new JsonPath(responseString);

}
	
}
