package ru.sav.saucemanager.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "o_qtiresultset")
public class QtiResultSet {
    @Id
    @Column(name = "resultset_id")
    private Long id;

    @Column(name = "lastmodified")
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastModified;

    @Column(name = "creationdate")
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;

    @JoinColumn(name = "identity_id")
    @ManyToOne
    private Identity identity;

    @Column(name = "olatresource_fk")
    private Long olatResourceResid;

    @JoinColumn(name = "repositoryref_fk")
    @ManyToOne
    private RepositoryEntry repositoryEntry;

    @Column(name = "ispassed")
    private Boolean isPassed;

    @Column(name = "score")
    private Float score;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "issuspended")
    private Boolean isSuspended;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(Boolean isPassed) {
        this.isPassed = isPassed;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(Boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Long getOlatResourceResid() {
        return olatResourceResid;
    }

    public void setOlatResourceResid(Long olatResourceResid) {
        this.olatResourceResid = olatResourceResid;
    }

    public RepositoryEntry getRepositoryEntry() {
        return repositoryEntry;
    }

    public void setRepositoryEntry(RepositoryEntry repositoryEntry) {
        this.repositoryEntry = repositoryEntry;
    }
}
