package com.example.newsfeed;

import android.app.Application;

import com.backendless.Backendless;

public class ContactApplication extends Application {

    public static final String APPLICATION_ID="34B2442F-B27C-1850-FF10-7F34BBA40000";
    public static final String API_KEY="4E79C263-D5CF-0F83-FF90-BEB3921E4000";
    public static final String SERVER_URL="https://api.backendless.com";
    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(),APPLICATION_ID,API_KEY);
    }
}
