package eu.senla.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class SelenideVideoNameListener implements ITestListener {
    String videoTestName;

    @Override  //ITestListener
    public void onTestStart(ITestResult result) {
        //videoTestName = result.getMethod().getMethodName();
        videoTestName = null;
       if (result.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(Test.class)) {
                 Test annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
            //String annotation = test.getTestName();
           videoTestName = annotation.testName();
           System.setProperty("videoTestName", videoTestName);
             }

    }

//        @Override  //IInvokedMethodListener
//        public void beforeInvocation(IInvokedMethod method, ITestResult result) {
//            videoTestName = null;
//
//            if (method.isTestMethod()) {
//                 videoTestName = method.getTestMethod().getDescription();
//                System.setProperty("videoTestName",videoTestName);
//            }
//        }
}



