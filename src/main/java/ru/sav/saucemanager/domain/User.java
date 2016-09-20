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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
