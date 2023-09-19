package com.basereh.springlibrary;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature", extraGlue = "com.basereh.springlibrary")
@DataJpaTest
class LibraryCucumberIntegrationTest {
}
