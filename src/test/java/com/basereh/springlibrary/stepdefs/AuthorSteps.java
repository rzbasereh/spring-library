package com.basereh.springlibrary.stepdefs;

import com.basereh.springlibrary.controller.dto.AuthorDto;
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
public class AuthorSteps {
    private final ScenarioData scenarioData;
    private final ScenarioException scenarioException;
    private final RestApiUtil restApiUtil;
    private final ObjectMapper objectMapper;

    @When("user adds {string} author with firstname={string}, lastname={string}")
    public void user_adds_author_with_name_(String paramName, String firstname, String lastname) {
        MockHttpServletResponse response = restApiUtil.postRequest(
                "/authors",
                getRequestBody(AuthorDto.builder().firstname(firstname).lastname(lastname).build())
        ).getResponse();
        scenarioData.add(paramName, mapResponseToDto(response));
    }

    @When("user gets all authors with {string} result")
    public void user_gets_all_authors_with_(String paramName) {
        List<AuthorDto> allPublishers = mapResponseToDtoList(restApiUtil.getRequest("/authors").getResponse());
        scenarioData.add(paramName, allPublishers);
    }

    @When("user gets the {string} author")
    public void user_gets_author(String paramName) {
        Long id = ((AuthorDto) scenarioData.get(paramName)).getId();
        MvcResult mvcResult = restApiUtil.getRequest("/authors/" + id);
        if (mvcResult.getResolvedException() == null) {
            scenarioData.add(paramName, mapResponseToDto(mvcResult.getResponse()));
        } else {
            scenarioException.setException(mvcResult.getResolvedException(), mvcResult.getResponse().getStatus());
        }
    }

    @When("user updates the {string} author to firstname={string}, lastname={string}")
    public void user_updates_author_to_(String paramName, String newFirstname, String newLastname) {
        Long currAuthorId = ((AuthorDto) scenarioData.get(paramName)).getId();
        AuthorDto updatedAuthor = AuthorDto.builder()
                .id(currAuthorId)
                .firstname(newFirstname)
                .lastname(newLastname)
                .build();

        MvcResult mvcResult = restApiUtil.putRequest("/authors/" + currAuthorId, getRequestBody(updatedAuthor));
        if (mvcResult.getResolvedException() == null) {
            scenarioData.add(paramName, mapResponseToDto(mvcResult.getResponse()));
        } else {
            scenarioException.setException(mvcResult.getResolvedException(), mvcResult.getResponse().getStatus());
        }
    }

    @When("user deletes the {string} author")
    public void user_deletes_(String paramName) {
        restApiUtil.deleteRequest("/authors/" + ((AuthorDto) scenarioData.get(paramName)).getId());
    }

    @Then("the {string} author exists with firstname={string}, lastname={string}")
    public void the_author_is_exist_with_desired_properties(
            String paramName,
            String expectedFirstname,
            String expectedLastname
    ) {
        AuthorDto publisher = scenarioData.get(paramName);
        AuthorDto actualPublisher = mapResponseToDto(restApiUtil
                .getRequest("/authors/" + publisher.getId()).getResponse());

        assertThat(actualPublisher.getFirstname()).isEqualTo(expectedFirstname);
        assertThat(actualPublisher.getLastname()).isEqualTo(expectedLastname);
    }

    @Then("the {string} author does not exist")
    public void the_publisher_is_deleted_from_system(String paramName) {
        Long pubId = ((AuthorDto) scenarioData.get(paramName)).getId();
        Integer actualStatusCode = restApiUtil.getRequest("/authors/" + pubId).getResponse().getStatus();
        assertThat(404).isEqualTo(actualStatusCode);
    }

    @Then("all the {string} authors exist as expected")
    public void all_authors_are_exist_as_expected(String paramName) {
        List<AuthorDto> actual = scenarioData.get(paramName);
        List<AuthorDto> expected = scenarioData.getAll().stream()
                .filter(o -> AuthorDto.class.isAssignableFrom(o.getClass()))
                .map(o -> (AuthorDto) o)
                .toList();
        assertThat(actual).hasSameElementsAs(expected);
    }

    private AuthorDto mapResponseToDto(MockHttpServletResponse response) {
        try {
            return objectMapper.readValue(response.getContentAsString(), AuthorDto.class);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<AuthorDto> mapResponseToDtoList(MockHttpServletResponse response) {
        try {
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<>() {
            });
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject getRequestBody(AuthorDto body) {
        JSONObject requestParams = new JSONObject();
        try {
            requestParams.put("id", body.getId());
            requestParams.put("firstname", body.getFirstname());
            requestParams.put("lastname", body.getLastname());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return requestParams;
    }
}
