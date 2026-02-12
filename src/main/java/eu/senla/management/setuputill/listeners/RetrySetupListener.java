package eu.senla.management.setuputill.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class RetrySetupListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method,
                                 ITestResult testResult) {
        if (method.isConfigurationMethod()) {
            int attempts = 0;
            while (attempts < 3) {
                try {
                    method
                            .getTestMethod()
                            .getConstructorOrMethod()
                            .getMethod()
                            .invoke(testResult.getInstance());
                    return;
                } catch (Exception e) {
                    attempts++;
                }
            }
            throw new SkipException("Setup field after retries");
        }
    }
}
