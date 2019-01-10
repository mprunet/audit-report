package com.orange.secudtsi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.orange.secudtsi.domain.VulnerabiliteRef;
import com.orange.secudtsi.repository.VulnerabiliteRefRepository;
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
 * REST controller for managing VulnerabiliteRef.
 */
@RestController
@RequestMapping("/api")
public class VulnerabiliteRefResource {

    private final Logger log = LoggerFactory.getLogger(VulnerabiliteRefResource.class);

    private static final String ENTITY_NAME = "vulnerabiliteRef";

    private final VulnerabiliteRefRepository vulnerabiliteRefRepository;

    public VulnerabiliteRefResource(VulnerabiliteRefRepository vulnerabiliteRefRepository) {
        this.vulnerabiliteRefRepository = vulnerabiliteRefRepository;
    }

    /**
     * POST  /vulnerabilite-refs : Create a new vulnerabiliteRef.
     *
     * @param vulnerabiliteRef the vulnerabiliteRef to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vulnerabiliteRef, or with status 400 (Bad Request) if the vulnerabiliteRef has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vulnerabilite-refs")
    @Timed
    public ResponseEntity<VulnerabiliteRef> createVulnerabiliteRef(@RequestBody VulnerabiliteRef vulnerabiliteRef) throws URISyntaxException {
        log.debug("REST request to save VulnerabiliteRef : {}", vulnerabiliteRef);
        if (vulnerabiliteRef.getId() != null) {
            throw new BadRequestAlertException("A new vulnerabiliteRef cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VulnerabiliteRef result = vulnerabiliteRefRepository.save(vulnerabiliteRef);
        return ResponseEntity.created(new URI("/api/vulnerabilite-refs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vulnerabilite-refs : Updates an existing vulnerabiliteRef.
     *
     * @param vulnerabiliteRef the vulnerabiliteRef to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vulnerabiliteRef,
     * or with status 400 (Bad Request) if the vulnerabiliteRef is not valid,
     * or with status 500 (Internal Server Error) if the vulnerabiliteRef couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vulnerabilite-refs")
    @Timed
    public ResponseEntity<VulnerabiliteRef> updateVulnerabiliteRef(@RequestBody VulnerabiliteRef vulnerabiliteRef) throws URISyntaxException {
        log.debug("REST request to update VulnerabiliteRef : {}", vulnerabiliteRef);
        if (vulnerabiliteRef.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VulnerabiliteRef result = vulnerabiliteRefRepository.save(vulnerabiliteRef);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vulnerabiliteRef.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vulnerabilite-refs : get all the vulnerabiliteRefs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vulnerabiliteRefs in body
     */
    @GetMapping("/vulnerabilite-refs")
    @Timed
    public List<VulnerabiliteRef> getAllVulnerabiliteRefs() {
        log.debug("REST request to get all VulnerabiliteRefs");
        return vulnerabiliteRefRepository.findAll();
    }

    /**
     * GET  /vulnerabilite-refs/:id : get the "id" vulnerabiliteRef.
     *
     * @param id the id of the vulnerabiliteRef to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vulnerabiliteRef, or with status 404 (Not Found)
     */
    @GetMapping("/vulnerabilite-refs/{id}")
    @Timed
    public ResponseEntity<VulnerabiliteRef> getVulnerabiliteRef(@PathVariable Long id) {
        log.debug("REST request to get VulnerabiliteRef : {}", id);
        Optional<VulnerabiliteRef> vulnerabiliteRef = vulnerabiliteRefRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vulnerabiliteRef);
    }

    /**
     * DELETE  /vulnerabilite-refs/:id : delete the "id" vulnerabiliteRef.
     *
     * @param id the id of the vulnerabiliteRef to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vulnerabilite-refs/{id}")
    @Timed
    public ResponseEntity<Void> deleteVulnerabiliteRef(@PathVariable Long id) {
        log.debug("REST request to delete VulnerabiliteRef : {}", id);

        vulnerabiliteRefRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
