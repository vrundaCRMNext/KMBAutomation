package CommonUtility;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class TestListeners extends SetUp implements ITestListener
{
	public static ExtentTest test;
	public static ExtentReports extent=ExtentReporterNG.getReportObject();
	public static ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		test = extent.createTest(result.getTestClass().getName() + "  @TestCase : " + result.getMethod().getMethodName());
		extentTest.set(test);

		log.info("Test Case_" + methodName+ "_Successfully Started");
	}

	public void onTestSuccess(ITestResult result) {
		
    	String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName+ " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().pass(m);

		log.info("Test Case_" + methodName + "_Successfully Passed");
		try {
			ScreenShot.takeSnapShot(methodName, "Pass");
		} catch (Exception e) {}
	}

	public static void extentInfo(String message)
	{
		Markup m = MarkupHelper.createLabel(message, ExtentColor.BLUE);
    	extentTest.get().log(Status.INFO, m);
	}
	
	public static void extentError(String message)
	{
		Markup m = MarkupHelper.createLabel(message, ExtentColor.RED);
    	extentTest.get().log(Status.FAIL, m);
	}
	
	
	public void onTestFailure(ITestResult result)
	{
		
		/*
		 * extentTest.get().fail(result.getThrowable()); String testMethodName
		 * =result.getMethod().getMethodName();
		 * 
		 * try { extentTest.get().addScreenCaptureFromPath(ScreenShot.takeSnapShot(
		 * testMethodName, "Fail"), result.getMethod().getMethodName()); } catch
		 * (Exception e) {}
		 */
		
		String methodName = result.getMethod().getMethodName();
		log.error(methodName+ " Get Failed due to " + "\n" + result.getThrowable().getMessage());

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		// String excepionMessage= result.getThrowable().getMessage();
		extentTest.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		try {
			ScreenShot.takeSnapShot(methodName, "Fail");
			extentTest.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.ScreenShotName).build());
		} catch (Exception e) 
		{
			System.out.println("Exception occured while adding SS to extent report :"+e.getMessage());
		}
		


		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		
		//extentTest.get().log(Status.SKIP,result.getMethod().getMethodName()+" Skipped");
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().skip(m);

		log.info("Test Case_" + methodName + "_get Skipped as its Runmode is 'NO' ");

	}


	public void onFinish(ITestContext context) {
		if (extent != null) {

			extent.flush();
		}
	}

	
}
