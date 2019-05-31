package com.example.cefood.API.OrderAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItemFromAPI {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderItemFromAPI() {
    }

    /**
     *
     * @param id
     * @param price
     * @param name
     * @param img
     * @param quantity
     */
    public OrderItemFromAPI(String id, String img, String name, Integer price, Integer quantity) {
        super();
        this.id = id;
        this.img = img;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderItemFromAPI withId(String id) {
        this.id = id;
        return this;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public OrderItemFromAPI withImg(String img) {
        this.img = img;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderItemFromAPI withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public OrderItemFromAPI withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderItemFromAPI withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

}
