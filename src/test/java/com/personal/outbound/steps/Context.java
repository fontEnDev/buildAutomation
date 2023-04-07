package com.personal.outbound.steps;

import com.personal.outbound.frame.CampaignManager;
import com.personal.outbound.frame.CampaignStrategys;
import com.personal.outbound.frame.CommonFunction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;

public class Context {
    public WebDriver driver;
    public HashMap<String, String> testData;
    public CampaignManager CampaignManagerObj;

    public CampaignStrategys CampaignStrategysObj;

    public CommonFunction commonFunction;
}
