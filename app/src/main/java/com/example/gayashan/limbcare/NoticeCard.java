package com.example.gayashan.limbcare;

import android.widget.ImageView;

public class NoticeCard {

    private String topic;
    private String description;
    public byte[] imgteamsnote;

    public NoticeCard(String topic, String description,byte[] imgnote){
        this.topic = topic;
        this.description = description;
        this.imgteamsnote=imgnote;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }
    public byte[] getImage() {
        return imgteamsnote;
    }
}
