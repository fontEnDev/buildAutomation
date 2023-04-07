package com.personal.outbound.steps;
import com.personal.outbound.lab.EnvSetup;
import io.cucumber.java.af.En;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.jmeter.JMeter;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JMeterAutomation extends Context {

    Context context;
    protected String pathReportFile = "";
    public JMeterAutomation(Context context){
        this.commonFunction = context.commonFunction;
    }

    @When("Performance testing with jmeter file {string}")
    public void loadAndRunFile(String pathJmeterFile) throws IOException {
        System.out.println("Performance testing with Jmeter");
        StandardJMeterEngine jmeter = new StandardJMeterEngine();
        File jmxFile = new File(EnvSetup.SUITE_PATH + pathJmeterFile);
        HashTree testPlanTree = SaveService.loadTree(jmxFile);
        jmeter.configure(testPlanTree);
        jmeter.run();
    }

    @Then("Verify system work correctly with file report {string} and request {string}")
    public void verifyPerformance(String pathFileReport, String requestName){
        pathReportFile = EnvSetup.SUITE_PATH + pathFileReport;
        List<String> statusRequest = commonFunction.getValueColumnOfFile(pathReportFile, "success");
        List<String> failureMessage = commonFunction.getValueColumnOfFile(pathReportFile, "failureMessage");
        for(int i = 0; i < statusRequest.size(); i++){
            Assert.assertTrue("Request is not successfull with failure message: " + failureMessage.get(i),
                    Boolean.parseBoolean(statusRequest.get(i)));
        }
    }

    @Given("Go to Jmeter folder and load properties file")
    public void goToJmeterFolderAndLoadPropertiesFile() {
        System.out.println("Go to jmeter folder and load properties file");
        File jmeterHome = new File(EnvSetup.JMETER_HOME);
        JMeterUtils.setJMeterHome(jmeterHome.getAbsolutePath());
        JMeterUtils.loadJMeterProperties(jmeterHome + "/bin/jmeter.properties");
        JMeterUtils.initLocale();
    }

    @And("Remove file {string} if it exits")
    public void removeFileIfItExits(String filePath) {
        System.out.println("Go to jmeter folder and load properties file");
        pathReportFile = EnvSetup.SUITE_PATH + filePath;
        File csvFile = new File(pathReportFile);
        if (csvFile.exists()) {
            csvFile.delete();
        }
    }
}