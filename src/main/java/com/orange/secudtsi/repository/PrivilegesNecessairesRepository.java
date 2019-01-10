package com.orange.secudtsi.repository;

import com.orange.secudtsi.domain.PrivilegesNecessaires;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PrivilegesNecessaires entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrivilegesNecessairesRepository extends JpaRepository<PrivilegesNecessaires, Long> {

}
