package com.automate.utils.screenrecording;

import com.automate.enums.ConfigProperties;
import com.automate.utils.configloader.PropertyUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenRecordingService {

  public static void startRecording() {
    if (PropertyUtils.getPropertyValue(ConfigProperties.RECORD_SCREEN).equalsIgnoreCase("yes")) {
      ScreenRecordingUtils.startScreenRecording();
    }
  }

  public static void stopRecording(String methodName) {
    if (PropertyUtils.getPropertyValue(ConfigProperties.RECORD_SCREEN).equalsIgnoreCase("yes")) {
      ScreenRecordingUtils.stopScreenRecording(methodName);
    }

  }
}
