package com.spotify.oauth2.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertiesUtils.propertyLoader("src/test/resources/data.properties");
    }
    public static DataLoader getInstance(){
        if(dataLoader ==null){
            dataLoader =new DataLoader();
        }
        return dataLoader;
    }

    public String getPlayListId(){
        String get_PlayList_id = properties.getProperty("get_Playlist_id");
        if(properties!=null)
            return get_PlayList_id;
        else
            throw new RuntimeException("PlayList_id is not mentioned in data.properties file");
    }
    public String getUpdatePlaylistId(){
        String update_playlist_id = properties.getProperty("update_playlist_id");
        if(properties!=null)
            return update_playlist_id;
        else
            throw new RuntimeException("update_playlist_id is not mentioned in data.properties file");
    }

}

