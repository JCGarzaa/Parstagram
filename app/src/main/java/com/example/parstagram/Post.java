package com.example.parstagram;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.File;
import java.util.Date;

import javax.sql.RowSet;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION =  "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";


    public ParseFile getMedia() {
        return getParseFile("media");
    }
    public void setMedia(ParseFile parseFile) {
        put("media", parseFile);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile file) {
        put(KEY_IMAGE, file);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }


}
