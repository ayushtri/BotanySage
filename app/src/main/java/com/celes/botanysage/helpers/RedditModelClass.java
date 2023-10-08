package com.celes.botanysage.helpers;

public class RedditModelClass {
    public String imgUrl;
    public String title;
    public String user;
    public String postUrl;

    public RedditModelClass(String imgUrl, String title, String user, String postUrl) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.user = user;
        this.postUrl = postUrl;
    }
}
