package ru.sav.saucemanager.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "o_user")
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<UserProperty> userProperties;

    @OneToMany(mappedBy = "user")
    private List<Identity> identities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserProperty> getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(List<UserProperty> userProperties) {
        this.userProperties = userProperties;
    }

    public List<Identity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<Identity> identities) {
        this.identities = identities;
    }
}
