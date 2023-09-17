package com.basereh.springlibrary.stepdefs;

import com.basereh.springlibrary.repository.AuthorRepository;
import com.basereh.springlibrary.repository.BookRepository;
import com.basereh.springlibrary.repository.PublisherRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.server.LocalServerPort;

@RequiredArgsConstructor
public class CommonSteps {
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @LocalServerPort
    private Integer port;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @When("clear database")
    public void clear_database() {
        publisherRepository.deleteAll();
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }
}
