package sahil;

import io.restassured.RestAssured;
import io.restassured.path.xml.*;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class soap_rest_reference {

	public static void main(String[] args) {
		// Declare base url
		RestAssured.baseURI="https://www.dataaccess.com/";
		//Declare the request body
		String requestBody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>1</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		//Extract response body
		String responseBody=given().header("Content-Type","text/xml; charset=utf-8").body(requestBody).
				    when().post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
		System.out.println(responseBody);
		//parse the responseBody
		XmlPath Xmlresponse = new XmlPath(responseBody);
		String responseParameter = Xmlresponse.getString("NumberToWordsResult");
		System.out.println(responseParameter);
		//validate response body
		Assert.assertEquals(responseParameter,"one ");
	}

}
