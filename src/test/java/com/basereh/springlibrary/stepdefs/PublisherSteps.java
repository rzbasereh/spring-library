package com.basereh.springlibrary.stepdefs;

import com.basereh.springlibrary.controller.dto.PublisherDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherSteps {
    private final Map<String, Object> scenarioData =new HashMap<>();

    @Before
    public void cleanup() {
        scenarioData.clear();
    }

    @When("user adds {string} with name={string}")
    public void user_adds_publisher_with_name_(String paramName, String pubName) {
        RequestSpecification request = getJsonRequest(PublisherDto.builder().name(pubName).build());

        scenarioData.put(paramName, request.post("/publishers").as(PublisherDto.class));
    }

    @When("user gets all publishers with {string} result")
    public void user_gets_all_publishers_with_(String paramName) {
        List<PublisherDto> allPublishers = getJsonRequest().get("/publishers").as(new TypeRef<>() {
        });
        scenarioData.put(paramName, allPublishers);
    }

    @When("user updates {string} to name={string}")
    public void user_updates_publisher_to_(String paramName, String newPubName) {
        PublisherDto currPublisher = (PublisherDto) scenarioData.get(paramName);
        PublisherDto updatedPublisher = PublisherDto.builder().id(currPublisher.getId()).name(newPubName).build();
        RequestSpecification request = getJsonRequest(updatedPublisher);

        request.put("/publishers/" + currPublisher.getId());
        scenarioData.put(paramName, PublisherDto.builder().id(currPublisher.getId()).name(newPubName).build());
    }

    @When("user deletes {string}")
    public void user_deletes_(String paramName) {
        RequestSpecification request = getJsonRequest();
        request.delete("/publishers/" + ((PublisherDto) scenarioData.get(paramName)).getId());
    }

    @Then("the {string} is exist with desired properties")
    public void the_publisher_is_exist_with_desired_properties(String paramName) {
        PublisherDto expectedPublisher = (PublisherDto) scenarioData.get(paramName);
        PublisherDto actualPublisher = getJsonRequest()
                .get("/publishers/" + expectedPublisher.getId()).as(PublisherDto.class);

        assertThat(expectedPublisher).usingRecursiveComparison().withStrictTypeChecking().isEqualTo(actualPublisher);
    }

    @Then("the {string} is deleted from system")
    public void the_publisher_is_deleted_from_system(String paramName) {
        Long pubId = ((PublisherDto) scenarioData.get(paramName)).getId();
        Integer expectedStatusCode = getJsonRequest().get("/publishers/" + pubId).getStatusCode();

        assertThat(404).isEqualTo(expectedStatusCode);
    }

    @Then("all {string} publishers are exist as expected")
    public void all_publishers_are_exist_as_expected(String paramName) {
        List<PublisherDto> actual = (List<PublisherDto>) scenarioData.get(paramName);
        List<PublisherDto> expected = scenarioData.entrySet().stream()
                .filter(entry -> PublisherDto.class.isAssignableFrom(entry.getValue().getClass()))
                .map(entry -> (PublisherDto) entry.getValue())
                .toList();
        assertThat(actual).hasSameElementsAs(expected);
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
