package stepDefinitions;

import Resource.APIResources;
import Resource.TestDataBuild;
import Resource.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.Assert.assertEquals;


public class StepDefinitions extends Utils {
    RequestSpecification res;
    ResponseSpecification responspec;
    Response response;
    TestDataBuild dt = new TestDataBuild();


    @Given("Create token with {string} and {string}")
    public void createTokenWithAnd(String user, String pass) throws IOException {
        res = given().spec(reqCreateToken()).body(dt.createToken(user, pass));

    }

    @Given("Create booking with {string},{string},{int},{string}")
    public void createBookingWith(String firstname, String lastname, int price, String additionalneeds) throws IOException {
        res = given().spec(requestCreateBooking()).body(dt.createBooking(firstname, lastname, price, additionalneeds));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);


        if (method.equalsIgnoreCase("POST") && resource.equalsIgnoreCase("CreateToken")) {
            response = res.when().post(resourceAPI.getResource());
            token = getJsonPath(response, "token");
            System.out.println(token);
        } else if (method.equalsIgnoreCase("POST"))
            response = res.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = res.when().get(resourceAPI.getResource());

    }

    @Then("the API call got success with status code {int}")
    public void theAPICallGotSuccessWithStatusCode(int codeRespone) {
        response.then().spec(responseSpecification(codeRespone));
        //assertEquals(codeRespone, response.getStatusCode());
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("the API call got status code is {int}")
    public void theAPICallGotStatusCodeIsStatus_code(int codeRespone) {
        assertEquals(codeRespone, response.getStatusCode());
    }

    @And("verify booking_id created maps to lastname {string} using {string}")
    public void verifyBooking_idCreatedMapsToLastnameUsing(String param, String resource) throws IOException {
        if (response.getStatusCode() == 200) {
            String booking_id = getJsonPath(response, "bookingid");
            res = given().spec(requestCreateBooking());
            APIResources resourceAPI = APIResources.valueOf(resource);
            response = res.when().get(resourceAPI.getResource() + booking_id);
            String actualName = getJsonPath(response, "lastname");
            assertEquals(param, actualName);
        }
    }

    @And("verify booking_id created maps to firstname {string} using {string}")
    public void verifyBooking_idCreatedMapsToFirstnameUsing(String param, String resource) throws IOException {
        if (response.getStatusCode() == 200) {
            String booking_id = getJsonPath(response, "bookingid");
            res = given().spec(requestCreateBooking());
            APIResources resourceAPI = APIResources.valueOf(resource);
            response = res.when().get(resourceAPI.getResource() + booking_id);
            String actualName = getJsonPath(response, "firstname");
            assertEquals(param, actualName);
        }
    }
}
