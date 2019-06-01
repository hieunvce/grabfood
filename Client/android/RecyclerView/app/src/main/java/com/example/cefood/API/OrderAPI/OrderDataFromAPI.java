package com.example.cefood.API.OrderAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderDataFromAPI implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("deliveryStatus")
    @Expose
    private Boolean deliveryStatus;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("items")
    @Expose
    private List<OrderItemFromAPI> items = null;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderDataFromAPI() {
    }

    /**
     *
     * @param updatedAt
     * @param total
     * @param id
     * @param v
     * @param items
     * @param createdAt
     * @param userId
     * @param deliveryStatus
     */
    public OrderDataFromAPI(String id, Boolean deliveryStatus, Integer total, List<OrderItemFromAPI> items, String userId, String createdAt, String updatedAt, Integer v) {
        super();
        this.id = id;
        this.deliveryStatus = deliveryStatus;
        this.total = total;
        this.items = items;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderDataFromAPI withId(String id) {
        this.id = id;
        return this;
    }

    public Boolean getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Boolean deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public OrderDataFromAPI withDeliveryStatus(Boolean deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public OrderDataFromAPI withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public List<OrderItemFromAPI> getItems() {
        return items;
    }

    public void setItems(List<OrderItemFromAPI> items) {
        this.items = items;
    }

    public OrderDataFromAPI withItems(List<OrderItemFromAPI> items) {
        this.items = items;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OrderDataFromAPI withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public OrderDataFromAPI withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OrderDataFromAPI withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public OrderDataFromAPI withV(Integer v) {
        this.v = v;
        return this;
    }

}
