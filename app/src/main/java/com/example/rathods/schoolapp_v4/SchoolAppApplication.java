package com.example.rathods.schoolapp_v4;

import android.app.Application;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by RATHOD'S on 6/11/2017.
 */

public class SchoolAppApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "rD0JnJYQQBUgt6QfOvYc0LiD5";
    private static final String TWITTER_SECRET = "aodP4KNZOYyRLzF4d6F1SX18HZt0nJhleImejLascNnXppz74d";
    private static SchoolAppApplication sInstance;

    public static SchoolAppApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        sInstance = this;

    }

}
