package com.mukeshproject.models;

import java.util.ArrayList;

/**
 * Created by lenovo pc on 27/08/2017.
 */

public class HomeCategoryModel {

    private String ID;
    private String Name;
    private String ImageName;

    private String ticketid;
    private String ticketname;
    private String ticketimagename;

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getTicketname() {
        return ticketname;
    }

    public void setTicketname(String ticketname) {
        this.ticketname = ticketname;
    }

    public String getTicketimagename() {
        return ticketimagename;
    }

    public void setTicketimagename(String ticketimagename) {
        this.ticketimagename = ticketimagename;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String ImageName) {
        this.ImageName = ImageName;
    }

}
