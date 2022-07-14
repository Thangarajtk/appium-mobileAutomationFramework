package com.automate.utils;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import com.automate.constants.FrameworkConstants;
import com.automate.enums.ConfigProperties;
import com.automate.utils.configloader.PropertyUtils;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppiumServerManager {

    private static AppiumDriverLocalService service;

    static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        }
        return isServerRunning;
    }

    public static void startAppiumServer() {
        if (PropertyUtils.getPropertyValue(ConfigProperties.START_APPIUM_SERVER).equalsIgnoreCase("yes")) {
            if (!AppiumServerManager.checkIfServerIsRunning(FrameworkConstants.getAppiumServerPort())) {
                //Build the Appium service
                AppiumServiceBuilder builder = new AppiumServiceBuilder();
                builder.usingDriverExecutable(new File(FrameworkConstants.getNodeJsPath()));
                builder.withAppiumJS(new File(FrameworkConstants.getAppiumJsPath()));
                builder.withIPAddress(FrameworkConstants.getAppiumServerHost());
                builder.usingPort(FrameworkConstants.getAppiumServerPort());
                builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
                builder.withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload");
                builder.withLogFile(new File(FrameworkConstants.getAppiumServerLogsPath()));
                //Start the server with the builder
                service = AppiumDriverLocalService.buildService(builder);
//				service = AppiumDriverLocalService.buildDefaultService();
                service.start();
                service.clearOutPutStreams();
            }
        }
    }

    public static void stopAppiumServer() {
        if (PropertyUtils.getPropertyValue(ConfigProperties.START_APPIUM_SERVER).equalsIgnoreCase("yes")) {
            service.stop();
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("taskkill /F /IM node.exe");
                runtime.exec("taskkill /F /IM cmd.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
