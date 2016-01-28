package ru.sav.saucemanager.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "o_gp_business")
public class GpBusiness {
    @Id
    @Column(name = "group_id")
    private Long Id;

    @Column(name = "creationdate")
    private Timestamp creationDate;

    @Column(name = "groupname")
    private String groupName;

    @JoinColumn(name = "fk_resource")
    @ManyToOne()
    private OlatResource resource;

    @JoinColumn(name = "fk_ownergroup")
    @ManyToOne()
    private SecGroup ownerGroup;

    @JoinColumn(name = "fk_partipiciantgroup")
    @ManyToOne()
    private SecGroup participantGroup;

    @JoinColumn(name = "fk_waitinggroup")
    @ManyToOne()
    private SecGroup waitingGroup;

    @JoinColumn(name = "fk_group_id")
    @ManyToOne()
    private BsGroup bsGroup;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public OlatResource getResource() {
        return resource;
    }

    public void setResource(OlatResource resource) {
        this.resource = resource;
    }

    public SecGroup getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(SecGroup ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public SecGroup getParticipantGroup() {
        return participantGroup;
    }

    public void setParticipantGroup(SecGroup participantGroup) {
        this.participantGroup = participantGroup;
    }

    public SecGroup getWaitingGroup() {
        return waitingGroup;
    }

    public void setWaitingGroup(SecGroup waitingGroup) {
        this.waitingGroup = waitingGroup;
    }
}
