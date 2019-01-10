package com.orange.secudtsi.repository;

import com.orange.secudtsi.domain.ComposantImpacte;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ComposantImpacte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComposantImpacteRepository extends JpaRepository<ComposantImpacte, Long> {

}
