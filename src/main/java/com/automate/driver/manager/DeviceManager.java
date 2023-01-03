package com.automate.driver.manager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeviceManager {

  private static final ThreadLocal<String> deviceName = new ThreadLocal<>();

  public static String getDeviceName() {
    return deviceName.get();
  }

  public static void setDeviceName(String device) {
    deviceName.set(device);
  }

  public static void unloadDeviceName() {
    deviceName.remove();
  }
}
