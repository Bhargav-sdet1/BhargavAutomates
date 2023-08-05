package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import allPojoClasses.Login;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utils;

public class loginrsasd extends Utils{
	
	RequestSpecification req;
	RequestSpecification loginReqest;
	Response response;
	
	@Given("RSA login API")
	public void rsa_login_api() throws IOException {
		
		Login login = new Login();
		login.setUserEmail("nov9@yopmail.com");
		login.setUserPassword("Asdfg1@34");
		loginReqest =given().spec(reqSpecific())
				.body(login);
		
	}
	@When("User calls {string} API with POST http request")
	public void user_calls_api_with_post_http_request(String string) {
		response =loginReqest.when().post("/api/ecom/auth/login")
				.then().extract().response();
	}
	@Then("the call got success with status code {string}")
	public void the_call_got_success_with_status_code(String string) {
	   assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
	   String resp= response.asString();
	   JsonPath js = new JsonPath(resp);
	   assertEquals(js.get(string),string2);
	}
}
