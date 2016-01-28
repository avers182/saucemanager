package ru.sav.saucemanager.domain;

import javax.persistence.*;

@Entity
@IdClass(UserPropertyCompositeKey.class)
@Table(name = "o_userproperty")
public class UserProperty {
    @Id
    @JoinColumn(name = "fk_user_id")
    @ManyToOne
    private User user;

    @Id
    @Column(name = "propname")
    private String propName;

    @Column(name = "propvalue")
    private String propValue;

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
