package ru.sav.saucemanager.model;

import javax.persistence.*;

@Entity
@Table(name = "o_references")
public class References {
    @Id
    @Column(name = "reference_id")
    private Long id;

    @JoinColumn(name = "source_id")
    @ManyToOne
    private OlatResource source;

    @JoinColumn(name = "target_id")
    @ManyToOne
    private OlatResource target;

    @Column(name = "userdata")
    private Long userdata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserdata() {
        return userdata;
    }

    public void setUserdata(Long userdata) {
        this.userdata = userdata;
    }

    public OlatResource getSource() {
        return source;
    }

    public void setSource(OlatResource sourceId) {
        this.source = sourceId;
    }

    public OlatResource getTarget() {
        return target;
    }

    public void setTarget(OlatResource targetId) {
        this.target = targetId;
    }
}
