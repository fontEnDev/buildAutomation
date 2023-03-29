package com.personal.outbound.steps;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.io.File;

public class JMeterAutomation {

    public static void main(String[] args) throws Exception {

// Use JMeter home directory
        File jmeterHome = new File("C:/Users/vvtruong/Downloads/ProjectTester/ProjectTester/AutoBuild/src/test/resources/apache-jmeter-5.5");

// Initialize JMeter engine
        JMeterUtils.setJMeterHome(jmeterHome.getAbsolutePath());
        JMeterUtils.loadJMeterProperties(jmeterHome + "/bin/jmeter.properties");
        JMeterUtils.initLocale();

// Load test plan from the .jmx file
        File jmxFile = new File(jmeterHome + "/bin/TestPlan.jmx");
        HashTree testPlanTree = SaveService.loadTree(jmxFile);

// Configure summariser to generate test results summary
        Summariser summariser = new Summariser();
        ResultCollector resultCollector = new ResultCollector(summariser);
        testPlanTree.add(testPlanTree.getArray()[0], resultCollector);

// Run the test plan
        StandardJMeterEngine jmeter = new StandardJMeterEngine();
        TestPlan testPlan = (TestPlan) testPlanTree.getArray()[0];
        jmeter.configure(testPlanTree);
        jmeter.run();
    }


}