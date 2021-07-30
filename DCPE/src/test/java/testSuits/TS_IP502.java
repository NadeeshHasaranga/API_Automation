package testSuits;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import common.Common;
import common.RequestType2;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ExtentReportListner;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(ExtentReportListner.class)
public class TS_IP502 extends Common{
	
	//ExtentReports report = new ExtentReports("");
	
	@BeforeTest
	public static void setup() {
		 RestAssured.baseURI = "http://122.255.12.59:9085/seta/api/v1/attendance";
		 reports = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		 reports.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}

	// ObjectMapper object;

	@Test(priority = 1, description = "Testing the mark attendance API")
	public void markInTime() {

		test = reports.startTest("markInTime");
		
		//Reporter.log("Test running",true);
		test.log(LogStatus.PASS, "Test Case 1 is running");
		//test.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
		// Path resourceDirectory = Paths.get("src","resources");
		// String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		// System.out.println(absolutePath);

		// retrieve json body from json file
		String requestBody = getJsonAsString("req2");
		// System.out.println(convertToString(requestBody));

		RequestType2 requesttype2 = new Gson().fromJson(requestBody, RequestType2.class);

		requesttype2.setEmployeeId("7");
		requesttype2.setDate("2021-03-02");
		requesttype2.setInTime("10:00:00");
		//System.out.println(convertToString(requesttype2));

		Response response = given().header("Content-type", "application/json").and().body(requestBody).when().post(RestAssured.baseURI)
				.then().extract().response();

		String responseBody = response.getBody().asString();
		System.out.println(responseBody + "bb");
		//int responseCode = response.statusCode();
		
		String message = response.jsonPath().getString("message");
		System.out.println(message);
		String expected = "{\"success\":true,\"statusCode\":200,\"message\":\"Started work!!\"}"; 
		
		System.out.println(expected + "aa");
		String ErrorMsg = verifyTheResponse(responseBody, expected, message);
		System.out.println(ErrorMsg);
		
		//Assertions.assertEquals(200, response.statusCode());
		
		String result = verifyParameter("truee",response.jsonPath().getString("success"), "True Message not present");
		System.out.println(result);
		
		//Assertions.assertEquals("true", response.jsonPath().getString("success"));
		//Assertions.assertEquals("Started work!!", response.jsonPath().getString("message"));
	}

	@Test(priority = 2)
	public void TC001() {
		
		test = reports.startTest("TC001");
		test.log(LogStatus.PASS, "Test Case2 Passed is running");

	}
	
	 @AfterMethod
	 public void getResult(ITestResult result){
	 
	 reports.endTest(test);
	 }
	 
	 @AfterTest
	 public void endReport(){
	 // writing everything to document
		 reports.flush();
		 reports.close();
	    }
	
}
