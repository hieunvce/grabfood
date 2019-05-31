package com.example.cefood.API.OrderAPI;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderResponseFromAPI {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("skip")
    @Expose
    private Integer skip;
    @SerializedName("data")
    @Expose
    private List<OrderDataFromAPI> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderResponseFromAPI() {
    }

    /**
     *
     * @param limit
     * @param total
     * @param data
     * @param skip
     */
    public OrderResponseFromAPI(Integer total, Integer limit, Integer skip, List<OrderDataFromAPI> data) {
        super();
        this.total = total;
        this.limit = limit;
        this.skip = skip;
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public OrderResponseFromAPI withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public OrderResponseFromAPI withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public OrderResponseFromAPI withSkip(Integer skip) {
        this.skip = skip;
        return this;
    }

    public List<OrderDataFromAPI> getData() {
        return data;
    }

    public void setData(List<OrderDataFromAPI> data) {
        this.data = data;
    }

    public OrderResponseFromAPI withData(List<OrderDataFromAPI> data) {
        this.data = data;
        return this;
    }

}