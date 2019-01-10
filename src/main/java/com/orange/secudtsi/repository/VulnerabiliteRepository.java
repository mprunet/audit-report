package com.orange.secudtsi.repository;

import com.orange.secudtsi.domain.Vulnerabilite;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Vulnerabilite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VulnerabiliteRepository extends JpaRepository<Vulnerabilite, Long> {

}
