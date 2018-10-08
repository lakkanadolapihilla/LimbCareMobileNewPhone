package com.example.gayashan.limbcare;

import android.widget.ImageView;

public class NoticeCard {

    private String topic;
    private String venue;
    private String date;
    private String time;
    private String description;
    public byte[] imgteamsnote;

    public NoticeCard(String topic, String venue, String date, String time, String description,byte[] imgnote){
        this.topic = topic;
        this.venue = venue;
        this.date = date;
        this.time = time;
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

    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
