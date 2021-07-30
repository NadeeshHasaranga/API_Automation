package testSuits;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import common.Common;
import common.IP504Request;
import common.RequestType2;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TS_IP504 extends Common {
	
	static IP504Request request504 = new IP504Request();
	static String requestBody;

	@BeforeTest
	public static void setup() {
		RestAssured.baseURI = "http://122.255.12.59:9085/seta/api/v1/attendance";
		reports = new ExtentReports(System.getProperty("user.dir") + "/test-output/STMExtentReport.html", true);
		reports.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

		// retrieve json body from json file
		requestBody = getJsonAsString("IP504");
		request504 = new Gson().fromJson(requestBody, IP504Request.class);
	}

	@Test(priority = 1, description = "Testing the")
	public void TC_01() {

		test = reports.startTest("TC_01");
		// Reporter.log("Test running",true);
		test.log(LogStatus.PASS, "Test Case TC_01 is running");
		// test.log(LogStatus.PASS, "Test Case (failTest) Status is passed");

		request504.setProductCategory(1);
		request504.setSbu(3);
		request504.setContractNo("60714800");
		request504.setProductType(0);

		Response response = given().header("Content-type", "application/json").and().body(requestBody).when()
				.post(RestAssured.baseURI).then().extract().response();

		String responseBody = response.getBody().asString();
		System.out.println(responseBody + "bb");
		// int responseCode = response.statusCode();

		String message = response.jsonPath().getString("errorDesc");
		System.out.println(message);
		String expected = "{\"status\":1,\"errorCode\":\"SUCCESS\",\"errorDesc\":\"Operation success\","
				+ "\"accounts\":[{\"contractNo\":\"60714800\",\"remarks\":[{\"remark\":\"data ok \",\"createdUser\":"
				+ "\"chandimac_inova\",\"createdDate\":\"2020-11-24T04:29:13.000+00:00\"}]}]}";

		System.out.println(expected + "aa");
		String ErrorMsg = verifyTheResponse(responseBody, expected, message);
		System.out.println(ErrorMsg);

		String result = verifyParameter("true", response.jsonPath().getString("success"),"");
		System.out.println(result);

		// Assertions.assertEquals(200, response.statusCode());
		// Assertions.assertEquals("true", response.jsonPath().getString("success"));
		// Assertions.assertEquals("Started work!!",
		// response.jsonPath().getString("message"));
	}

	
	@BeforeMethod
	public void StartReport() {
		//String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
	
		System.out.println("Name of current method: " + name);
		test = reports.startTest(name);
		
	}
	
	
	@Test(priority = 2, description = "Testing the")
	public void TC_02() {

		test = reports.startTest("TC_02");
		test.log(LogStatus.PASS, "Test Case2 is running");

	}
	
	
	@Test(priority = 1, description = "Testing the")
	public void TC_03() {

		test = reports.startTest("TC_01");
		// Reporter.log("Test running",true);
		test.log(LogStatus.PASS, "Test Case TC_01 is running");
		// test.log(LogStatus.PASS, "Test Case (failTest) Status is passed");

		request504.setProductCategory(1);
		request504.setSbu(3);
		request504.setContractNo("60714800");
		request504.setProductType(0);

		Response response = given().header("Content-type", "application/json").and().body(requestBody).when()
				.post(RestAssured.baseURI).then().extract().response();

		String responseBody = response.getBody().asString();
		System.out.println(responseBody + "bb");
		// int responseCode = response.statusCode();

		String message = response.jsonPath().getString("message");
		System.out.println(message);
		String expected = "{\"status\":1,\"errorCode\":\"SUCCESS\",\"errorDesc\":\"Operation success\","
				+ "\"accounts\":[{\"contractNo\":\"60714800\",\"remarks\":[{\"remark\":\"data ok \",\"createdUser\":"
				+ "\"chandimac_inova\",\"createdDate\":\"2020-11-24T04:29:13.000+00:00\"}]}]}";

		//System.out.println(expected + "aa");
		String ErrorMsg = verifyTheResponse(responseBody, expected, message);
		//System.out.println(ErrorMsg);

		String result = verifyParameter("true", response.jsonPath().getString("success"), "True Message not present");
		//System.out.println(result);

	}


	@AfterMethod
	public void getResult(ITestResult result) {

		reports.endTest(test);
	}

	@AfterTest
	public void endReport() {
		// writing everything to document
		reports.flush();
		reports.close();
	}


}
