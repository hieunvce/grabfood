package com.example.cefood.API.OrderAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderFormItem {

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
     */
    public OrderFormItem() {
    }

    /**
     * @param price
     * @param name
     * @param img
     * @param quantity
     */
    public OrderFormItem(String img, String name, Integer price, Integer quantity) {
        super();
        this.img = img;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public OrderFormItem withImg(String img) {
        this.img = img;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderFormItem withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public OrderFormItem withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderFormItem withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

}