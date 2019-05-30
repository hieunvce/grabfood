package com.example.cefood.API.RestaurantAPI;

import com.example.cefood.Model.Restaurant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantsResponseFromAPI {
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
    private List<Restaurant> data = null;

    public RestaurantsResponseFromAPI() {

    }

    public RestaurantsResponseFromAPI(Integer total, Integer limit, Integer skip, List<Restaurant> data) {
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

    public RestaurantsResponseFromAPI withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public RestaurantsResponseFromAPI withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public RestaurantsResponseFromAPI withSkip(Integer skip) {
        this.skip = skip;
        return this;
    }

    public List<Restaurant> getData() {
        return data;
    }

    public void setData(List<Restaurant> data) {
        this.data = data;
    }

    public RestaurantsResponseFromAPI withData(List<Restaurant> data) {
        this.data = data;
        return this;
    }
}
