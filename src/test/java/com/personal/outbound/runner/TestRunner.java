package com.personal.outbound.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/feature"},
        glue = {"com.personal.outbound.steps"}
)
public class TestRunner {
}
