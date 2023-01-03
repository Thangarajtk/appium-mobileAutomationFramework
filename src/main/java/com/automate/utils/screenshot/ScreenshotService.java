package com.automate.utils.screenshot;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenshotService {

  // Abstract layer to handle the change in business requirement
  public static String getScreenshotAsBase64() {
    return ScreenshotUtils.captureScreenshotAsBase64();
  }
}
