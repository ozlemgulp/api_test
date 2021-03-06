package general;

import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class TestListener implements ISuiteListener, ITestListener {

	final static Logger log = Logger.getLogger(BasicLogger.class);
	public static Spec spec = new Spec();

	long testSuideStart;
	long start;

	public void onStart(ISuite iSuite) {
		testSuideStart = System.currentTimeMillis();
		if (!APIServerTests.isAppBackendUp()) // check if the service under test is up before running any tests
			throw new SkipException("Skipping tests as the application backend is down.");
	}

	public void onFinish(ISuite iSuite) {
		// Display the Test Suite Execution Duration
		log.info("Test Suite Execution Duration: " + (System.currentTimeMillis() - testSuideStart));
		log.info("Finished running all the tests.");
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		start = System.currentTimeMillis();
		log.info("Starting test " + iTestResult.getName());
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log.info("Test " + iTestResult.getName() + " PASSED");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		log.error("Test " + iTestResult.getName() + "  FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log.warn("Test " + iTestResult.getName() + " SKIPPED");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

	}

	@Override
	public void onStart(ITestContext iTestContext) {
		// Display Test StartTime
		log.info(iTestContext.getName() + "Test Started" + iTestContext.getStartDate());

	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		// Display Test EndTime
		log.info(iTestContext.getName() + "Test Finished" + iTestContext.getEndDate());

	}
}
