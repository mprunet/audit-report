package com.orange.secudtsi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.orange.secudtsi.domain.ComposantImpacte;
import com.orange.secudtsi.repository.ComposantImpacteRepository;
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
 * REST controller for managing ComposantImpacte.
 */
@RestController
@RequestMapping("/api")
public class ComposantImpacteResource {

    private final Logger log = LoggerFactory.getLogger(ComposantImpacteResource.class);

    private static final String ENTITY_NAME = "composantImpacte";

    private final ComposantImpacteRepository composantImpacteRepository;

    public ComposantImpacteResource(ComposantImpacteRepository composantImpacteRepository) {
        this.composantImpacteRepository = composantImpacteRepository;
    }

    /**
     * POST  /composant-impactes : Create a new composantImpacte.
     *
     * @param composantImpacte the composantImpacte to create
     * @return the ResponseEntity with status 201 (Created) and with body the new composantImpacte, or with status 400 (Bad Request) if the composantImpacte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/composant-impactes")
    @Timed
    public ResponseEntity<ComposantImpacte> createComposantImpacte(@RequestBody ComposantImpacte composantImpacte) throws URISyntaxException {
        log.debug("REST request to save ComposantImpacte : {}", composantImpacte);
        if (composantImpacte.getId() != null) {
            throw new BadRequestAlertException("A new composantImpacte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComposantImpacte result = composantImpacteRepository.save(composantImpacte);
        return ResponseEntity.created(new URI("/api/composant-impactes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /composant-impactes : Updates an existing composantImpacte.
     *
     * @param composantImpacte the composantImpacte to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated composantImpacte,
     * or with status 400 (Bad Request) if the composantImpacte is not valid,
     * or with status 500 (Internal Server Error) if the composantImpacte couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/composant-impactes")
    @Timed
    public ResponseEntity<ComposantImpacte> updateComposantImpacte(@RequestBody ComposantImpacte composantImpacte) throws URISyntaxException {
        log.debug("REST request to update ComposantImpacte : {}", composantImpacte);
        if (composantImpacte.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComposantImpacte result = composantImpacteRepository.save(composantImpacte);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, composantImpacte.getId().toString()))
            .body(result);
    }

    /**
     * GET  /composant-impactes : get all the composantImpactes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of composantImpactes in body
     */
    @GetMapping("/composant-impactes")
    @Timed
    public List<ComposantImpacte> getAllComposantImpactes() {
        log.debug("REST request to get all ComposantImpactes");
        return composantImpacteRepository.findAll();
    }

    /**
     * GET  /composant-impactes/:id : get the "id" composantImpacte.
     *
     * @param id the id of the composantImpacte to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the composantImpacte, or with status 404 (Not Found)
     */
    @GetMapping("/composant-impactes/{id}")
    @Timed
    public ResponseEntity<ComposantImpacte> getComposantImpacte(@PathVariable Long id) {
        log.debug("REST request to get ComposantImpacte : {}", id);
        Optional<ComposantImpacte> composantImpacte = composantImpacteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(composantImpacte);
    }

    /**
     * DELETE  /composant-impactes/:id : delete the "id" composantImpacte.
     *
     * @param id the id of the composantImpacte to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/composant-impactes/{id}")
    @Timed
    public ResponseEntity<Void> deleteComposantImpacte(@PathVariable Long id) {
        log.debug("REST request to delete ComposantImpacte : {}", id);

        composantImpacteRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
