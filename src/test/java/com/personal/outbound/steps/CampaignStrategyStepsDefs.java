package com.personal.outbound.steps;

import com.personal.outbound.lab.EnvSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    }


    @And("Add new campaign strategy")
    public void addNewCampaignStrategy() {
        System.out.println("Click add new stratgy button");
        System.out.println("Strategy load successful");
    }


    @And("Get Price of Course {string} on Table")
    public void getPriceOnTable(String course) {
        String xpath = "//table[@name='courses']//td[contains(text(),'%s')]//following-sibling::td";
        String price = driver.findElement(By.xpath(String.format(xpath, course))).getText();
        System.out.println("price of course : " + price);
    }
}