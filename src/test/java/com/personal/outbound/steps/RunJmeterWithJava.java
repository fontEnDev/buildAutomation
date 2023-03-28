package com.personal.outbound.steps;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.engine.util.CompoundVariable;
//import org.apache.jmeter.functions.Functions;
//import org.apache.jmeter.reporters.ResultCollector;
//import org.apache.jmeter.reporters.Summariser;
//import org.apache.jmeter.testelement.TestElement;
//import org.apache.jmeter.util.JMeterUtils;
//import org.apache.jmeter.util.ScriptingTestElement;
//import org.apache.jmeter.util.ScriptingTestElementBeanInfo;
import org.apache.jorphan.collections.HashTree;

import java.io.File;
import java.util.Arrays;

public class RunJmeterWithJava {


//    public static void main(String[] args) throws Exception {
//        // Set the path to the JMeter properties file
//        JMeterUtils.loadJMeterProperties("path/to/jmeter.properties");
//
//        // Initialize JMeter engine
//        StandardJMeterEngine jmeter = new StandardJMeterEngine();
//
//        // Initialize the test tree from a JMX file
//        HashTree testPlanTree = JMeterUtils.loadTree(new File("path/to/testplan.jmx"));
//
//        // Get all the result collectors and summarizers in the test tree
//        testPlanTree.traverse(new TestPlanCollectorVisitor());
//
//        // Initialize result collector
//        ResultCollector resultCollector = new ResultCollector(new Summariser());
//        resultCollector.setFilename("path/to/results.jtl");
//        testPlanTree.add(testPlanTree.getArray()[0], resultCollector);
//
//        // Run JMeter test
//        jmeter.configure(testPlanTree);
//        jmeter.run();
//    }
//
//    private static class TestPlanCollectorVisitor implements HashTreeTraverser {
//
//        @Override
//        public void addNode(Object node, HashTree subTree) {
//            if (node instanceof ResultCollector || node instanceof Summariser) {
//                ScriptingTestElement el = (ScriptingTestElement) node;
//                Arrays.stream(ScriptingTestElementBeanInfo.getProps())
//                        .filter(p -> p.propertyType.equals(CompoundVariable.class))
//                        .forEach(p -> {
//                            TestElement te = (TestElement) el.getProperty(p.name).getObjectValue();
//                            Functions.getFunctionNames().forEach(f -> te.setProperty(f + ".param_types", ""));
//                        });
//            }
//        }
//
//        @Override
//        public void subtractNode() {
//        }
//
//        @Override
//        public void processPath() {
//        }
//    }
}

