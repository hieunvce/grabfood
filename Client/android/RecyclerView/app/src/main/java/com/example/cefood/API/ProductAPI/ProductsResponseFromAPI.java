package com.example.cefood.API.ProductAPI;

import java.util.List;

import com.example.cefood.Model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsResponseFromAPI {

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
    private List<Product> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProductsResponseFromAPI() {
    }

    /**
     *
     * @param limit
     * @param total
     * @param data
     * @param skip
     */
    public ProductsResponseFromAPI(Integer total, Integer limit, Integer skip, List<Product> data) {
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

    public ProductsResponseFromAPI withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ProductsResponseFromAPI withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public ProductsResponseFromAPI withSkip(Integer skip) {
        this.skip = skip;
        return this;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public ProductsResponseFromAPI withData(List<Product> data) {
        this.data = data;
        return this;
    }

}