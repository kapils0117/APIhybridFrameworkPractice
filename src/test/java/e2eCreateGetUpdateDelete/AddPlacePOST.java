package e2eCreateGetUpdateDelete;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class AddPlacePOST {

	//JSON object response we need to convert into String format to get it print , method = toString()
	@Test
	public void add_Place(ITestContext context) {
		
		
		// Initialize Faker instance
        Faker faker = new Faker();
    	RestAssured.baseURI="https://rahulshettyacademy.com";
        // Create JSON Object for location with hardcoded values
        JSONObject location = new JSONObject();
        location.put("lat", -38.383494);
        location.put("lng", 33.427362);

        // Create String array for types
        String[] types = {"shoe park", "shop"};

        // Convert the string array to JSONArray
        JSONArray typesArray = new JSONArray(types);

        // Create JSON Object for the entire request payload using Faker for dynamic data
        JSONObject requestBody = new JSONObject();
        requestBody.put("location", location);
        requestBody.put("accuracy", 50);
        requestBody.put("name", faker.company().name()); // Random company name
        requestBody.put("phone_number", faker.phoneNumber().phoneNumber()); // Random phone number
        requestBody.put("address", faker.address().fullAddress()); // Random address
        requestBody.put("types", typesArray); // Adding the string array as a JSONArray
        requestBody.put("website", faker.internet().url()); // Random website URL
        requestBody.put("language", faker.country().name() + "-" + faker.country().countryCode2()); // Random language and country code

        // Print the request body to verify
        System.out.println(requestBody.toString());
        
        

Response responses = given().log().all()
.queryParam("key", "qaclick123")
.header("Content-Type", "application/json")
.body(requestBody.toString())
.when().post("maps/api/place/add/json")
.then().assertThat().statusCode(200)
.extract().response();
      
//Print the response as a string
String responseString = responses.asString();
System.out.println("Response Body: " + responseString);

JsonPath js= new JsonPath(responseString);
String placesId=js.get("place_id");
System.out.println("Place_id is" +"" +placesId);
         //context.setAttribute("place_id", placesId); // This attribute works only if all classes are in same Test inside testNG xml file
	
context.getSuite().setAttribute("place_id", placesId); // For testng XML2 - to run at suite level when classes are in different methods, this will work at suite level if classess are in different test inside testNG.xml file
	}
	
	
}
