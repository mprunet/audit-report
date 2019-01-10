package com.orange.secudtsi.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PrivilegesNecessaires.
 */
@Entity
@Table(name = "privileges_necessaires")
public class PrivilegesNecessaires implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "poids")
    private Float poids;

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

    public PrivilegesNecessaires nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPoids() {
        return poids;
    }

    public PrivilegesNecessaires poids(Float poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(Float poids) {
        this.poids = poids;
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
        PrivilegesNecessaires privilegesNecessaires = (PrivilegesNecessaires) o;
        if (privilegesNecessaires.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), privilegesNecessaires.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrivilegesNecessaires{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", poids=" + getPoids() +
            "}";
    }
}
