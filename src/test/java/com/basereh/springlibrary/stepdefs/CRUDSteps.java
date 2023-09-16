package com.basereh.springlibrary.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

public class CRUDSteps {
    @LocalServerPort
    private int port;

    private Response response;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }
    
    @When("sending {string} to {string}")
    public void sending_request_to_path(String requestType, String path) {    
    	RequestSpecification request = getJsonRequest();    	
    	response = request.request(requestType, path);
    }
    
    @When("sending {string} to {string} with")
    public void sending_request_to_path_with_(String requestType, String path, List<Map<String, String>> body) {    
    	RequestSpecification request = getJsonRequest();
  
    	JSONObject requestParams = new JSONObject(); 
    	try {
    		for (var entry: body.get(0).entrySet()) {
    			requestParams.put(entry.getKey(), entry.getValue());
    		}
		} catch (JSONException e) {
			e.printStackTrace();
		} 
    	request.body(requestParams.toString());
    	
    	response = request.request(requestType, path);
    }

    @Then("the HTTP status code should be {int}")
    public void the_http_status_code_should_be_(Integer statusCode) {
    	assertEquals(response.getStatusCode(), statusCode);
    }
    
    @Then("the HTTP response body should be")
    public void the_http_response_body_should_be_(List<Map<String, String>> body) {
    	JsonPath jsonPathBody = response.then().assertThat().extract().jsonPath();
    	for (var entry: body.get(0).entrySet()) {
    		assertEquals(entry.getValue(), jsonPathBody.get(entry.getKey()).toString());
    	}
    }
    
    private RequestSpecification getJsonRequest() {
    	return RestAssured.given().header("Content-Type", "application/json");
    }
}
