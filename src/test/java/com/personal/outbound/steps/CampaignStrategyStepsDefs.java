package com.personal.outbound.steps;

import com.personal.outbound.lab.EnvSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class CampaignStrategyStepsDefs extends Context{


    Context context;

    public CampaignStrategyStepsDefs(Context context){
        this.context = context;
        this.driver = context.driver;
        this.CampaignManagerObj = context.CampaignManagerObj;
        this.CampaignStrategysObj = context.CampaignStrategysObj;
    }

    @Given("The Campaign Strategy URL is hit")
    public void theCampaignStrategyURLIsHit() {
        System.out.println("Go to Campaign Strategy Home page");
        driver.get(EnvSetup.baseUrl + "/watch?v=JsqNYCBL37o");
    }


    @And("Add new campaign strategy")
    public void addNewCampaignStrategy() {
        System.out.println("Click add new stratgy button");

        System.out.println("Strategy load successful");
    }

    }