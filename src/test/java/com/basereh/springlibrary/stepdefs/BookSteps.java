package com.basereh.springlibrary.stepdefs;

import com.basereh.springlibrary.controller.dto.AuthorDto;
import com.basereh.springlibrary.controller.dto.BookDto;
import com.basereh.springlibrary.controller.dto.PublisherDto;
import com.basereh.springlibrary.util.RestApiUtil;
import com.basereh.springlibrary.util.ScenarioData;
import com.basereh.springlibrary.util.ScenarioException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class BookSteps {
    private final ScenarioData scenarioData;
    private final ScenarioException scenarioException;
    private final RestApiUtil restApiUtil;
    private final ObjectMapper objectMapper;


    @ParameterType("(?:[^,]*)(?:,\\s?[^,]*)*")
    public List<String> listOfStrings(String arg) {
        return Arrays.asList(arg.split(",\\s?"));
    }

    @When("user adds {string} book with name={string}, publisher={string}, authors={listOfStrings}")
    public void user_adds_book_with_name_(
            String paramName,
            String name,
            String pubParamName,
            List<String> authorParamNames
    ) {
        PublisherDto publisher = scenarioData.get(pubParamName);
        Set<AuthorDto> authors = authorParamNames.stream()
                .map(aName -> (AuthorDto) scenarioData.get(aName))
                .collect(Collectors.toSet());
        MockHttpServletResponse response = restApiUtil.postRequest(
                "/books",
                getRequestBody(BookDto.builder()
                        .name(name)
                        .publisher(publisher)
                        .authors(authors)
                        .build()
                )).getResponse();

        scenarioData.add(paramName, mapResponseToDto(response));
    }

    @When("user gets all books with {string} result")
    public void user_gets_all_books_with_(String paramName) {
        List<BookDto> allBooks = mapResponseToDtoList(restApiUtil.getRequest("/books").getResponse());
        scenarioData.add(paramName, allBooks);
    }

    @When("user gets the {string} book")
    public void user_gets_book(String paramName) {
        BookDto book = scenarioData.get(paramName);
        MvcResult mvcResult = restApiUtil.getRequest("/books/" + book.getId());
        if (mvcResult.getResolvedException() == null) {
            scenarioData.add(paramName, mapResponseToDto(mvcResult.getResponse()));
        } else {
            scenarioException.setException(mvcResult.getResolvedException(), mvcResult.getResponse().getStatus());
        }
    }

    @When("user updates the {string} book to name={string}, publisher={string}, authors={listOfStrings}")
    public void user_updates_book_to_(
            String paramName,
            String newName,
            String newPubParamName,
            List<String> newAuthorParamNames) {
        BookDto currBook = scenarioData.get(paramName);
        PublisherDto newPublisher = scenarioData.get(newPubParamName);
        Set<AuthorDto> newAuthors = newAuthorParamNames.stream()
                .map(aName -> (AuthorDto) scenarioData.get(aName))
                .collect(Collectors.toSet());
        BookDto updatedBook = BookDto.builder()
                .id(currBook.getId())
                .name(newName)
                .publisher(newPublisher)
                .authors(newAuthors)
                .build();

        MvcResult mvcResult = restApiUtil.putRequest("/books/" + currBook.getId(), getRequestBody(updatedBook));
        if (mvcResult.getResolvedException() == null) {
            scenarioData.add(paramName, mapResponseToDto(mvcResult.getResponse()));
        } else {
            scenarioException.setException(mvcResult.getResolvedException(), mvcResult.getResponse().getStatus());
        }
    }

    @When("user deletes the {string} book")
    public void user_deletes_(String paramName) {
        BookDto book = scenarioData.get(paramName);
        restApiUtil.deleteRequest("/books/" + book.getId());
    }

    @Then("the {string} book is exist with desired properties")
    public void the_book_is_exist_with_desired_properties(String paramName) {
        BookDto expected = scenarioData.get(paramName);
        BookDto actual = mapResponseToDto(restApiUtil.getRequest("/books/" + expected.getId()).getResponse());

        assertThat(expected).usingRecursiveComparison().withStrictTypeChecking().isEqualTo(actual);
    }

    @Then("the {string} book is deleted from system")
    public void the_book_is_deleted_from_system(String paramName) {
        BookDto book = scenarioData.get(paramName);
        Integer expectedStatusCode = restApiUtil.getRequest("/books/" + book.getId()).getResponse().getStatus();
        assertThat(404).isEqualTo(expectedStatusCode);
    }

    @Then("all the {string} books are exist as expected")
    public void all_books_are_exist_as_expected(String paramName) {
        List<BookDto> actual = scenarioData.get(paramName);
        List<BookDto> expected = scenarioData.getAll(BookDto.class);
        assertThat(actual).hasSameElementsAs(expected);
    }

    private BookDto mapResponseToDto(MockHttpServletResponse response) {
        try {
            return objectMapper.readValue(response.getContentAsString(), BookDto.class);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<BookDto> mapResponseToDtoList(MockHttpServletResponse response) {
        try {
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<>() {
            });
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject getRequestBody(BookDto body) {
        JSONObject requestParams = new JSONObject();
        try {
            requestParams.put("id", body.getId());
            requestParams.put("name", body.getName());
            requestParams.put("publisher", getRequestBody(body.getPublisher()));

            JSONArray authors = new JSONArray();
            body.getAuthors().forEach(author -> authors.put(getRequestBody(author)));
            requestParams.put("authors", authors);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return requestParams;
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
