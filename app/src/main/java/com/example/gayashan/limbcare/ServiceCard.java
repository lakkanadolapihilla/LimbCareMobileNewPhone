package com.example.gayashan.limbcare;

public class ServiceCard {

    private String topic;
    private String description;

    public ServiceCard(String topic, String description) {
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
