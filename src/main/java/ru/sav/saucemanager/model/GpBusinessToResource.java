package ru.sav.saucemanager.model;

import javax.persistence.*;

@Entity
@Table(name = "o_gp_business_to_resource")
public class GpBusinessToResource {
    @Id
    @Column(name = "g_id")
    private Long Id;

    @JoinColumn(name = "fk_resource")
    @ManyToOne()
    private OlatResource olatResource;

    @JoinColumn(name = "fk_group")
    @ManyToOne()
    private GpBusiness gpBusiness;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public OlatResource getOlatResource() {
        return olatResource;
    }

    public void setOlatResource(OlatResource olatResource) {
        this.olatResource = olatResource;
    }

    public GpBusiness getGpBusiness() {
        return gpBusiness;
    }

    public void setGpBusiness(GpBusiness gpBusiness) {
        this.gpBusiness = gpBusiness;
    }
}
