package com.example.gayashan.limbcare;

public class GalleryCard {

    private String topic;
    private  String idgallery;
    private String description;
    private byte[] imgGalleryne;

    public GalleryCard( String idgallery,String topic, String description,byte[] imgGallerynei){
        this.topic = topic;
        this.idgallery=idgallery;
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

    public String getIdgallery() {
        return idgallery;
    }


}
