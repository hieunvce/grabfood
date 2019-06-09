package com.example.cefood.API.OrderAPI;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OrderForm {

    @SerializedName("items")
    @Expose
    private List<OrderFormItem> items = null;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;


    /**
     * No args constructor for use in serialization
     */
    public OrderForm() {
    }

    /**
     * @param total
     * @param items
     */
    public OrderForm(List<OrderFormItem> items, Integer total) {
        super();
        this.items = items;
        this.total = total;
    }

    public List<OrderFormItem> getItems() {
        return items;
    }

    public void setItems(List<OrderFormItem> items) {
        this.items = items;
    }

    public OrderForm withItems(List<OrderFormItem> items) {
        this.items = items;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public OrderForm withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderForm withName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public OrderForm withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OrderForm withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    //TODO: get address and phone add to order
}