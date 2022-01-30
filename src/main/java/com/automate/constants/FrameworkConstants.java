package com.automate.constants;

import com.automate.enums.ConfigProperties;
import com.automate.utils.configloader.PropertyUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {

    public static final String PROJECT_PATH = System.getProperty("user.dir");

    @Getter
    private static final String appiumServerHost = "127.0.0.1";
    @Getter
    private static final int appiumServerPort = 4723;
    @Getter
    private static final String appiumJsPath = System.getenv("APPIUM_HOME") + File.separator + "main.js";
    @Getter
    private static final String androidApkPath = PROJECT_PATH + File.separator + "src/test/resources" + File.separator + "app" + File.separator + "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
    @Getter
    private static final String credentialsJsonFile = "data/credentials.json";
    @Getter
    private static final String chromedriverExecutablesPath = PROJECT_PATH + File.separator + "src/test/resources" + File.separator + "executables" + File.separator + "chromedriver.exe";
    @Getter
    private static final String configPropertiesPath = PROJECT_PATH + File.separator + "src/test/resources" + File.separator + "config" + File.separator + "config.properties";
    @Getter
    private static final String configJsonPath = PROJECT_PATH + File.separator + "src/test/resources" + File.separator + "config" + File.separator + "config.json";
    @Getter
    private static final long explicitWait = 15;
    @Getter
    private static final String testDataSheet = "TEST_DATA";
    @Getter
    private static final String iosAppPath = "";
    @Getter
    private static final String screenshotPath = PROJECT_PATH + File.separator + "screenshots";
    @Getter
    private static final String testDataFilePath = PROJECT_PATH + File.separator + "src/test/resources" + File.separator + "data" + File.separator + "testdata.xlsx";
    @Getter
    private static final String nodeJsPath = System.getenv("NODE_HOME") + File.separator + "node.exe";

    private static final String extentReportPath = PROJECT_PATH + File.separator + "extent-test-report";

    public static String getExtentReportPath() {
        if (PropertyUtils.getPropertyValue(ConfigProperties.OVERRIDE_REPORTS).equalsIgnoreCase("yes")) {
            return extentReportPath + File.separator + "index.html";
        } else {
            return extentReportPath + File.separator + getCurrentDateTime() + File.separator + "index.html";
        }
    }

    private static final String appiumServerLogsPath = PROJECT_PATH + File.separator + "server-logs";

    public static String getAppiumServerLogsPath() {
        if (PropertyUtils.getPropertyValue(ConfigProperties.OVERRIDE_SERVER_LOG).equalsIgnoreCase("yes")) {
            return appiumServerLogsPath + File.separator + "server.log";
        } else {
            return appiumServerLogsPath + File.separator + getCurrentDateTime() + File.separator + "server.log";
        }
    }

    private static final String screenRecordingsPath = PROJECT_PATH + File.separator + "screen-recordings";

    public static String getScreenRecordingsPath() {
        File screenRecordingsDir = new File(screenRecordingsPath);
        if (!screenRecordingsDir.exists()) {
            screenRecordingsDir.mkdir();
        }
        return screenRecordingsPath;
    }

    private static String getCurrentDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(localDateTime);
    }
}
