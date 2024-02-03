package com.RPCompanion.entities;
import java.sql.Blob;

public class PowerEntity {
    private int id;
    private int rpCharacterId;
    private String name;
    private Blob image;
    private String description;
    public PowerEntity(){}
    public PowerEntity(int rpCharacterId, String name, Blob image, String description) {
        this.rpCharacterId = rpCharacterId;
        this.name = name;
        this.image = image;
        this.description = description;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Blob getImage() {
        return image;
    }
    public void setImage(Blob image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PowerEntity{" +
                "id=" + id +
                ", rpCharacterId=" + rpCharacterId +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", description='" + description + '\'' +
                '}';
    }
}
