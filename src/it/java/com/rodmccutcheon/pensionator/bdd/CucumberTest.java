package com.rodmccutcheon.pensionator.bdd;

import com.rodmccutcheon.pensionator.PensionatorApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/it/resources/features")
@SpringBootTest(classes = PensionatorApplication.class)
public class CucumberTest {


}
