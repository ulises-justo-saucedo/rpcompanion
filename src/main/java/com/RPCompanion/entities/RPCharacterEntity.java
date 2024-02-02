package com.RPCompanion.entities;
import java.time.LocalDate;
public class RPCharacterEntity {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private int age;
    private String story;
    private String aspect;

    public RPCharacterEntity(){}

    public RPCharacterEntity(String name, String surname, LocalDate birthDate, int age, String story, String aspect) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.age = age;
        this.story = story;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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

    public void setHistory(String story) {
        this.story = story;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }
}
