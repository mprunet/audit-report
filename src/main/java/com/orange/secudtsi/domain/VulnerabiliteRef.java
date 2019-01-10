package com.orange.secudtsi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A VulnerabiliteRef.
 */
@Entity
@Table(name = "vulnerabilite_ref")
public class VulnerabiliteRef implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "probabilite")
    private Float probabilite;

    @Column(name = "impact")
    private Float impact;

    @Column(name = "criticite")
    private String criticite;

    @Column(name = "cvss")
    private Float cvss;

    @Column(name = "recommandation")
    private String recommandation;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Vulnerabilite vulnRef;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public VulnerabiliteRef nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public VulnerabiliteRef description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getProbabilite() {
        return probabilite;
    }

    public VulnerabiliteRef probabilite(Float probabilite) {
        this.probabilite = probabilite;
        return this;
    }

    public void setProbabilite(Float probabilite) {
        this.probabilite = probabilite;
    }

    public Float getImpact() {
        return impact;
    }

    public VulnerabiliteRef impact(Float impact) {
        this.impact = impact;
        return this;
    }

    public void setImpact(Float impact) {
        this.impact = impact;
    }

    public String getCriticite() {
        return criticite;
    }

    public VulnerabiliteRef criticite(String criticite) {
        this.criticite = criticite;
        return this;
    }

    public void setCriticite(String criticite) {
        this.criticite = criticite;
    }

    public Float getCvss() {
        return cvss;
    }

    public VulnerabiliteRef cvss(Float cvss) {
        this.cvss = cvss;
        return this;
    }

    public void setCvss(Float cvss) {
        this.cvss = cvss;
    }

    public String getRecommandation() {
        return recommandation;
    }

    public VulnerabiliteRef recommandation(String recommandation) {
        this.recommandation = recommandation;
        return this;
    }

    public void setRecommandation(String recommandation) {
        this.recommandation = recommandation;
    }

    public Vulnerabilite getVulnRef() {
        return vulnRef;
    }

    public VulnerabiliteRef vulnRef(Vulnerabilite vulnerabilite) {
        this.vulnRef = vulnerabilite;
        return this;
    }

    public void setVulnRef(Vulnerabilite vulnerabilite) {
        this.vulnRef = vulnerabilite;
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
        VulnerabiliteRef vulnerabiliteRef = (VulnerabiliteRef) o;
        if (vulnerabiliteRef.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vulnerabiliteRef.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VulnerabiliteRef{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", probabilite=" + getProbabilite() +
            ", impact=" + getImpact() +
            ", criticite='" + getCriticite() + "'" +
            ", cvss=" + getCvss() +
            ", recommandation='" + getRecommandation() + "'" +
            "}";
    }
}
