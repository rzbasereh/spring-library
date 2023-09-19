package com.basereh.springlibrary.stepdefs;

import com.basereh.springlibrary.controller.dto.PublisherDto;
import com.basereh.springlibrary.util.RestApiUtil;
import com.basereh.springlibrary.util.ScenarioData;
import com.basereh.springlibrary.util.ScenarioException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class PublisherSteps {
    private final ScenarioData scenarioData;
    private final ScenarioException scenarioException;
    private final RestApiUtil restApiUtil;
    private final ObjectMapper objectMapper;

    @When("user adds the {string} publisher with name={string}")
    public void user_adds_publisher_with_name_(String paramName, String pubName) {
        MockHttpServletResponse response = restApiUtil.postRequest(
                "/publishers",
                getRequestBody(PublisherDto.builder().name(pubName).build())).getResponse();
        scenarioData.add(paramName, mapResponseToDto(response));
    }

    @When("user gets all publishers with {string} result")
    public void user_gets_all_publishers_with_(String paramName) {
        List<PublisherDto> allPublishers = mapResponseToDtoList(restApiUtil.getRequest("/publishers").getResponse());
        scenarioData.add(paramName, allPublishers);
    }

    @When("user gets {string} publisher")
    public void user_gets_publisher(String paramName) {
        Long id = ((PublisherDto) scenarioData.get(paramName)).getId();
        MvcResult mvcResult = restApiUtil.getRequest("/publishers/" + id);
        if (mvcResult.getResolvedException() == null) {
            scenarioData.add(paramName, mapResponseToDto(mvcResult.getResponse()));
        } else {
            scenarioException.setException(mvcResult.getResolvedException(), mvcResult.getResponse().getStatus());
        }
    }

    @When("user updates the {string} publisher to name={string}")
    public void user_updates_publisher_to_(String paramName, String newPubName) {
        Long currPublisherId = ((PublisherDto) scenarioData.get(paramName)).getId();
        PublisherDto updatedPublisher = PublisherDto.builder().id(currPublisherId).name(newPubName).build();

        MvcResult mvcResult = restApiUtil.putRequest("/publishers/" + currPublisherId, getRequestBody(updatedPublisher));
        if (mvcResult.getResolvedException() == null) {
            scenarioData.add(paramName, mapResponseToDto(mvcResult.getResponse()));
        } else {
            scenarioException.setException(mvcResult.getResolvedException(), mvcResult.getResponse().getStatus());
        }
    }

    @When("user deletes the {string} publisher")
    public void user_deletes_(String paramName) {
        restApiUtil.deleteRequest("/publishers/" + ((PublisherDto) scenarioData.get(paramName)).getId());
    }

    @Then("the {string} publisher is exist with desired properties")
    public void the_publisher_is_exist_with_desired_properties(String paramName) {
        PublisherDto expectedPublisher = scenarioData.get(paramName);
        PublisherDto actualPublisher = mapResponseToDto(
                restApiUtil.getRequest("/publishers/" + expectedPublisher.getId()).getResponse());

        assertThat(expectedPublisher).usingRecursiveComparison().withStrictTypeChecking().isEqualTo(actualPublisher);
    }

    @Then("the {string} publisher is deleted from system")
    public void the_publisher_is_deleted_from_system(String paramName) {
        Long pubId = ((PublisherDto) scenarioData.get(paramName)).getId();
        Integer expectedStatusCode = restApiUtil.getRequest("/publishers/" + pubId).getResponse().getStatus();
        assertThat(404).isEqualTo(expectedStatusCode);
    }

    @Then("all {string} publishers are exist as expected")
    public void all_publishers_are_exist_as_expected(String paramName) {
        List<PublisherDto> actual = scenarioData.get(paramName);
        List<PublisherDto> expected = scenarioData.getAll(PublisherDto.class);
        assertThat(actual).hasSameElementsAs(expected);
    }

    private PublisherDto mapResponseToDto(MockHttpServletResponse response) {
        try {
            return objectMapper.readValue(response.getContentAsString(), PublisherDto.class);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PublisherDto> mapResponseToDtoList(MockHttpServletResponse response) {
        try {
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<>() {
            });
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject getRequestBody(PublisherDto body) {
        JSONObject requestParams = new JSONObject();
        try {
            requestParams.put("id", body.getId());
            requestParams.put("name", body.getName());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return requestParams;
    }
}
