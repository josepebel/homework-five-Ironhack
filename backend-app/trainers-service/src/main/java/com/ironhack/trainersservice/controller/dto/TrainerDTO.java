package com.ironhack.trainersservice.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TrainerDTO {
    @NotEmpty(message = "Trainer name is required")
    private String name;
    @NotNull(message="Age required")
    private byte age;
    private String hobby;
    private String picture;

    public TrainerDTO() {
    }

    public TrainerDTO(String name, byte age, String hobby, String picture) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
