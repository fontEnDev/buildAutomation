package com.personal.outbound.steps;

import com.personal.outbound.lab.EnvSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CampaignStepsDefs extends Context{

    Context context;

    public CampaignStepsDefs(Context context){
        this.context = context;
        this.driver = context.driver;
        this.CampaignManagerObj = context.CampaignManagerObj;
        this.CampaignStrategysObj = context.CampaignStrategysObj;
    }

    @Given("The Campaign URL is hit")
    public void campaignURLHit() throws InterruptedException {
        System.out.println("Go to campaign URL");
        driver.get(EnvSetup.baseUrl);
        Thread.sleep(10000);
        System.out.println("hellpo");
    }
}
