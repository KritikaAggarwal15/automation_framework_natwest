package com.natWest.webService.customListeners;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.*;

import static com.natWest.genericUtils.FilePathGenerator.getFilePath;
import static com.natWest.genericUtils.PropertyFileLoader.loadConfigFile;
import static io.restassured.RestAssured.given;


public class CustomListener implements ITestListener {
    public static RequestSpecification requestSpecification;
    protected static Response actualResponse;
    public int index;
    private static String getObjectEndpoint;
    public static Map<Integer, String> ids = new LinkedHashMap<>();
    public static List<Map<String, String>> rowList = new ArrayList<>();

    @Override
    public void onStart(ITestContext context) {

        String env = System.getProperty("env"); // Get the 'env' property from Maven
        String configFilePath = getFilePath("environments") + env;
        Properties configLoader = loadConfigFile(configFilePath);
        RestAssured.baseURI = configLoader.getProperty("baseURI").trim();
        setGetObjectEndpoint(configLoader.getProperty("objectEndpoint"));
        requestSpecification = given().header("Content-Type", "application/json");
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    public void onTestSuccess(ITestResult result) {

    }

    public void onTestFailure(ITestResult result) {

    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onFinish(ITestContext context) {

    }

    public String getGetObjectEndpoint() {
        return getObjectEndpoint;
    }

    public void setGetObjectEndpoint(String getObjectEndpoint) {
        CustomListener.getObjectEndpoint = getObjectEndpoint;
    }


}
