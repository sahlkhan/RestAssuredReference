package sahil;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
public class Patch_Rest_Reference {

		public static void main(String[] args) {
				//Declare the base url
				RestAssured.baseURI="https://reqres.in/";
				
				// Declare the request body
				String requestBody="{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}" ;
				//Declare the expected result
				JsonPath JspRequest = new JsonPath(requestBody);
				String req_name=JspRequest.getString("name");
				String req_job=JspRequest.getString("job");
				LocalDateTime currentdate=LocalDateTime.now();
				String expecteddate=currentdate.toString().substring(0,11);
				
				// Declare the given,when,then method
				String responseBody=given().header("Content-Type","application/json").body(requestBody).
						when().patch("api/users/2").then().extract().response().asString();
				//System.out.println(responseBody);
				
				//create of an object of JSON path to parse response body
				JsonPath JspResponse = new JsonPath(responseBody);
				String res_name=JspResponse.getString("name");
				String res_job=JspResponse.getString("job");
				System.out.println(res_name);
				System.out.println(res_job);
				String res_updatedAt=JspResponse.getString("updatedAt");
				res_updatedAt=res_updatedAt.substring(0,11);
				
				// Validate response body parameter
				Assert.assertEquals(res_name,req_name);
				Assert.assertEquals(res_job,req_job);
				Assert.assertEquals(res_updatedAt,expecteddate);
	}

}
