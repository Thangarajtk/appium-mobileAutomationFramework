package com.automate.driver;

public final class DeviceManager {

    private DeviceManager() {
    }

    private static ThreadLocal<String> deviceName = new ThreadLocal<>();

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
