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

}