package com.example.cefood.API.UserAPI;

public class EditUserForm {
    private String email;
    private String password;
    private String name;
    private String gender;
    private String image;

    public EditUserForm() {
    }

    public EditUserForm(String email, String password, String name, String gender, String image) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
