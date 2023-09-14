package com.basereh.springlibrary.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

public class PublisherSteps {
    @LocalServerPort
    private int port;

    private Map<String, Response> responseMap;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @When("I try to create {string} with name {string}")
    public void i_try_to_create_publisher_with_name(String publisher, String name) {
        try {
            JSONObject data = new JSONObject();
            data.put("name", name);
            RequestSpecification request = RestAssured.given();
            request.body(data.toString());

            Response res = request.post("/publishers");
            res.getBody().print();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("I should get publisher in response")
    public void getPublisherWithName() {

    }
}
