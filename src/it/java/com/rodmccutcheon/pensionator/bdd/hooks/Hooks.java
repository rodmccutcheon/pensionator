package com.rodmccutcheon.pensionator.bdd.hooks;

import com.rodmccutcheon.pensionator.bdd.config.CucumberConfig;
import com.rodmccutcheon.pensionator.bdd.pageobjects.HeaderPageFragment;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CucumberConfig.class)
@SpringBootTest
public class Hooks {

    @Autowired
    private HeaderPageFragment headerPageFragment;

    @Autowired
    private DataSource datasource;

    @Before
    public void setup() throws SQLException {
        System.out.println("Cleaning up database");
        ScriptUtils.executeSqlScript(datasource.getConnection(), new ClassPathResource("test-data.sql"));
    }

    @After
    public void logout() {
        headerPageFragment.logout();
    }
}
