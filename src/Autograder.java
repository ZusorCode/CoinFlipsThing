
import java.io.*;
import java.lang.*;
import java.util.*;

import org.json.*;

public class Autograder
{

    /**
     * Necessary private fields for the Autograder class.
     */
    private Interceptor interceptor;
    private HashMap<String, ArrayList<String>> outputs;
    private PrintStream origOut;

    /**
     * Autograders contain the main logic for autograding and are used by
     * Grader.java
     */
    public Autograder()
    {
        tests = new ArrayList<TestCase>();
        outputs = new HashMap<String, ArrayList<String>>();
        origOut = System.out;
        interceptor = new Interceptor(origOut, this);
        System.setOut(interceptor);// just add the interceptor
    }

    /**
     * This adds a TestCase. Instead of doing the comparisons itself, it takes
     * a boolean which is the result of some custom test. Also allows for any
     * custom message.
     * @param <T>            This allows for any type of output
     * @param testName       Name of the test being run
     * @param status         Boolean result of anything
     * @param studentOutput  Student output that was tested
     * @param solutionOutput Solution output that was tested
     * @param message        Message to show
     */
    public <T> void addTest(String testName, boolean status,
                            T studentOutput, T solutionOutput, String message)
    {
        tests.add(new TestCase(testName, status, message, studentOutput, solutionOutput));
    }

    /**
     * This will do a standard comparison between studentOutput and
     * solutionOutput. And depending on the result will create a TestCase with
     * the result and either the messagePass or messageFail
     * @param <T>            This allows for any type of output
     * @param testName       Name of the test being run
     * @param studentOutput  Student output to test
     * @param solutionOutput Solution output to test
     * @param messagePass    Message to show if assertEqual passes
     * @param messageFail    Message to show if assertEqual fails
     */
    public <T> void assertEqual(String testName, T studentOutput,
                                T solutionOutput, String messagePass, String messageFail)
    {
        String studentOutputString;
        String solutionOutputString;
        if (studentOutput == null)
        {
            studentOutputString = "";
        } else
        {
            studentOutputString = studentOutput.toString();
        }
        if (solutionOutput == null)
        {
            solutionOutputString = "";
        } else
        {
            solutionOutputString = solutionOutput.toString();
        }
        boolean success = solutionOutput.equals(studentOutput);
        String message = success ? messagePass : messageFail;
        addTest(testName, success, studentOutputString, solutionOutputString,
                message);
    }

    /**
     * Allows you to get the original output stream.
     * @return System.out
     */
    public PrintStream getOriginal()
    {
        return origOut;
    }

    /**
     * Adds an output from the Interceptor to outputs
     * @param className Name of the class the output came from
     * @param s         Output string
     */
    public void addOutput(String className, String s)
    {
        ArrayList<String> arr = outputs.get(className);
        if(arr == null)
        {
            arr = new ArrayList<String>();
        }
        arr.add(s);
        outputs.put(className, arr);
    }

    /**
     * Get a string of all the outputs for a given class name.
     * @param  className Name of the class to retriev output from.
     * @return Returns output for the given className as a concated string
     */
    public String getOutput(String className)
    {
        ArrayList<String> output = outputs.get(className);
        if (output == null)
        {
            return "You forgot to print something.";
        }
        String outputString = "";
        for (String s : output)
        {
            outputString += s;
        }
        return outputString;
    }

    /**
     * Get an ArrayList of all outputs for a given class name.
     * @param className Name of the class to retriev output from.
     * @return Returns output for the given className as an ArrayList.
     */
    public ArrayList<String> getOutputArrayList(String className)
    {
        ArrayList<String> output = outputs.get(className);
        return output;
    }

    /**
     * This resets the current outputs. This should be used after
     * create a new test so that tests don't also test previous
     * outputs.
     */
    public void clearOutput()
    {
        outputs = new HashMap<String, ArrayList<String>>();
    }

    /**
     * Similar to the method above, however, instead of clearing out all output
     * it will only clear output for a given classname. However, it only does
     * this if output exists for the class. Otherwise, it does nothing.
     * @param className Class to clear output for
     */
    public void clearOutput(String className)
    {
        ArrayList<String> output = outputs.get(className);
        if (output != null)
        {
            ArrayList<String> arr = new ArrayList<String>();
            outputs.put(className, arr);
        }
    }

    /**
     * Used for printing the output of all test results.
     * @return string of JSON-like results
     */
    public String toString() {
        JSONObject jsonResults = new JSONObject();
        JSONArray jsonTests = new JSONArray();
        jsonResults.put("tests", jsonTests);
        for (int i = 0; i < tests.size(); i++) {
            TestCase test = tests.get(i);
            JSONObject jsonTest = new JSONObject();
            jsonTest.put("success", test.success);
            jsonTest.put("test", test.test);
            jsonTest.put("message", test.message);
            jsonTest.put("studentOutput", test.studentOutput);
            jsonTest.put("solutionOutput", test.solutionOutput);
            jsonTests.put(jsonTest);
        }
        return "__unittests__" + jsonResults.toString();
    }

    /**
     * Contains all created test cases.
     */
    private ArrayList<TestCase> tests;

    /**
     * Contains the results a of single test case.
     */
    class TestCase
    {
        public TestCase(String test, boolean success, String message,
                        Object studentOutput, Object solutionOutput)
        {
            this.success = success;
            this.message = message;
            this.studentOutput = studentOutput;
            this.solutionOutput = solutionOutput;
            this.test = test;
        }

        private String message;
        private boolean success;
        private Object studentOutput;
        private Object solutionOutput;
        private String test;
    }
}
