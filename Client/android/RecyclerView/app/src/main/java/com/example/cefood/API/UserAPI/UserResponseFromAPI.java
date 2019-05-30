package com.example.cefood.API.UserAPI;

import com.example.cefood.Model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponseFromAPI {
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
    private List<User> data = null;

    /**
     * No args constructor for use in serialization
     */
    public UserResponseFromAPI() {
    }

    /**
     * @param limit
     * @param total
     * @param data
     * @param skip
     */
    public UserResponseFromAPI(Integer total, Integer limit, Integer skip, List<User> data) {
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

    public UserResponseFromAPI withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public UserResponseFromAPI withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public UserResponseFromAPI withSkip(Integer skip) {
        this.skip = skip;
        return this;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public UserResponseFromAPI withData(List<User> data) {
        this.data = data;
        return this;
    }

}

