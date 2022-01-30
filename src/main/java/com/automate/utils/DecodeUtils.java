package com.automate.utils;

import java.util.Base64;

public final class DecodeUtils {

    private DecodeUtils() {}

    public static String getDecodedString(String encodedString) {
        return new String(Base64.getDecoder().decode(encodedString.getBytes()));
    }
}
