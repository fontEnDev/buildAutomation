package com.personal.outbound.steps;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LogicControllerGui;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.gui.HTTPArgumentsPanel;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RunJmeterWithJava {


    public static void main(String[] args) throws IOException {
        String path = "C:/Users/vvtruong/Downloads/ProjectTester/ProjectTester/AutoBuild/src/test/resources/apache-jmeter-5.5/";

        // Set JMeter properties
        JMeterUtils.setJMeterHome(path);
        JMeterUtils.loadJMeterProperties(path + "bin/jmeter.properties");
        JMeterUtils.initLocale();

        // Create a Test Plan
        TestPlan testPlan = new TestPlan();
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setName("Sample Test Plan");
        testPlan.getArguments().setProperty(TestElement.GUI_CLASS, ArgumentsPanel.class.getName());
        testPlan.getArguments().setProperty(TestElement.TEST_CLASS, Arguments.class.getName());

        // Create a Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setName("Sample Thread Group");
        threadGroup.setNumThreads(5);
        threadGroup.setRampUp(10);

        LoopController loopController = new LoopController();
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setLoops(4); // Set the number of loops to 1
        threadGroup.setSamplerController(loopController);

        HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
        httpSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        httpSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        httpSampler.getArguments().setProperty(TestElement.GUI_CLASS, HTTPArgumentsPanel.class.getName());
        httpSampler.getArguments().setProperty(TestElement.TEST_CLASS, Arguments.class.getName());
        httpSampler.setName("Sample HTTP Request");
        httpSampler.setComment("Request GET google.com");
        httpSampler.setMethod("POST");
        httpSampler.setProtocol("https");
        httpSampler.setDomain("dev-8.ixcc-sandbox.avayacloud.com");
        httpSampler.setPath("/auth/realms/system/protocol/openid-connect/token");



        // Add the Test Plan, Thread Group, and HTTP Sampler to a HashTree
        HashTree hashTree = new HashTree();
        hashTree.add(testPlan);
        HashTree threadGroupHashTree = hashTree.add(testPlan, threadGroup);
        HashTree httpSamplerHashTree = threadGroupHashTree.add(httpSampler);

        // Save the HashTree to a JMX file
        FileOutputStream fileStream = new FileOutputStream(path + "test7.jmx");
        SaveService.saveTree(hashTree, fileStream);
    }
}

