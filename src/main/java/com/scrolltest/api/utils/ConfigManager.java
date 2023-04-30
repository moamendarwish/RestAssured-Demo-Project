package com.scrolltest.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager manager;
    private static final Properties props = new Properties();
    private ConfigManager() throws IOException {
            InputStream inputStream = ConfigManager.class.getResourceAsStream("/config.properties");
            props.load(inputStream);
    }
    public static ConfigManager getInstance(){
        if(manager == null){
            synchronized (ConfigManager.class){
                try {
                    manager = new ConfigManager();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }return manager ;
    }
    public String getString(String key){
        return System.getProperty(key,props.getProperty(key));
    }
}