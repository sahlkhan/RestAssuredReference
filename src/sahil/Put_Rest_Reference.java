package sahil;
import java.time.LocalDateTime;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class Put_Rest_Reference {

	public static void main(String[] args) {
		//Declare base url
		RestAssured.baseURI="https://reqres.in/";
		//declare request body
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		//Declare the expected result
		JsonPath JspRequestBody = new JsonPath(requestBody);
		String req_name=JspRequestBody.getString("name");
		String req_job=JspRequestBody.getString("job");
		LocalDateTime currentdate=LocalDateTime.now();
		String expecteddate=currentdate.toString().substring(0,11);
		//extract request 
		String responseBody = given().header("Content-Type","application/json").body(requestBody).when().put("api/users/2").then().extract().response().asString();
		//System.out.println(responseBody);
		//create a object Json path to parse the responsebody
		JsonPath JspResponse = new JsonPath(responseBody);
		String res_name=JspResponse.getString("name");
		String res_job=JspResponse.getString("job");
		System.out.println(res_name);
		System.out.println(res_job);
		String res_updatedAt=JspResponse.getString("updatedAt");
		res_updatedAt=res_updatedAt.substring(0,11);
		//validate the responseBody parameters
		Assert.assertEquals(res_name,req_name);
		Assert.assertEquals(res_job,req_job);
		Assert.assertEquals(res_updatedAt,expecteddate);
	}

}
