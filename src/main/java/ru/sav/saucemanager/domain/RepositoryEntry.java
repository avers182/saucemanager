package ru.sav.saucemanager.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "o_repositoryentry")
public class RepositoryEntry {
    @Id
    @Column(name = "repositoryentry_id")
    private Long id;

    @Column(name = "displayname")
    private String displayName;

    @JoinColumn(name = "fk_olatresource")
    @ManyToOne
    private OlatResource olatResource;

    @JoinColumn(name = "fk_ownergroup")
    @ManyToOne
    private SecGroup ownerGroup;

    @JoinColumn(name = "fk_tutorgroup")
    @ManyToOne
    private SecGroup tutorGroup;

    @JoinColumn(name = "fk_participantgroup")
    @ManyToOne
    private SecGroup  participantGroup;

    @OneToMany(mappedBy = "repositoryEntry")
    private List<RepositoryEntryToBsGroup> repositoryEntryToBsGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonIgnore
    public SecGroup getParticipantGroup() {
        return participantGroup;
    }

    public void setParticipantGroup(SecGroup participantGroup) {
        this.participantGroup = participantGroup;
    }

    @JsonIgnore
    public SecGroup getTutorGroup() {
        return tutorGroup;
    }

    public void setTutorGroup(SecGroup tutorGroup) {
        this.tutorGroup = tutorGroup;
    }

    @JsonIgnore
    public SecGroup getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(SecGroup ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    @JsonIgnore
    public OlatResource getOlatResource() {
        return olatResource;
    }

    public void setOlatResource(OlatResource olatResource) {
        this.olatResource = olatResource;
    }

    @JsonIgnore
    public List<RepositoryEntryToBsGroup> getRepositoryEntryToBsGroups() {
        return repositoryEntryToBsGroups;
    }

    public void setRepositoryEntryToBsGroups(List<RepositoryEntryToBsGroup> repositoryEntryToBsGroups) {
        this.repositoryEntryToBsGroups = repositoryEntryToBsGroups;
    }
}
