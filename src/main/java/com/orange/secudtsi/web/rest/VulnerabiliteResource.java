package com.orange.secudtsi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.orange.secudtsi.domain.Vulnerabilite;
import com.orange.secudtsi.repository.VulnerabiliteRepository;
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
 * REST controller for managing Vulnerabilite.
 */
@RestController
@RequestMapping("/api")
public class VulnerabiliteResource {

    private final Logger log = LoggerFactory.getLogger(VulnerabiliteResource.class);

    private static final String ENTITY_NAME = "vulnerabilite";

    private final VulnerabiliteRepository vulnerabiliteRepository;

    public VulnerabiliteResource(VulnerabiliteRepository vulnerabiliteRepository) {
        this.vulnerabiliteRepository = vulnerabiliteRepository;
    }

    /**
     * POST  /vulnerabilites : Create a new vulnerabilite.
     *
     * @param vulnerabilite the vulnerabilite to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vulnerabilite, or with status 400 (Bad Request) if the vulnerabilite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vulnerabilites")
    @Timed
    public ResponseEntity<Vulnerabilite> createVulnerabilite(@RequestBody Vulnerabilite vulnerabilite) throws URISyntaxException {
        log.debug("REST request to save Vulnerabilite : {}", vulnerabilite);
        if (vulnerabilite.getId() != null) {
            throw new BadRequestAlertException("A new vulnerabilite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Vulnerabilite result = vulnerabiliteRepository.save(vulnerabilite);
        return ResponseEntity.created(new URI("/api/vulnerabilites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vulnerabilites : Updates an existing vulnerabilite.
     *
     * @param vulnerabilite the vulnerabilite to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vulnerabilite,
     * or with status 400 (Bad Request) if the vulnerabilite is not valid,
     * or with status 500 (Internal Server Error) if the vulnerabilite couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vulnerabilites")
    @Timed
    public ResponseEntity<Vulnerabilite> updateVulnerabilite(@RequestBody Vulnerabilite vulnerabilite) throws URISyntaxException {
        log.debug("REST request to update Vulnerabilite : {}", vulnerabilite);
        if (vulnerabilite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Vulnerabilite result = vulnerabiliteRepository.save(vulnerabilite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vulnerabilite.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vulnerabilites : get all the vulnerabilites.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vulnerabilites in body
     */
    @GetMapping("/vulnerabilites")
    @Timed
    public List<Vulnerabilite> getAllVulnerabilites() {
        log.debug("REST request to get all Vulnerabilites");
        return vulnerabiliteRepository.findAll();
    }

    /**
     * GET  /vulnerabilites/:id : get the "id" vulnerabilite.
     *
     * @param id the id of the vulnerabilite to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vulnerabilite, or with status 404 (Not Found)
     */
    @GetMapping("/vulnerabilites/{id}")
    @Timed
    public ResponseEntity<Vulnerabilite> getVulnerabilite(@PathVariable Long id) {
        log.debug("REST request to get Vulnerabilite : {}", id);
        Optional<Vulnerabilite> vulnerabilite = vulnerabiliteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vulnerabilite);
    }

    /**
     * DELETE  /vulnerabilites/:id : delete the "id" vulnerabilite.
     *
     * @param id the id of the vulnerabilite to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vulnerabilites/{id}")
    @Timed
    public ResponseEntity<Void> deleteVulnerabilite(@PathVariable Long id) {
        log.debug("REST request to delete Vulnerabilite : {}", id);

        vulnerabiliteRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
