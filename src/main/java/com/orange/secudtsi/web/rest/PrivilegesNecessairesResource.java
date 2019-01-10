package com.orange.secudtsi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.orange.secudtsi.domain.PrivilegesNecessaires;
import com.orange.secudtsi.repository.PrivilegesNecessairesRepository;
import com.orange.secudtsi.web.rest.errors.BadRequestAlertException;
import com.orange.secudtsi.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PrivilegesNecessaires.
 */
@RestController
@RequestMapping("/api")
public class PrivilegesNecessairesResource {

    private final Logger log = LoggerFactory.getLogger(PrivilegesNecessairesResource.class);

    private static final String ENTITY_NAME = "privilegesNecessaires";

    private final PrivilegesNecessairesRepository privilegesNecessairesRepository;

    public PrivilegesNecessairesResource(PrivilegesNecessairesRepository privilegesNecessairesRepository) {
        this.privilegesNecessairesRepository = privilegesNecessairesRepository;
    }

    /**
     * POST  /privileges-necessaires : Create a new privilegesNecessaires.
     *
     * @param privilegesNecessaires the privilegesNecessaires to create
     * @return the ResponseEntity with status 201 (Created) and with body the new privilegesNecessaires, or with status 400 (Bad Request) if the privilegesNecessaires has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/privileges-necessaires")
    @Timed
    public ResponseEntity<PrivilegesNecessaires> createPrivilegesNecessaires(@RequestBody PrivilegesNecessaires privilegesNecessaires) throws URISyntaxException {
        log.debug("REST request to save PrivilegesNecessaires : {}", privilegesNecessaires);
        if (privilegesNecessaires.getId() != null) {
            throw new BadRequestAlertException("A new privilegesNecessaires cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrivilegesNecessaires result = privilegesNecessairesRepository.save(privilegesNecessaires);
        return ResponseEntity.created(new URI("/api/privileges-necessaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /privileges-necessaires : Updates an existing privilegesNecessaires.
     *
     * @param privilegesNecessaires the privilegesNecessaires to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated privilegesNecessaires,
     * or with status 400 (Bad Request) if the privilegesNecessaires is not valid,
     * or with status 500 (Internal Server Error) if the privilegesNecessaires couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/privileges-necessaires")
    @Timed
    public ResponseEntity<PrivilegesNecessaires> updatePrivilegesNecessaires(@RequestBody PrivilegesNecessaires privilegesNecessaires) throws URISyntaxException {
        log.debug("REST request to update PrivilegesNecessaires : {}", privilegesNecessaires);
        if (privilegesNecessaires.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrivilegesNecessaires result = privilegesNecessairesRepository.save(privilegesNecessaires);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, privilegesNecessaires.getId().toString()))
            .body(result);
    }

    /**
     * GET  /privileges-necessaires : get all the privilegesNecessaires.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of privilegesNecessaires in body
     */
    @GetMapping("/privileges-necessaires")
    @Timed
    public List<PrivilegesNecessaires> getAllPrivilegesNecessaires() {
        log.debug("REST request to get all PrivilegesNecessaires");
        return privilegesNecessairesRepository.findAll();
    }

    /**
     * GET  /privileges-necessaires/:id : get the "id" privilegesNecessaires.
     *
     * @param id the id of the privilegesNecessaires to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the privilegesNecessaires, or with status 404 (Not Found)
     */
    @GetMapping("/privileges-necessaires/{id}")
    @Timed
    public ResponseEntity<PrivilegesNecessaires> getPrivilegesNecessaires(@PathVariable Long id) {
        log.debug("REST request to get PrivilegesNecessaires : {}", id);
        Optional<PrivilegesNecessaires> privilegesNecessaires = privilegesNecessairesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(privilegesNecessaires);
    }

    /**
     * DELETE  /privileges-necessaires/:id : delete the "id" privilegesNecessaires.
     *
     * @param id the id of the privilegesNecessaires to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/privileges-necessaires/{id}")
    @Timed
    public ResponseEntity<Void> deletePrivilegesNecessaires(@PathVariable Long id) {
        log.debug("REST request to delete PrivilegesNecessaires : {}", id);

        privilegesNecessairesRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
