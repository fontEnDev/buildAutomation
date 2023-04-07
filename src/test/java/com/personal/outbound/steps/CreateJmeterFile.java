package com.personal.outbound.steps;

import io.cucumber.java.hu.Ha;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LogicControllerGui;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.extractor.BeanShellPostProcessor;
import org.apache.jmeter.extractor.json.jsonpath.JSONPostProcessor;
import org.apache.jmeter.extractor.json.jsonpath.gui.JSONPostProcessorGui;
import org.apache.jmeter.processor.PostProcessor;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.gui.HTTPArgumentsPanel;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;
import org.apache.jmeter.protocol.http.util.HTTPFileArg;
import org.apache.jmeter.protocol.http.util.HTTPFileArgs;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.reporters.gui.SummariserGui;
import org.apache.jmeter.sampler.DebugSampler;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testbeans.gui.TestBeanGUI;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.TestElementProperty;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.visualizers.ViewResultsFullVisualizer;
import org.apache.jorphan.collections.HashTree;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateJmeterFile extends Context {
    Context context;

    public CreateJmeterFile(Context context) {
        this.commonFunction = context.commonFunction;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:/Users/vvtruong/Downloads/ProjectTester/ProjectTester/AutoBuild/src/test/resources";
        String token = "";
        JMeterUtils.setJMeterHome(path+ "/apache-jmeter-5.5");
        JMeterUtils.loadJMeterProperties(path + "/apache-jmeter-5.5/bin/jmeter.properties");
        JMeterUtils.initLocale();


        TestPlan testPlan = new TestPlan();
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setName("Get_Token");


        Arguments arguments = new Arguments();
        arguments.setProperty(TestElement.GUI_CLASS, ArgumentsPanel.class.getName());
        arguments.setProperty(TestElement.TEST_CLASS, Arguments.class.getName());
        testPlan.setUserDefinedVariables(arguments);
        arguments.addArgument("contact_URL", "contacts-api.avaya-obaas.com");
        arguments.addArgument("Token", token);
        arguments.addArgument("Hello", "token", "=");
        testPlan.addParameter("Text", "Hello");


        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setName("get_Token");
        threadGroup.setScheduler(false);
        threadGroup.setDuration(5);
        threadGroup.setDelay(2);
        threadGroup.setNumThreads(10);
        threadGroup.setRampUp(10);
        threadGroup.setProperty("ThreadGroup.on_sample_error", "stoptest");
        threadGroup.setProperty("ThreadGroup.same_user_on_next_iteration", false);
        threadGroup.setProperty("ThreadGroup.delayedStart", "true");

        LoopController loopController = new LoopController();
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setLoops(1); // Set the number of loops to 1
        threadGroup.setSamplerController(loopController);

        HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
        httpSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        httpSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        httpSampler.setName("getToken");
        httpSampler.setComment("Request GET google.com");
        httpSampler.setMethod("POST");
        httpSampler.setProtocol("https");
        httpSampler.setDomain("dev-8.ixcc-sandbox.avayacloud.com");
        httpSampler.setPath("/auth/realms/system/protocol/openid-connect/token");
//        httpSampler.setAutoRedirects(false);
//        httpSampler.setFollowRedirects(true);
//        httpSampler.setUseKeepAlive(true);
//        httpSampler.setDoMultipart(true);
//        httpSampler.setDoBrowserCompatibleMultipart(true);
//        httpSampler.setPostBodyRaw(true);
//        Arguments arguments1 = new Arguments();
//        HTTPArgument httpArgument = new HTTPArgument();
//        httpArgument.setValue("{\r\n\t\"name\": \"Contactlist\",\r\n\t\"description\": \"description of contact list\"\r\n}");
//        httpArgument.setMetaData("=");
//        arguments1.addArgument(httpArgument);
//        httpSampler.setArguments(arguments1);

        Arguments arguments1 = new Arguments();
//        arguments1.addArgument(new HTTPArgument("grant_type", "client_credentials", "="));
//        arguments1.addArgument(new HTTPArgument("scope", "openid", "=", false));
        HTTPArgument argument1 = new HTTPArgument();
        argument1.setProperty(TestElement.GUI_CLASS, HTTPArgumentsPanel.class.getName());
        argument1.setProperty(TestElement.TEST_CLASS, HTTPArgument.class.getName());
        argument1.setName("Content-Type");
        argument1.setValue("application/x-www-form-urlencoded");
        argument1.setMetaData("=");
        argument1.setAlwaysEncoded(false);
        arguments1.addArgument(argument1);


        HTTPArgument argument2 = new HTTPArgument("grant_type", "client_credentials", "=");
        argument2.setAlwaysEncoded(false);
        arguments1.addArgument(argument2);

        HTTPArgument argument3 =new HTTPArgument("scope", "openid", "=", false);
        argument3.setAlwaysEncoded(false);
        arguments1.addArgument(argument3);

        HTTPArgument argument4 = new HTTPArgument();
        argument4.setName("client_secret");
        argument4.setValue("dn2ITSPPLLjSX9i6fA0QTIlDi5DcL7tZ");
        argument4.setMetaData("="); // Biến đổi cặp tên giá trị thành "tên=giá trị"
        arguments1.addArgument(argument4);

        HTTPArgument argument5 = new HTTPArgument();
        argument5.setName("client_id");
        argument5.setValue("obaas-admin");
        argument5.setMetaData("="); // Biến đổi cặp tên giá trị thành "tên=giá trị"
        arguments1.addArgument(argument5);

        //Add argument for https sample
        httpSampler.setArguments(arguments1);

        JSONPostProcessor jsonPostProcessor = new JSONPostProcessor();
        jsonPostProcessor.setProperty(TestElement.GUI_CLASS, JSONPostProcessorGui.class.getName());
        jsonPostProcessor.setProperty(TestElement.TEST_CLASS, JSONPostProcessor.class.getName());
        jsonPostProcessor.setName("getToke");
        jsonPostProcessor.setScopeAll();
        jsonPostProcessor.setComment("Automation");
        jsonPostProcessor.setRefNames("Bearer");
        jsonPostProcessor.setJsonPathExpressions("$.access_token");
        jsonPostProcessor.setDefaultValues("NOTFOUNDTOKEN");
        jsonPostProcessor.setMatchNumbers("1");

        BeanShellPostProcessor beanShellPostProcessor = new BeanShellPostProcessor();
        beanShellPostProcessor.setProperty(TestElement.GUI_CLASS, TestBeanGUI.class.getName());
        beanShellPostProcessor.setProperty(TestElement.TEST_CLASS, BeanShellPostProcessor.class.getName());
        beanShellPostProcessor.setName("saveToken");
        beanShellPostProcessor.setComment("add token to properties file");
        beanShellPostProcessor.setScript("${__setProperty(bearerToken,${Bearer})}");

        DebugSampler debugSampler = new DebugSampler();
        debugSampler.setProperty(TestElement.GUI_CLASS, TestBeanGUI.class.getName());
        debugSampler.setProperty(TestElement.TEST_CLASS, DebugSampler.class.getName());
        debugSampler.isDisplayJMeterProperties();
        debugSampler.isDisplayJMeterVariables();
        debugSampler.setProperty("displaySystemProperties", "true");
        debugSampler.setName("Debug variable");

        ResultCollector resultCollector = new ResultCollector();
//        ViewResultsFullVisualizer viewResultsFullVisualizer = new ViewResultsFullVisualizer();
        SampleSaveConfiguration sampleSaveConfiguration = new SampleSaveConfiguration();
//        viewResultsFullVisualizer.setFile("C:\\Users\\vvtruong\\Downloads\\ProjectTester\\ProjectTester\\AutoBuild\\src\\test\\resources\\report\\report1.csv");
        sampleSaveConfiguration.saveRequestHeaders();
        sampleSaveConfiguration.saveCode();
        sampleSaveConfiguration.saveLabel();
        sampleSaveConfiguration.saveThreadName();
        sampleSaveConfiguration.saveUrl();
        sampleSaveConfiguration.saveSuccess();
        sampleSaveConfiguration.saveResponseHeaders();
        sampleSaveConfiguration.saveFieldNames();

        resultCollector.setProperty("TestElement.gui_class", "ViewResultsFullVisualizer");
        resultCollector.setProperty("TestElement.test_class", "ResultCollector");
        String path1 = System.getProperty("user.dir") + "/src/test/resources/report/report1.csv" ;
        resultCollector.setFilename(new File(path1).toString());
        resultCollector.setSaveConfig(sampleSaveConfiguration);
        resultCollector.setName("report_1");

        Summariser summariser = new Summariser();
        summariser.setProperty(TestElement.GUI_CLASS, SummariserGui.class.getName());
        summariser.setProperty(TestElement.TEST_CLASS, Summariser.class.getName());
        summariser.setName("Summary_Report");

//        TestPlan testPlan1 = new TestPlan();
//        testPlan1.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
//        testPlan1.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
//        testPlan1.setName("AddContactList");

        ThreadGroup threadGroup1 = new ThreadGroup();
        threadGroup1.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        threadGroup1.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup1.setName("AddContactList");
        threadGroup1.setNumThreads(5);
        threadGroup1.setRampUp(10);
        threadGroup1.setSamplerController(loopController);

        HTTPSampler httpSampler1 = new HTTPSampler();
        httpSampler1.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        httpSampler1.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        httpSampler1.setName("AddContactList");
        httpSampler1.setComment("Request GET google.com");
        httpSampler1.setMethod("POST");
        httpSampler1.setProtocol("https");
        httpSampler1.setDomain("dev-8.ixcc-sandbox.avayacloud.com");
        httpSampler1.setPath("/auth/realms/system/protocol/openid-connect/token");


        HashTree hashTree = new HashTree();


        hashTree.add(testPlan);
        hashTree.add(testPlan, summariser);
        HashTree threadGroupHashTree = hashTree.add(testPlan, threadGroup);
        HashTree threadGroupHashTree1 = hashTree.add(testPlan, threadGroup1);
        threadGroupHashTree1.add(httpSampler1);
        threadGroupHashTree1.add(resultCollector);
        threadGroupHashTree1.add(summariser);
        HashTree httpSamplerHashTree = threadGroupHashTree.add(httpSampler);
        httpSamplerHashTree.add(jsonPostProcessor);
        httpSamplerHashTree.add(beanShellPostProcessor);
        httpSamplerHashTree.add(resultCollector);
        httpSamplerHashTree.add(summariser);
        httpSamplerHashTree.add(debugSampler);




//        HashTree threadGroupHashTree1 = hashTree.add(testPlan, threadGroup1);
//        threadGroupHashTree1.add(httpSampler1);
//        threadGroupHashTree1.add(resultCollector);
//        threadGroupHashTree1.add(summariser);

//        StandardJMeterEngine jm = new StandardJMeterEngine();
//        jm.configure(hashTree);
//        jm.run();
//        JMeterVariables vars = new JMeterVariables();
//
//        System.out.println("Variable of token : " + vars.get("${__P(bearerToken)}"));




        try {
            FileOutputStream fileStream = new FileOutputStream("C:/Users/vvtruong/Downloads/ProjectTester/ProjectTester/AutoBuild/src/test/resources/datafile/text1.jmx");
            SaveService.saveTree(hashTree, fileStream);
        } catch (Exception e) {
            System.out.println("log failed e: " + e);
        }
//        StandardJMeterEngine jm = new StandardJMeterEngine();
//        jm.configure(hashTree);
//        jm.run();
    }



}