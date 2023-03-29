package com.personal.outbound.steps;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class createJmeterFile {

            public static void main (String[] args){
//             Engine
            StandardJMeterEngine jm = new StandardJMeterEngine();
            // jmeter.properties
            JMeterUtils.loadJMeterProperties("D:/Software/apache-jmeter-5.5/bin/jmeter.properties");

            HashTree hashTree = new HashTree();

            SetupThreadGroup threadGroup = new SetupThreadGroup();
            threadGroup.setNumThreads(1);
            threadGroup.setRampUp(1);


            // HTTP Sampler
            HTTPSampler httpSampler = new HTTPSampler();
            httpSampler.setProtocol("https");
            httpSampler.setDomain("dev-8.ixcc-sandbox.avayacloud.com");
            httpSampler.setPort(80);
            httpSampler.setPath("/auth/realms/system/protocol/openid-connect/token");
            httpSampler.setMethod("POST");

            // Thread Group

            // Test plan
            TestPlan testPlan = new TestPlan("MY TEST PLAN");

            hashTree.add("testPlan", testPlan);
            hashTree.add("threadGroup", threadGroup);
            hashTree.add("httpSampler", httpSampler);

            jm.configure(hashTree);

            jm.run();
        }
}
