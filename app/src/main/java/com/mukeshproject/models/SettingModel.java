package com.mukeshproject.models;

import java.util.ArrayList;

/**
 * Created by lenovo pc on 09/09/2017.
 */

public class SettingModel {

    private ArrayList<Userdetails> userdetails = new ArrayList<>();
    private ArrayList<MenuResponse> menudetails = new ArrayList<>();

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
}
