package com.basereh.springlibrary;

import com.basereh.springlibrary.util.ScenarioData;
import com.basereh.springlibrary.util.ScenarioException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public ScenarioData getScenarioData() {
        return new ScenarioData();
    }

    @Bean
    public ScenarioException getScenarioException() {
        return new ScenarioException();
    }
}
