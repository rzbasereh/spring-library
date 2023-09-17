package com.basereh.springlibrary.stepdefs;

import com.basereh.springlibrary.controller.dto.PublisherDto;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherSteps {
    private final Map<String, PublisherDto> publishers = new HashMap<>();

    @When("user adds {string} with name={string}")
    public void user_create_publisher_with_name_(String paramName, String pubName) {
        RequestSpecification request = getJsonRequest(PublisherDto.builder().name(pubName).build());

        publishers.put(paramName, request.post("/publishers").then().extract().as(PublisherDto.class));
    }

    @When("user updates {string} to name={string}")
    public void user_update_publisher_name_to_(String paramName, String newPubName) {
        PublisherDto currPublisher = publishers.get(paramName);
        PublisherDto updatedPublisher = PublisherDto.builder().id(currPublisher.getId()).name(newPubName).build();
        RequestSpecification request = getJsonRequest(updatedPublisher);

        request.put("/publishers/" + currPublisher.getId());
        publishers.put(paramName, PublisherDto.builder().id(currPublisher.getId()).name(newPubName).build());
    }

    @When("user deletes {string}")
    public void user_delete_(String paramName) {
        RequestSpecification request = getJsonRequest();
        request.delete("/publishers/" + publishers.get(paramName).getId());
    }

    @Then("the {string} is exist with desired properties")
    public void publisher_exists(String paramName) {
        PublisherDto expectedPublisher = publishers.get(paramName);
        PublisherDto actualPublisher = getJsonRequest().get("/publishers/" + expectedPublisher.getId())
                .then().extract().as(PublisherDto.class);

        assertThat(expectedPublisher).usingRecursiveComparison().withStrictTypeChecking().isEqualTo(actualPublisher);
    }

    @Then("the {string} is deleted from system")
    public void the_publisher_is_deleted_from_system(String paramName) {
        Long pubId = publishers.get(paramName).getId();
        Integer expectedStatusCode = getJsonRequest().get("/publishers/" + pubId).getStatusCode();

        assertThat(404).isEqualTo(expectedStatusCode);
    }

    private RequestSpecification getJsonRequest() {
        return RestAssured.given().header("Content-Type", "application/json");
    }

    private RequestSpecification getJsonRequest(PublisherDto body) {
        RequestSpecification request = getJsonRequest();
        JSONObject requestParams = new JSONObject();
        try {
            requestParams.put("id", body.getId());
            requestParams.put("name", body.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.body(requestParams.toString());
        return request;
    }
}
