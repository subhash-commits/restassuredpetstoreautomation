package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;//responsible for UI(look and feel of the report)
	public ExtentReports extent;//(used to write common info)
	public ExtentTest test;//(to get data about the test pass,fail or skip)

	String repName;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
		repName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\Reports\\" + repName); // specify location of the report
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of report
		sparkReporter.config().setReportName("Pet Store Users API"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Not used
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}
}
