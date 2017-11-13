package com.rodmccutcheon.pensionator.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/it/resources/features")
public class CucumberTest {

}
