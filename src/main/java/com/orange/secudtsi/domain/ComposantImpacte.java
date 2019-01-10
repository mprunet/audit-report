package com.orange.secudtsi.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ComposantImpacte.
 */
@Entity
@Table(name = "composant_impacte")
public class ComposantImpacte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

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

    public ComposantImpacte nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        ComposantImpacte composantImpacte = (ComposantImpacte) o;
        if (composantImpacte.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), composantImpacte.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComposantImpacte{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
