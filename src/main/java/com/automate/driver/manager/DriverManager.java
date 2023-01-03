package com.automate.driver.manager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DriverManager {

  private static final ThreadLocal<AppiumDriver<MobileElement>> threadLocalDriver = new ThreadLocal<>();

  public static AppiumDriver<MobileElement> getDriver() {
    return threadLocalDriver.get();
  }

  public static void setAppiumDriver(AppiumDriver<MobileElement> driver) {
    if (Objects.nonNull(driver))
      threadLocalDriver.set(driver);
  }

  public static void unload() {
    threadLocalDriver.remove();
  }
}
