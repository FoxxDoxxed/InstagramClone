package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

//4e. create post class and make it have superclass parse object. must have
//parse class name
@ParseClassName("Post")
public class Post extends ParseObject {
    //4f. define getters and setters based on each key i defined on the post
    //area of the parse dashboard (description image & user)
    //4f. define keys
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";

    //4f. define getters and setters
    public String getDescription() {
        //getString is defined inside parseobject class. method will pull attribute
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription(String description) {
        //put is defined on parse object that will associate this key witth the
        //value passed in
        put(KEY_DESCRIPTION, description);
    }

    //for image get back sepcial return type called parse file.
    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }
}
