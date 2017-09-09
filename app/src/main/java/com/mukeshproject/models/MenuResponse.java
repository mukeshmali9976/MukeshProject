package com.mukeshproject.models;

import java.util.ArrayList;

/**
 * Created by lenovo pc on 09/09/2017.
 */

public class MenuResponse {
    private String menu_id;
    private String subsubcat_id;
    private String meta_discription;
    private String submenu_id;
    private String status;
    private String focus_keyword;
    private String priority;
    private String subsubmenuurl;
    private String subsubmenu_id;
    private String slug;
    private String subcat_id;
    private String seotitle;
    private String subsubmenu;
    private String submenu;
    private String submenuurl;
    private String cat_id;
    private String menu_nameurl;
    private String menu_name;
    private ArrayList<MenuResponse> submenu_details = new ArrayList<>();
    private ArrayList<MenuResponse> subsubmenudetails = new ArrayList<>();

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getSubsubcat_id() {
        return subsubcat_id;
    }

    public void setSubsubcat_id(String subsubcat_id) {
        this.subsubcat_id = subsubcat_id;
    }

    public String getMeta_discription() {
        return meta_discription;
    }

    public void setMeta_discription(String meta_discription) {
        this.meta_discription = meta_discription;
    }

    public String getSubmenu_id() {
        return submenu_id;
    }

    public void setSubmenu_id(String submenu_id) {
        this.submenu_id = submenu_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFocus_keyword() {
        return focus_keyword;
    }

    public void setFocus_keyword(String focus_keyword) {
        this.focus_keyword = focus_keyword;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSubsubmenuurl() {
        return subsubmenuurl;
    }

    public void setSubsubmenuurl(String subsubmenuurl) {
        this.subsubmenuurl = subsubmenuurl;
    }

    public String getSubsubmenu_id() {
        return subsubmenu_id;
    }

    public void setSubsubmenu_id(String subsubmenu_id) {
        this.subsubmenu_id = subsubmenu_id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSubcat_id() {
        return subcat_id;
    }

    public void setSubcat_id(String subcat_id) {
        this.subcat_id = subcat_id;
    }

    public String getSeotitle() {
        return seotitle;
    }

    public void setSeotitle(String seotitle) {
        this.seotitle = seotitle;
    }

    public String getSubsubmenu() {
        return subsubmenu;
    }

    public void setSubsubmenu(String subsubmenu) {
        this.subsubmenu = subsubmenu;
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    public String getSubmenuurl() {
        return submenuurl;
    }

    public void setSubmenuurl(String submenuurl) {
        this.submenuurl = submenuurl;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getMenu_nameurl() {
        return menu_nameurl;
    }

    public void setMenu_nameurl(String menu_nameurl) {
        this.menu_nameurl = menu_nameurl;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public ArrayList<MenuResponse> getSubmenu_details() {
        return submenu_details;
    }

    public void setSubmenu_details(ArrayList<MenuResponse> submenu_details) {
        this.submenu_details = submenu_details;
    }

    public ArrayList<MenuResponse> getSubsubmenudetails() {
        return subsubmenudetails;
    }

    public void setSubsubmenudetails(ArrayList<MenuResponse> subsubmenudetails) {
        this.subsubmenudetails = subsubmenudetails;
    }
}
