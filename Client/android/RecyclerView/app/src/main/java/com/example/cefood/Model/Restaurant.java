package com.example.cefood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("active")
    @Expose
    private Boolean active;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public Restaurant() {
    }

    public Restaurant(String id, Boolean active, String img, String name, String address, String createdAt, String updatedAt) {
        this.id = id;
        this.active = active;
        this.img = img;
        this.name = name;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Restaurant withId (String id){
        this.id = id;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Restaurant withActive(Boolean active){
        this.active = active;
        return this;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Restaurant withImg(String img){
        this.img = img;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant withName(String name){
        this.name = name;
        return  this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Restaurant withAddress(String address){
        this.address = address;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Restaurant withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Restaurant withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
