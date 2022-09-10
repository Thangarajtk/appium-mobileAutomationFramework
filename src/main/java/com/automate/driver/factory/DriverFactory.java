package com.automate.driver.factory;

import com.automate.customexceptions.DriverInitializationException;
import com.automate.driver.manager.DriverManager;
import com.automate.driver.Drivers;
import com.automate.enums.MobilePlatformName;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DriverFactory {

    public static void initializeDriver(MobilePlatformName mobilePlatformName, String devicename, String udid, int port, String emulator) {
        AppiumDriver<MobileElement> driver;
        switch (mobilePlatformName) {
            case ANDROID:
                driver = Drivers.createAndroidDriverForNativeApp(devicename, udid, port, emulator);
                break;
            case ANDROID_WEB:
                driver = Drivers.createAndroidDriverForWeb(devicename, udid, port, emulator);
                break;
            case IOS:
                driver = Drivers.createIOSDriverForNativeApp(devicename, udid, port);
                break;
            case IOS_WEB:
                driver = Drivers.createIOSDriverForWeb(devicename, udid, port);
                break;
            default:
                throw new DriverInitializationException("Platform name " + mobilePlatformName + " is not found. Please check the platform name");
        }
        DriverManager.setAppiumDriver(driver);
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
