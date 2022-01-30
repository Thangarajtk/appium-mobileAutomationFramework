package com.automate.utils.configloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.automate.constants.FrameworkConstants;
import com.automate.customExceptions.PropertyFileUsageException;
import com.automate.enums.ConfigProperties;

public final class PropertyUtils {

    private PropertyUtils() {
    }

    private static final Properties property = new Properties();

    static void loadProperties(String propertyFilePath) {
        try (FileInputStream input = new FileInputStream(propertyFilePath)) {
            property.load(input);
        } catch (IOException e) {
            throw new PropertyFileUsageException("IOException occurred while loading Property file in the specified path");
        }
    }

    public static String getPropertyValue(ConfigProperties key) {
        loadProperties(FrameworkConstants.getConfigPropertiesPath());
        if (Objects.isNull(property.getProperty(key.name().toLowerCase())) || Objects.isNull(key.name().toLowerCase())) {
            throw new PropertyFileUsageException("Property name - " + key + " is not found. Please check the config.properties");
        }
        return property.getProperty(key.name().toLowerCase());
    }
}
