package common;

import java.io.File;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Common implements ITestListener {
	
	protected static ExtentReports reports;
	protected static ExtentTest test;
	
	private static String resultpath = getResultPath();


	public static void deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i].getName());
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
	}

	private static String getResultPath() {

		resultpath = "test";//new SimpleDateFormat("yyyy-MM-dd hh-mm.ss").format(new Date());
		if (!new File(resultpath).isDirectory()) {
			new File(resultpath);
		}
		return resultpath;
	}

	String ReportLocation = "test-output/Report/" + resultpath + "/";

	public void onTestStart(ITestResult result) {

		test = reports.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName());
		System.out.println(result.getTestClass().getTestName());
		System.out.println(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "Test is pass");

	}

	public void onTestFailure(ITestResult result) {
		test.log(LogStatus.FAIL, "Test is fail");

	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "Test is skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		System.out.println(ReportLocation + "  ReportLocation");
		reports = new ExtentReports(ReportLocation + "ExtentReport.html");
		test = reports.startTest("");

	}

	public void onFinish(ITestContext context) {
		reports.endTest(test);
		reports.flush();

	}	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Parameter Verification
	public String verifyParameter(String ParamData, String ParamName, String ErrorMessage) {
		String Result, Error = ErrorMessage;

		try {
			Assertions.assertEquals(ParamData, ParamName);

			Result = ParamData;

			return Result;
		} catch (AssertionError e) {
			return Error;
		}
	}

	// Convert json to string
	public static String convertToString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			return null;
		}
	}

	// Read json file
	public static String getJsonAsString(String fileName) {
		try {

			Path resourceDirectory = Paths.get("resources", fileName + ".json");
			String absolutePath = resourceDirectory.toFile().getAbsolutePath();
			resourceDirectory = Paths.get(absolutePath);
			return new String(Files.readAllBytes(resourceDirectory));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	//Response verification
	public String verifyTheResponse(String Response, String ExpectedResult, String ErrorMessage) {
		String Output, Error;

		if (Response.equals(ExpectedResult)) {
			Output = "Response meet the expected result" + "\nResponse is" + " " + Response;
			
			test.log(LogStatus.PASS, "Response meet the expected result");

			return Output;
		} else {
			
			Assertions.assertEquals(ExpectedResult, Response);
			Error = "Error is" + ErrorMessage;
			test.log(LogStatus.FAIL, "Response doesn't meet the expected result");
	
			return Error;
			
		}
	}

//		public String RequestBodyTC001() {
//			
//			// retrieve json body from json file
//			String requestBody = getJsonAsString("req2");
//			// System.out.println(convertToString(requestBody));
//
//			RequestType2 requesttype2 = new Gson().fromJson(requestBody, RequestType2.class);
//
//			requesttype2.setEmployeeId("7");
//			requesttype2.setDate("2021-03-02");
//			requesttype2.setInTime("10:00:00");
//			//System.out.println(convertToString(requesttype2));
//			
//			return requestBody;
//
//		}

}
