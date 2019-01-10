package com.orange.secudtsi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Vulnerabilite.
 */
@Entity
@Table(name = "vulnerabilite")
public class Vulnerabilite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_ref")
    private String ref;

    @Column(name = "categorie")
    private String categorie;

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
    private Projet projet;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PrivilegesNecessaires privilegesNecessaires;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ComposantImpacte composantImpacte;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public Vulnerabilite ref(String ref) {
        this.ref = ref;
        return this;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCategorie() {
        return categorie;
    }

    public Vulnerabilite categorie(String categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public Vulnerabilite nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public Vulnerabilite description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getProbabilite() {
        return probabilite;
    }

    public Vulnerabilite probabilite(Float probabilite) {
        this.probabilite = probabilite;
        return this;
    }

    public void setProbabilite(Float probabilite) {
        this.probabilite = probabilite;
    }

    public Float getImpact() {
        return impact;
    }

    public Vulnerabilite impact(Float impact) {
        this.impact = impact;
        return this;
    }

    public void setImpact(Float impact) {
        this.impact = impact;
    }

    public String getCriticite() {
        return criticite;
    }

    public Vulnerabilite criticite(String criticite) {
        this.criticite = criticite;
        return this;
    }

    public void setCriticite(String criticite) {
        this.criticite = criticite;
    }

    public Float getCvss() {
        return cvss;
    }

    public Vulnerabilite cvss(Float cvss) {
        this.cvss = cvss;
        return this;
    }

    public void setCvss(Float cvss) {
        this.cvss = cvss;
    }

    public String getRecommandation() {
        return recommandation;
    }

    public Vulnerabilite recommandation(String recommandation) {
        this.recommandation = recommandation;
        return this;
    }

    public void setRecommandation(String recommandation) {
        this.recommandation = recommandation;
    }

    public Projet getProjet() {
        return projet;
    }

    public Vulnerabilite projet(Projet projet) {
        this.projet = projet;
        return this;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public PrivilegesNecessaires getPrivilegesNecessaires() {
        return privilegesNecessaires;
    }

    public Vulnerabilite privilegesNecessaires(PrivilegesNecessaires privilegesNecessaires) {
        this.privilegesNecessaires = privilegesNecessaires;
        return this;
    }

    public void setPrivilegesNecessaires(PrivilegesNecessaires privilegesNecessaires) {
        this.privilegesNecessaires = privilegesNecessaires;
    }

    public ComposantImpacte getComposantImpacte() {
        return composantImpacte;
    }

    public Vulnerabilite composantImpacte(ComposantImpacte composantImpacte) {
        this.composantImpacte = composantImpacte;
        return this;
    }

    public void setComposantImpacte(ComposantImpacte composantImpacte) {
        this.composantImpacte = composantImpacte;
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
        Vulnerabilite vulnerabilite = (Vulnerabilite) o;
        if (vulnerabilite.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vulnerabilite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vulnerabilite{" +
            "id=" + getId() +
            ", ref='" + getRef() + "'" +
            ", categorie='" + getCategorie() + "'" +
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
