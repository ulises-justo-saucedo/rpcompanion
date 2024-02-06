package com.RPCompanion.entities;
import java.sql.Blob;
import java.sql.Date;
public class RPCharacterEntity {
    private int id;
    private String name;
    private String surname;
    private Date birthDate;
    private int age;
    private String story;
    private Blob aspect;

    public RPCharacterEntity(){}
    public RPCharacterEntity(String name, String surname, Date birthDate, int age, String story, Blob aspect) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.age = age;
        this.story = story;
        this.aspect = aspect;
    }
    public RPCharacterEntity(String name, String surname, Date birthDate, int age, Blob aspect) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.age = age;
        this.aspect = aspect;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Blob getAspect() {
        return aspect;
    }

    public void setAspect(Blob aspect) {
        this.aspect = aspect;
    }

    @Override
    public String toString() {
        return "RPCharacterEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", story='" + story + '\'' +
                ", aspect='" + aspect + '\'' +
                '}';
    }
}
