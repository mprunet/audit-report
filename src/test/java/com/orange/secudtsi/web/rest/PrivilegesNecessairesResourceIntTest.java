package com.orange.secudtsi.web.rest;

import com.orange.secudtsi.AuditReportApp;

import com.orange.secudtsi.domain.PrivilegesNecessaires;
import com.orange.secudtsi.repository.PrivilegesNecessairesRepository;
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
 * Test class for the PrivilegesNecessairesResource REST controller.
 *
 * @see PrivilegesNecessairesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuditReportApp.class)
public class PrivilegesNecessairesResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Float DEFAULT_POIDS = 1F;
    private static final Float UPDATED_POIDS = 2F;

    @Autowired
    private PrivilegesNecessairesRepository privilegesNecessairesRepository;

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

    private MockMvc restPrivilegesNecessairesMockMvc;

    private PrivilegesNecessaires privilegesNecessaires;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PrivilegesNecessairesResource privilegesNecessairesResource = new PrivilegesNecessairesResource(privilegesNecessairesRepository);
        this.restPrivilegesNecessairesMockMvc = MockMvcBuilders.standaloneSetup(privilegesNecessairesResource)
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
    public static PrivilegesNecessaires createEntity(EntityManager em) {
        PrivilegesNecessaires privilegesNecessaires = new PrivilegesNecessaires()
            .nom(DEFAULT_NOM)
            .poids(DEFAULT_POIDS);
        return privilegesNecessaires;
    }

    @Before
    public void initTest() {
        privilegesNecessaires = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrivilegesNecessaires() throws Exception {
        int databaseSizeBeforeCreate = privilegesNecessairesRepository.findAll().size();

        // Create the PrivilegesNecessaires
        restPrivilegesNecessairesMockMvc.perform(post("/api/privileges-necessaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(privilegesNecessaires)))
            .andExpect(status().isCreated());

        // Validate the PrivilegesNecessaires in the database
        List<PrivilegesNecessaires> privilegesNecessairesList = privilegesNecessairesRepository.findAll();
        assertThat(privilegesNecessairesList).hasSize(databaseSizeBeforeCreate + 1);
        PrivilegesNecessaires testPrivilegesNecessaires = privilegesNecessairesList.get(privilegesNecessairesList.size() - 1);
        assertThat(testPrivilegesNecessaires.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testPrivilegesNecessaires.getPoids()).isEqualTo(DEFAULT_POIDS);
    }

    @Test
    @Transactional
    public void createPrivilegesNecessairesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = privilegesNecessairesRepository.findAll().size();

        // Create the PrivilegesNecessaires with an existing ID
        privilegesNecessaires.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrivilegesNecessairesMockMvc.perform(post("/api/privileges-necessaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(privilegesNecessaires)))
            .andExpect(status().isBadRequest());

        // Validate the PrivilegesNecessaires in the database
        List<PrivilegesNecessaires> privilegesNecessairesList = privilegesNecessairesRepository.findAll();
        assertThat(privilegesNecessairesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPrivilegesNecessaires() throws Exception {
        // Initialize the database
        privilegesNecessairesRepository.saveAndFlush(privilegesNecessaires);

        // Get all the privilegesNecessairesList
        restPrivilegesNecessairesMockMvc.perform(get("/api/privileges-necessaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(privilegesNecessaires.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getPrivilegesNecessaires() throws Exception {
        // Initialize the database
        privilegesNecessairesRepository.saveAndFlush(privilegesNecessaires);

        // Get the privilegesNecessaires
        restPrivilegesNecessairesMockMvc.perform(get("/api/privileges-necessaires/{id}", privilegesNecessaires.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(privilegesNecessaires.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPrivilegesNecessaires() throws Exception {
        // Get the privilegesNecessaires
        restPrivilegesNecessairesMockMvc.perform(get("/api/privileges-necessaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrivilegesNecessaires() throws Exception {
        // Initialize the database
        privilegesNecessairesRepository.saveAndFlush(privilegesNecessaires);

        int databaseSizeBeforeUpdate = privilegesNecessairesRepository.findAll().size();

        // Update the privilegesNecessaires
        PrivilegesNecessaires updatedPrivilegesNecessaires = privilegesNecessairesRepository.findById(privilegesNecessaires.getId()).get();
        // Disconnect from session so that the updates on updatedPrivilegesNecessaires are not directly saved in db
        em.detach(updatedPrivilegesNecessaires);
        updatedPrivilegesNecessaires
            .nom(UPDATED_NOM)
            .poids(UPDATED_POIDS);

        restPrivilegesNecessairesMockMvc.perform(put("/api/privileges-necessaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPrivilegesNecessaires)))
            .andExpect(status().isOk());

        // Validate the PrivilegesNecessaires in the database
        List<PrivilegesNecessaires> privilegesNecessairesList = privilegesNecessairesRepository.findAll();
        assertThat(privilegesNecessairesList).hasSize(databaseSizeBeforeUpdate);
        PrivilegesNecessaires testPrivilegesNecessaires = privilegesNecessairesList.get(privilegesNecessairesList.size() - 1);
        assertThat(testPrivilegesNecessaires.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testPrivilegesNecessaires.getPoids()).isEqualTo(UPDATED_POIDS);
    }

    @Test
    @Transactional
    public void updateNonExistingPrivilegesNecessaires() throws Exception {
        int databaseSizeBeforeUpdate = privilegesNecessairesRepository.findAll().size();

        // Create the PrivilegesNecessaires

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrivilegesNecessairesMockMvc.perform(put("/api/privileges-necessaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(privilegesNecessaires)))
            .andExpect(status().isBadRequest());

        // Validate the PrivilegesNecessaires in the database
        List<PrivilegesNecessaires> privilegesNecessairesList = privilegesNecessairesRepository.findAll();
        assertThat(privilegesNecessairesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrivilegesNecessaires() throws Exception {
        // Initialize the database
        privilegesNecessairesRepository.saveAndFlush(privilegesNecessaires);

        int databaseSizeBeforeDelete = privilegesNecessairesRepository.findAll().size();

        // Get the privilegesNecessaires
        restPrivilegesNecessairesMockMvc.perform(delete("/api/privileges-necessaires/{id}", privilegesNecessaires.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PrivilegesNecessaires> privilegesNecessairesList = privilegesNecessairesRepository.findAll();
        assertThat(privilegesNecessairesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrivilegesNecessaires.class);
        PrivilegesNecessaires privilegesNecessaires1 = new PrivilegesNecessaires();
        privilegesNecessaires1.setId(1L);
        PrivilegesNecessaires privilegesNecessaires2 = new PrivilegesNecessaires();
        privilegesNecessaires2.setId(privilegesNecessaires1.getId());
        assertThat(privilegesNecessaires1).isEqualTo(privilegesNecessaires2);
        privilegesNecessaires2.setId(2L);
        assertThat(privilegesNecessaires1).isNotEqualTo(privilegesNecessaires2);
        privilegesNecessaires1.setId(null);
        assertThat(privilegesNecessaires1).isNotEqualTo(privilegesNecessaires2);
    }
}
