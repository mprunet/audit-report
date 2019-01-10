package com.orange.secudtsi.web.rest;

import com.orange.secudtsi.AuditReportApp;

import com.orange.secudtsi.domain.ComposantImpacte;
import com.orange.secudtsi.repository.ComposantImpacteRepository;
import com.orange.secudtsi.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.orange.secudtsi.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ComposantImpacteResource REST controller.
 *
 * @see ComposantImpacteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuditReportApp.class)
public class ComposantImpacteResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    @Autowired
    private ComposantImpacteRepository composantImpacteRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restComposantImpacteMockMvc;

    private ComposantImpacte composantImpacte;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComposantImpacteResource composantImpacteResource = new ComposantImpacteResource(composantImpacteRepository);
        this.restComposantImpacteMockMvc = MockMvcBuilders.standaloneSetup(composantImpacteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ComposantImpacte createEntity(EntityManager em) {
        ComposantImpacte composantImpacte = new ComposantImpacte()
            .nom(DEFAULT_NOM);
        return composantImpacte;
    }

    @Before
    public void initTest() {
        composantImpacte = createEntity(em);
    }

    @Test
    @Transactional
    public void createComposantImpacte() throws Exception {
        int databaseSizeBeforeCreate = composantImpacteRepository.findAll().size();

        // Create the ComposantImpacte
        restComposantImpacteMockMvc.perform(post("/api/composant-impactes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantImpacte)))
            .andExpect(status().isCreated());

        // Validate the ComposantImpacte in the database
        List<ComposantImpacte> composantImpacteList = composantImpacteRepository.findAll();
        assertThat(composantImpacteList).hasSize(databaseSizeBeforeCreate + 1);
        ComposantImpacte testComposantImpacte = composantImpacteList.get(composantImpacteList.size() - 1);
        assertThat(testComposantImpacte.getNom()).isEqualTo(DEFAULT_NOM);
    }

    @Test
    @Transactional
    public void createComposantImpacteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = composantImpacteRepository.findAll().size();

        // Create the ComposantImpacte with an existing ID
        composantImpacte.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComposantImpacteMockMvc.perform(post("/api/composant-impactes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantImpacte)))
            .andExpect(status().isBadRequest());

        // Validate the ComposantImpacte in the database
        List<ComposantImpacte> composantImpacteList = composantImpacteRepository.findAll();
        assertThat(composantImpacteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllComposantImpactes() throws Exception {
        // Initialize the database
        composantImpacteRepository.saveAndFlush(composantImpacte);

        // Get all the composantImpacteList
        restComposantImpacteMockMvc.perform(get("/api/composant-impactes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(composantImpacte.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }
    
    @Test
    @Transactional
    public void getComposantImpacte() throws Exception {
        // Initialize the database
        composantImpacteRepository.saveAndFlush(composantImpacte);

        // Get the composantImpacte
        restComposantImpacteMockMvc.perform(get("/api/composant-impactes/{id}", composantImpacte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(composantImpacte.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComposantImpacte() throws Exception {
        // Get the composantImpacte
        restComposantImpacteMockMvc.perform(get("/api/composant-impactes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComposantImpacte() throws Exception {
        // Initialize the database
        composantImpacteRepository.saveAndFlush(composantImpacte);

        int databaseSizeBeforeUpdate = composantImpacteRepository.findAll().size();

        // Update the composantImpacte
        ComposantImpacte updatedComposantImpacte = composantImpacteRepository.findById(composantImpacte.getId()).get();
        // Disconnect from session so that the updates on updatedComposantImpacte are not directly saved in db
        em.detach(updatedComposantImpacte);
        updatedComposantImpacte
            .nom(UPDATED_NOM);

        restComposantImpacteMockMvc.perform(put("/api/composant-impactes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedComposantImpacte)))
            .andExpect(status().isOk());

        // Validate the ComposantImpacte in the database
        List<ComposantImpacte> composantImpacteList = composantImpacteRepository.findAll();
        assertThat(composantImpacteList).hasSize(databaseSizeBeforeUpdate);
        ComposantImpacte testComposantImpacte = composantImpacteList.get(composantImpacteList.size() - 1);
        assertThat(testComposantImpacte.getNom()).isEqualTo(UPDATED_NOM);
    }

    @Test
    @Transactional
    public void updateNonExistingComposantImpacte() throws Exception {
        int databaseSizeBeforeUpdate = composantImpacteRepository.findAll().size();

        // Create the ComposantImpacte

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComposantImpacteMockMvc.perform(put("/api/composant-impactes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(composantImpacte)))
            .andExpect(status().isBadRequest());

        // Validate the ComposantImpacte in the database
        List<ComposantImpacte> composantImpacteList = composantImpacteRepository.findAll();
        assertThat(composantImpacteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComposantImpacte() throws Exception {
        // Initialize the database
        composantImpacteRepository.saveAndFlush(composantImpacte);

        int databaseSizeBeforeDelete = composantImpacteRepository.findAll().size();

        // Get the composantImpacte
        restComposantImpacteMockMvc.perform(delete("/api/composant-impactes/{id}", composantImpacte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ComposantImpacte> composantImpacteList = composantImpacteRepository.findAll();
        assertThat(composantImpacteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComposantImpacte.class);
        ComposantImpacte composantImpacte1 = new ComposantImpacte();
        composantImpacte1.setId(1L);
        ComposantImpacte composantImpacte2 = new ComposantImpacte();
        composantImpacte2.setId(composantImpacte1.getId());
        assertThat(composantImpacte1).isEqualTo(composantImpacte2);
        composantImpacte2.setId(2L);
        assertThat(composantImpacte1).isNotEqualTo(composantImpacte2);
        composantImpacte1.setId(null);
        assertThat(composantImpacte1).isNotEqualTo(composantImpacte2);
    }
}
