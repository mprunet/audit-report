package com.orange.secudtsi.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Projet.
 */
@Entity
@Table(name = "projet")
public class Projet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "basicat")
    private String basicat;

    @Column(name = "orange_carto")
    private String orangeCarto;

    @Column(name = "periscope")
    private String periscope;

    @Column(name = "version")
    private String version;

    @Column(name = "nom")
    private String nom;

    @Column(name = "moe")
    private String moe;

    @Column(name = "cdp")
    private String cdp;

    @Column(name = "rssi")
    private String rssi;

    @Column(name = "cssi")
    private String cssi;

    @Column(name = "ds")
    private String ds;

    @Column(name = "dp")
    private String dp;

    @Column(name = "type_audit")
    private String typeAudit;

    @Column(name = "entreprise_auditeur")
    private String entrepriseAuditeur;

    @Column(name = "date_restitution")
    private LocalDate dateRestitution;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBasicat() {
        return basicat;
    }

    public Projet basicat(String basicat) {
        this.basicat = basicat;
        return this;
    }

    public void setBasicat(String basicat) {
        this.basicat = basicat;
    }

    public String getOrangeCarto() {
        return orangeCarto;
    }

    public Projet orangeCarto(String orangeCarto) {
        this.orangeCarto = orangeCarto;
        return this;
    }

    public void setOrangeCarto(String orangeCarto) {
        this.orangeCarto = orangeCarto;
    }

    public String getPeriscope() {
        return periscope;
    }

    public Projet periscope(String periscope) {
        this.periscope = periscope;
        return this;
    }

    public void setPeriscope(String periscope) {
        this.periscope = periscope;
    }

    public String getVersion() {
        return version;
    }

    public Projet version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNom() {
        return nom;
    }

    public Projet nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMoe() {
        return moe;
    }

    public Projet moe(String moe) {
        this.moe = moe;
        return this;
    }

    public void setMoe(String moe) {
        this.moe = moe;
    }

    public String getCdp() {
        return cdp;
    }

    public Projet cdp(String cdp) {
        this.cdp = cdp;
        return this;
    }

    public void setCdp(String cdp) {
        this.cdp = cdp;
    }

    public String getRssi() {
        return rssi;
    }

    public Projet rssi(String rssi) {
        this.rssi = rssi;
        return this;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public String getCssi() {
        return cssi;
    }

    public Projet cssi(String cssi) {
        this.cssi = cssi;
        return this;
    }

    public void setCssi(String cssi) {
        this.cssi = cssi;
    }

    public String getDs() {
        return ds;
    }

    public Projet ds(String ds) {
        this.ds = ds;
        return this;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getDp() {
        return dp;
    }

    public Projet dp(String dp) {
        this.dp = dp;
        return this;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getTypeAudit() {
        return typeAudit;
    }

    public Projet typeAudit(String typeAudit) {
        this.typeAudit = typeAudit;
        return this;
    }

    public void setTypeAudit(String typeAudit) {
        this.typeAudit = typeAudit;
    }

    public String getEntrepriseAuditeur() {
        return entrepriseAuditeur;
    }

    public Projet entrepriseAuditeur(String entrepriseAuditeur) {
        this.entrepriseAuditeur = entrepriseAuditeur;
        return this;
    }

    public void setEntrepriseAuditeur(String entrepriseAuditeur) {
        this.entrepriseAuditeur = entrepriseAuditeur;
    }

    public LocalDate getDateRestitution() {
        return dateRestitution;
    }

    public Projet dateRestitution(LocalDate dateRestitution) {
        this.dateRestitution = dateRestitution;
        return this;
    }

    public void setDateRestitution(LocalDate dateRestitution) {
        this.dateRestitution = dateRestitution;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projet projet = (Projet) o;
        if (projet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Projet{" +
            "id=" + getId() +
            ", basicat='" + getBasicat() + "'" +
            ", orangeCarto='" + getOrangeCarto() + "'" +
            ", periscope='" + getPeriscope() + "'" +
            ", version='" + getVersion() + "'" +
            ", nom='" + getNom() + "'" +
            ", moe='" + getMoe() + "'" +
            ", cdp='" + getCdp() + "'" +
            ", rssi='" + getRssi() + "'" +
            ", cssi='" + getCssi() + "'" +
            ", ds='" + getDs() + "'" +
            ", dp='" + getDp() + "'" +
            ", typeAudit='" + getTypeAudit() + "'" +
            ", entrepriseAuditeur='" + getEntrepriseAuditeur() + "'" +
            ", dateRestitution='" + getDateRestitution() + "'" +
            "}";
    }
}
