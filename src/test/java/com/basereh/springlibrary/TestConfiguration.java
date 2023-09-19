package com.basereh.springlibrary;

import com.basereh.springlibrary.util.ScenarioData;
import com.basereh.springlibrary.util.ScenarioException;
import io.cucumber.spring.ScenarioScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    @ScenarioScope
    public ScenarioData getScenarioData() {
        return new ScenarioData();
    }

    @Bean
    @ScenarioScope
    public ScenarioException getScenarioException() {
        return new ScenarioException();
    }
}
