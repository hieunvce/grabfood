package com.example.cefood.DTO;

public class DataDiscoverShopDTO {
    private int HinhAnh;
    private String Ten;

    public DataDiscoverShopDTO(int hinhAnh, String ten) {
        HinhAnh = hinhAnh;
        Ten = ten;
    }

    public int getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }
}
