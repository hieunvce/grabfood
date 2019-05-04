package com.example.recyclerview.DTO;

public class DataCartDTO {
    private String HinhAnh;
    private String Ten;
    private String Gia;
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public DataCartDTO(String hinhAnh, String ten, String gia, String number) {
        HinhAnh = hinhAnh;
        Ten = ten;
        Gia = gia;
        this.number = number;
    }
}
