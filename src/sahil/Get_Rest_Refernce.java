package sahil;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;


public class Get_Rest_Refernce {

	public static void main(String[] args) {
		// Declare the base Url
		RestAssured.baseURI="https://reqres.in/";
		//Declare the requestbody
		
		//Declare given,when and then method
		
		String responseBody=given().header("Content-type","Application/json").body("requestbody").
				when().get("api/users?page=2").then().extract().response().asString();
		System.out.println(responseBody);
	}

}
