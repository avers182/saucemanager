package ru.sav.saucemanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="o_olatresource")
public class OlatResource {
    @Id
    @Column(name="resource_id")
    private Long id;

    @Column(name="resid")
    private Long resId;

    @Column(name="resname")
    private String resName;

    @OneToMany(mappedBy = "olatResource")
    private List<RepositoryEntry> repositoryEntries;

    @OneToMany(mappedBy = "olatResource")
    private List<GpBusinessToResource> gpBusinessToResources;

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resid) {
        this.resId = resid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public List<RepositoryEntry> getRepositoryEntries() {
        return repositoryEntries;
    }

    public void setRepositoryEntries(List<RepositoryEntry> repositoryEntries) {
        this.repositoryEntries = repositoryEntries;
    }

    public List<GpBusinessToResource> getGpBusinessToResources() {
        return gpBusinessToResources;
    }

    public void setGpBusinessToResources(List<GpBusinessToResource> gpBusinessToResources) {
        this.gpBusinessToResources = gpBusinessToResources;
    }
}