package com.mycompany.healthapp.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LoggingConfig {
    public static void configure() {
        try (InputStream is = LoggingConfig.class.getClassLoader().getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
