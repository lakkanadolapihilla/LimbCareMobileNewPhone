package com.example.gayashan.limbcare;

public class NoticeCard {

    private String topic;
    private String description;

    public NoticeCard(String topic, String description){
        this.topic = topic;
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }
}
