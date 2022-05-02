package com.example.myapplication;

import android.content.SharedPreferences;

public class contact {
    public String link = "null";
    public String name = "null";
    public String phone = "null";
    public String email = "null";
    public String instagram = "null";
    public String snapchat = "null";
    public String twitter = "null";

    public void updateContactSP(String key, SharedPreferences sp) {
        String keyCategory = key.substring(0,key.length()-1);
        if(keyCategory.equals("name")){
            this.name = sp.getString(key, "");
        }

        if(keyCategory.equals("email")){
            this.email = sp.getString(key, "");
        }

        if(keyCategory.equals("phone")){
            this.phone = sp.getString(key, "");
        }

        if(keyCategory.equals("instagram")){
            this.instagram = sp.getString(key, "");
        }

        if(keyCategory.equals("snapchat")){
            this.snapchat = sp.getString(key, "");
        }

        if(keyCategory.equals("twitter")){
            this.twitter = sp.getString(key, "");
        }
    }

    public void setContact(SharedPreferences sp, String name, String email, String phone, String instagram, String snapchat, String twitter) {
        this.name = sp.getString(name, "");
        this.email = sp.getString(email, "");
        this.phone = sp.getString(phone, "");
        this.instagram = sp.getString(instagram, "");
        this.snapchat = sp.getString(snapchat, "");
        this.twitter = sp.getString(twitter, "");
    }
}
