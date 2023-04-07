package com.personal.outbound.steps;

import com.personal.outbound.frame.CampaignManager;
import com.personal.outbound.frame.CampaignStrategys;
import com.personal.outbound.frame.CommonFunction;
import com.personal.outbound.lab.EnvSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.HashMap;

public class Hook {
    Context context;

    public Hook(Context context){
        this.context = context;
    }

    @BeforeAll(order = 10)
    public static void loadEnv() {
        new EnvSetup();
    }


    @Before
    public void setUp(Scenario scenario) {
        System.out.println(scenario.getName());
        System.out.println(scenario.getLine());
        context.driver = EnvSetup.setupDriverInstance();;
        context.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        context.testData = new HashMap<>();
        context.CampaignManagerObj = new CampaignManager(context.driver);
        context.CampaignStrategysObj = new CampaignStrategys(context.driver);
        context.commonFunction = new CommonFunction();

    }

    @After
    public void closeBrowser(){
        System.out.println("Close browser");
        context.driver.close();
    }

}
