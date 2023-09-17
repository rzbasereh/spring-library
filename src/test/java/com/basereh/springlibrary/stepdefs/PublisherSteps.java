package com.basereh.springlibrary.stepdefs;

import com.basereh.springlibrary.controller.dto.PublisherDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PublisherSteps {
    @LocalServerPort
    private int port;

    private Map<String, PublisherDto> publishers;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        publishers = new HashMap<>();
    }

    @When("user create {string} with name {string}")
    public void user_create_publisher_with_name_(String paramName, String pubName) {
        RequestSpecification request = getJsonRequest();
        JSONObject requestParams = new JSONObject();
        try {
            requestParams.put("name", pubName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.body(requestParams.toString());
        publishers.put(paramName, request.post("/publishers").then().extract().as(PublisherDto.class));
    }

    @When("user change {string} name to {string}")
    public void user_update_publisher_name_to_(String paramName, String newPubName) {
        RequestSpecification request = getJsonRequest();
        JSONObject requestParams = new JSONObject();
        PublisherDto curPublisher = publishers.get(paramName);
        try {
            requestParams.put("id", curPublisher.getId());
            requestParams.put("name", newPubName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.body(requestParams.toString());
        publishers.put(paramName, request.put("/publishers/" + curPublisher.getId())
                .then()
                .extract()
                .as(PublisherDto.class));
    }

    @When("user delete {string}")
    public void user_delete_(String paramName) {
        RequestSpecification request = getJsonRequest();
        PublisherDto curPublisher = publishers.get(paramName);
        request.delete("/publishers/" + curPublisher.getId());
    }

    @Then("{string} is exist")
    public void publisher_is_exist(String paramName) {
        assertTrue(publishers.containsKey(paramName));
    }

    @Then("name of {string} is {string}")
    public void name_of_publisher_is_(String paramName, String expectedPubName) {
        String actualPubName = publishers.get(paramName).getName();
        assertEquals(expectedPubName, actualPubName);
    }

    private RequestSpecification getJsonRequest() {
        return RestAssured.given().header("Content-Type", "application/json");
    }
}
