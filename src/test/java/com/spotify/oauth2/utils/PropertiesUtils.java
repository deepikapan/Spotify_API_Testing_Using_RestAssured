package com.spotify.oauth2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties propertyLoader(String filePath) {

        Properties prop = new Properties();
        BufferedReader reader;
        try {
            try {
                reader = new BufferedReader(new FileReader(filePath));

                try {
                    prop.load(reader);
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Failed to load properties file! " + filePath);
                }
            } catch (FileNotFoundException f) {
                f.printStackTrace();
                throw new RuntimeException("Properties file not found at : " + filePath);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
