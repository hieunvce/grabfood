package com.example.recyclerview;

public class DataShop {
    private String HinhAnh;
    private String Ten;
    private String Adddress;
    private String Id;

    public DataShop(String hinhAnh, String ten, String adddress, String id) {
        HinhAnh = hinhAnh;
        Ten = ten;
        Adddress = adddress;
        Id = id;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getAdddress() {
        return Adddress;
    }

    public void setAdddress(String adddress) {
        Adddress = adddress;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
