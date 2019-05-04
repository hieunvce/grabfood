package com.example.recyclerview.DTO;

import java.io.Serializable;

public class DataDishDTO implements Serializable {
    private String HinhAnh;
    private String Ten;
    private String Gia;
    private String SoLuong;

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public DataDishDTO(String hinhAnh, String ten, String gia, String soLuong) {
        HinhAnh = hinhAnh;
        Ten = ten;
        Gia = gia;
        SoLuong = soLuong;
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

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }


}
