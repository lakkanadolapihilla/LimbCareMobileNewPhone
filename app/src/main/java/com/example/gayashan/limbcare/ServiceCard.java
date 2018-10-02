package com.example.gayashan.limbcare;

public class ServiceCard {

    private String topic;
    private String description;
    private byte[] imgservicesA;

    public ServiceCard(String topic, String description,byte[] imgservicesA) {
        this.topic = topic;
        this.description = description;
        this.imgservicesA=imgservicesA;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }
    public byte[] getImage() {
        return imgservicesA;
    }

}
