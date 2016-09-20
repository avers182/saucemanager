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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QtiResultSet that = (QtiResultSet) o;

        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (identity != null ? !identity.equals(that.identity) : that.identity != null) return false;
        if (isPassed != null ? !isPassed.equals(that.isPassed) : that.isPassed != null) return false;
        if (isSuspended != null ? !isSuspended.equals(that.isSuspended) : that.isSuspended != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (olatResourceResid != null ? !olatResourceResid.equals(that.olatResourceResid) : that.olatResourceResid != null)
            return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (identity != null ? identity.hashCode() : 0);
        result = 31 * result + (olatResourceResid != null ? olatResourceResid.hashCode() : 0);
        result = 31 * result + (isPassed != null ? isPassed.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (isSuspended != null ? isSuspended.hashCode() : 0);
        return result;
    }
}
