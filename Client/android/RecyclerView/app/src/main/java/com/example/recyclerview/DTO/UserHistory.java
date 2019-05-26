package com.example.recyclerview.DTO;

public class UserHistory {
    private String id;
    private String fullName;
    private String Price;
    private String Food;
    private String Date;

    public UserHistory(String id, String fullName, String price, String food, String date) {
        this.id = id;
        this.fullName = fullName;
        this.Price = price;
        this.Food = food;
        this.Date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
