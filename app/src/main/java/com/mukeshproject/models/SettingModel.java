package com.mukeshproject.models;

import java.util.ArrayList;

/**
 * Created by lenovo pc on 09/09/2017.
 */

public class SettingModel {

    private ArrayList<Userdetails> userdetails = new ArrayList<>();
    private ArrayList<MenuResponse> menudetails = new ArrayList<>();
    private ArrayList<SlidingImageModel> slider = new ArrayList<>();
    private ArrayList<ProductitemListModel>productlist = new ArrayList<>();

    public ArrayList<ProductitemListModel> getProductlist() {
        return productlist;
    }

    public void setProductlist(ArrayList<ProductitemListModel> productlist) {
        this.productlist = productlist;
    }

    public ArrayList<Userdetails> getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(ArrayList<Userdetails> userdetails) {
        this.userdetails = userdetails;
    }

    public ArrayList<MenuResponse> getMenudetails() {
        return menudetails;
    }

    public void setMenudetails(ArrayList<MenuResponse> menudetails) {
        this.menudetails = menudetails;
    }

    public ArrayList<SlidingImageModel> getSlider() {
        return slider;
    }

    public void setSlider(ArrayList<SlidingImageModel> slider) {
        this.slider = slider;
    }
}
