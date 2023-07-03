package sahil;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;

public class Post_Rest_Referenc {

	public static void main(String[] args) {
	//declare the base url
	RestAssured.baseURI="https://reqres.in/";
	//declare the request body string variable
	String requestBody="{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}"; 
	//Declare the expected result
	JsonPath JspRequest = new JsonPath(requestBody);
	String req_name=JspRequest.getString("name");
	String req_job=JspRequest.getString("job");
	LocalDateTime currentdate=LocalDateTime.now();
	String expecteddate=currentdate.toString().substring(0,11);
	//declare the given ,when and then method(response body fetching)
	//String responseBody=given().header("Content-Type","application/json").body(requestBody).log().all().
			//when().post("api/users").then().log().all().extract().response().asString();
	String responseBody=given().header("Content-Type","application/json").body(requestBody).
			when().post("api/users").then().extract().response().asString();
	//System.out.println(responseBody);
	//create a object Json path to parse the responsebody
	JsonPath JspResponse = new JsonPath(responseBody);
	String res_name=JspResponse.getString("name");
	String res_job=JspResponse.getString("job");
	String res_createdAt=JspResponse.getString("createdAt");
	System.out.println(res_name);
	System.out.println(res_job);
	res_createdAt=res_createdAt.substring(0,11);
	//validate the responseBody parameters
	Assert.assertEquals(res_name,req_name);
	Assert.assertEquals(res_job,req_job);
	Assert.assertEquals(res_createdAt,expecteddate);
	}

}
