package com.personal.outbound.frame;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampaignManager {

    public WebDriver driver;

    public CampaignManager(WebDriver driver) {
        this.driver = driver;
    }

    public void addCampaignIsDisplay(){
        Assert.assertTrue(driver.findElement(By.id("add")).isDisplayed());
    }

    public void navigate(){

    }

}
