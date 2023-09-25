package com.natWest.genericUtils;

import java.util.Properties;

import static com.natWest.genericUtils.PropertyFileLoader.loadConfigFile;

public class FilePathGenerator {
    public static String getFilePath(String key) {
        Properties configLoader = loadConfigFile("src/test/resources/config/filepath.properties");
        return configLoader.getProperty(key);

    }
}