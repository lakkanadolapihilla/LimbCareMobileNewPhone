package com.example.gayashan.limbcare;

public class GalleryCard {

    private String topic;
    private String description;
    private byte[] imgGalleryne;

    public GalleryCard(String topic, String description,byte[] imgGallerynei){
        this.topic = topic;
        this.description = description;
        this.imgGalleryne=imgGallerynei;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }
    public byte[] getImage() {
        return imgGalleryne;
    }
}
