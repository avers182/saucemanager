package ru.sav.saucemanager.model;


import java.io.Serializable;

public class UserPropertyCompositeKey implements Serializable{
    private User user;
    private String propName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }
}
