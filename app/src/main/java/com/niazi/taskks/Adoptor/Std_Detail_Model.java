package com.niazi.taskks.Adoptor;

public class Std_Detail_Model {
    String name,phoneno;

    int image;

    public Std_Detail_Model(String name, String phoneno, int image) {
        this.name = name;
        this.phoneno = phoneno;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public int getImage() {
        return image;
    }
}
