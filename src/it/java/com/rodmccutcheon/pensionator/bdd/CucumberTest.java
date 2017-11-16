package com.rodmccutcheon.pensionator.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/it/resources/features")
@SpringBootTest
public class CucumberTest {


}
