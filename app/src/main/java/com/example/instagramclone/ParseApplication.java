//1d. make application class where i initialize parse sdk with credentials.
//create new application class (this one) that will extend root applicaiton class
//parse app should be a sub class of the root app class from android.app.application
package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

//1e. class app has a lifecycle
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //4g. make sure to register ParseApplication with Parse before i call
        //Parse.initialize otherwise i wont be able to query or set data on
        //my post model
        //4g. Register your parse models
        ParseObject.registerSubclass(Post.class);

        //1f. to initialize sdk copy app & client id from back4app's app settings
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("FzQU2owXNFiUbq4g1bHH7HDSLdQUFsq7DH9rLLJw")
                .clientKey("KqluuSvenNGFPMkoYhBIAFpVws7twxoosPGWzpjz")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
