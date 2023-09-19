package com.basereh.springlibrary.stepdefs;

import com.basereh.springlibrary.repository.AuthorRepository;
import com.basereh.springlibrary.repository.BookRepository;
import com.basereh.springlibrary.repository.PublisherRepository;
import com.basereh.springlibrary.util.ScenarioException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RequiredArgsConstructor
public class CommonSteps {
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ScenarioException scenarioException;

    @Given("clear database")
    public void clear_database() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Then("user gets an error with status={int}")
    public void user_gets_an_error_with_status_(Integer expectedStatus) {
        Integer actualStatus = scenarioException.getException().status();
        assertThat(actualStatus).isEqualTo(expectedStatus);
    }
}
