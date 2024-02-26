package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

private ConfigLoader(){
    properties = PropertiesUtils.propertyLoader("src/test/resources/config.properties");
}
public static ConfigLoader getInstance(){
    if(configLoader ==null){
        configLoader =new ConfigLoader();
    }
    return configLoader;
}

public String getClientID(){
    String client_id = properties.getProperty("client_id");
    if(properties!=null)
        return client_id;
    else
        throw new RuntimeException("Client-Id is not mentioned in config.properties file");
}
    public String getClientSecret(){
        String client_secret = properties.getProperty("client_secret");
        if(properties!=null)
            return client_secret;
        else
            throw new RuntimeException("client_secret is not mentioned in config.properties file");
    }
    public String getRefresh_token(){
        String refresh_token = properties.getProperty("refresh_token");
        if(properties!=null)
            return refresh_token;
        else
            throw new RuntimeException("refresh_token is not mentioned in config.properties file");
    }

    public String getGrant_type(){
        String grant_type = properties.getProperty("grant_type");
        if(properties!=null)
            return grant_type;
        else
            throw new RuntimeException("grant_type is not mentioned in config.properties file");
    }

    public String getUser_Id(){
        String user_Id = properties.getProperty("user_Id");
        if(properties!=null)
            return user_Id;
        else
            throw new RuntimeException("user_Id is not mentioned in config.properties file");
    }

}
