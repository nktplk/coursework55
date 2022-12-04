package com.model.user;


import java.io.Serializable;

public class CurrentUser extends User implements Serializable {

    private static CurrentUser instance;
    //private Country country;
    private String lastAccessDate;

    private CurrentUser(String login, String password, String role) {
        super(login, password, role);
        //this.country = country;
    }

    public CurrentUser() {
        super("", "", "");
    }

    /*public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }*/

    public String getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(String lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public static CurrentUser getInstance(User user) {//(String login, String password, String country, String role) {
        if (instance == null && user != null) {
            instance = new CurrentUser(user.login, user.password, user.role);
        }
        return instance;
    }

    public static CurrentUser getUser() {
        return instance;
    }

    public static void nullInstance() {
        instance = null;
    }

}

