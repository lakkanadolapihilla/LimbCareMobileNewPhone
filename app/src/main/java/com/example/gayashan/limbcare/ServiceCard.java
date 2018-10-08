package com.example.gayashan.limbcare;

public class ServiceCard {

    private String topic;
    private String description;
    private String type;
    private String price;
    private byte[] imgservicesA;

    public ServiceCard(String topic, String description,byte[] imgservicesA, String type, String price) {
        this.type = type;
        this.price = price;
        this.topic = topic;
        this.description = description;
        this.imgservicesA=imgservicesA;
    }

    public String getTopic() {
        return topic;
    }
    public String getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public byte[] getImage() {
        return imgservicesA;
    }

}
