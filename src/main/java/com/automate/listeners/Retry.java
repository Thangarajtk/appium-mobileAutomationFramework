package com.automate.listeners;

import com.automate.enums.ConfigProperties;
import com.automate.utils.configloader.PropertyUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private final int maxRetry = Integer.parseInt(PropertyUtils.getPropertyValue(ConfigProperties.RETRY_COUNT));

    @Override
    public boolean retry(ITestResult result) {
        boolean value = false;
        if (PropertyUtils.getPropertyValue(ConfigProperties.RETRY_FAILED_TESTS).equalsIgnoreCase("yes")) {
            value = count < maxRetry;
            count++;
        }
        return value;
    }
}
