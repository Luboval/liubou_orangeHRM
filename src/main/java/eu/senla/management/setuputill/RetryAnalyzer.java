package eu.senla.management.setuputill;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            Allure.addAttachment("Retry attempt", "Test " + result.getName() + " retry # " + retryCount);
            log.info("Retrying Test " + result.getName() + " (" + retryCount + ")");
            return true;
        }
        return false;
    }
}
