package com.example.parstagram;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.sql.RowSet;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION =  "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_LIKED_BY = "likedBy";
    public static final String KEY_PFP = "profilePic";

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

    public ParseFile getPFP() { return getUser().getParseFile(KEY_PFP); }

    public void setImage(ParseFile file) {
        put(KEY_IMAGE, file);
    }
    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }
    public List<ParseUser> getLikedBy() {
        List<ParseUser> likedBy = getList(KEY_LIKED_BY);
        if (likedBy == null) {
            return new ArrayList<>();
        }
        return getList(KEY_LIKED_BY);
    }

    public void setLikedBy(List<ParseUser> newLikedBy) { put(KEY_LIKED_BY, newLikedBy); }

    public String getLikesCount() {
        int likeCount = getLikedBy().size();
        return likeCount + (likeCount == 1 ? " like" : " likes");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isLikedByCurrentUser() {
        List <ParseUser> likedBy = getLikedBy();
        for (int i = 0; i< likedBy.size(); i++) {
            if (likedBy.get(i).hasSameId(ParseUser.getCurrentUser())) {
                return true;
            }
        }
        return false;
    }

    public void unlike() {
        List <ParseUser> likedBy = getLikedBy();
        for (int i = 0; i < likedBy.size(); i++) {
            if (likedBy.get(i).hasSameId(ParseUser.getCurrentUser())) {
                likedBy.remove(i);
            }
        }
        setLikedBy(likedBy);
        saveInBackground();
    }

    public void like() {
        unlike();
        List<ParseUser> likedBy = getLikedBy();
        likedBy.add(ParseUser.getCurrentUser());
        setLikedBy(likedBy);
        saveInBackground();
    }
}
