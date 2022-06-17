package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register parse models
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Comment.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MqYbOKVPmiY5UEaOmVqSgN8KBrP3wHxEWM6pcsMT")
                .clientKey("BF81FOGu8m1J56E6dixATWzXo05W4rA4x7X1sZGq")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
