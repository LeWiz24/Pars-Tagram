package com.olivares.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DulfyCkOSm5bYakucabiH8O6Egl8prJXkj5yKySz")
                .clientKey("7Xv0ySlMtF3vHPPbhCmW0Jf0JLHVE6NVWnZqCK9P")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
