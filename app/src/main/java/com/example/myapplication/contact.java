package com.example.myapplication;

import android.content.SharedPreferences;

public class contact {
    public String link = "";
    public String name = "";
    public String email = "";
    public String phone = "";
    public String instagram = "";
    public String snapchat = "";
    public String twitter = "";

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

    public String getLink() {
        return this.name + " ," + this.email + " ," + this.phone + " ," + this.instagram + " ," + this.snapchat + " ," + this.twitter + " ";
    }
}
