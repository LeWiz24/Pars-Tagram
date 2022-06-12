package com.olivares.parstagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String USER_DESCRIPTION = "user";

    public String getKeyDescription() {
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE):;
    }
    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE, parseFile);
    }

    public String getUserDescription() {
        return getString(USER_DESCRIPTION);
    }
}
