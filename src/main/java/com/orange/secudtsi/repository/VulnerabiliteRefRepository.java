package com.orange.secudtsi.repository;

import com.orange.secudtsi.domain.VulnerabiliteRef;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VulnerabiliteRef entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VulnerabiliteRefRepository extends JpaRepository<VulnerabiliteRef, Long> {

}
