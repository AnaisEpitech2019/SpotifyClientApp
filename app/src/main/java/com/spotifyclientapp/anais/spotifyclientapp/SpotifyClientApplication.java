package com.spotifyclientapp.anais.spotifyclientapp;

import android.app.Application;

import com.spotifyclientapp.anais.spotifyclientapp_api.managers.APIManager;

public class SpotifyClientApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        APIManager.setBaseUrl("https://api.spotify.com");
    }
}
