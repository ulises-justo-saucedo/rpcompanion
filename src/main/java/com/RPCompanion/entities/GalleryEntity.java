package com.RPCompanion.entities;
import java.sql.Blob;

public class GalleryEntity {
    private int id;
    private int rpCharacterId;
    private Blob image;
    public GalleryEntity(){

    }
    public GalleryEntity(int rpCharacterId, Blob image) {
        this.rpCharacterId = rpCharacterId;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRpCharacterId() {
        return rpCharacterId;
    }

    public void setRpCharacterId(int rpCharacterId) {
        this.rpCharacterId = rpCharacterId;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "GalleryEntity{" +
                "id=" + id +
                ", characterId=" + rpCharacterId +
                ", image=" + image +
                '}';
    }
}
