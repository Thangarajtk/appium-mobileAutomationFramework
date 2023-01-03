package com.automate.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DecodeUtils {

  public static String getDecodedString(String encodedString) {
    return new String(Base64.getDecoder().decode(encodedString.getBytes()));
  }
}
