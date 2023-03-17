package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(listeners.TestListener.class.getName());

    @Override
    public void onTestStart(ITestResult result) {
        log.info("STARTING TEST METHOD: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("TEST METHOD: " + result.getName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("TEST METHOD: " + result.getName() + " FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

}

