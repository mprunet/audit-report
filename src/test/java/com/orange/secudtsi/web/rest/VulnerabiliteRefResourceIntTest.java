package com.orange.secudtsi.web.rest;

import com.orange.secudtsi.AuditReportApp;

import com.orange.secudtsi.domain.VulnerabiliteRef;
import com.orange.secudtsi.repository.VulnerabiliteRefRepository;
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
 * Test class for the VulnerabiliteRefResource REST controller.
 *
 * @see VulnerabiliteRefResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuditReportApp.class)
public class VulnerabiliteRefResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Float DEFAULT_PROBABILITE = 1F;
    private static final Float UPDATED_PROBABILITE = 2F;

    private static final Float DEFAULT_IMPACT = 1F;
    private static final Float UPDATED_IMPACT = 2F;

    private static final String DEFAULT_CRITICITE = "AAAAAAAAAA";
    private static final String UPDATED_CRITICITE = "BBBBBBBBBB";

    private static final Float DEFAULT_CVSS = 1F;
    private static final Float UPDATED_CVSS = 2F;

    private static final String DEFAULT_RECOMMANDATION = "AAAAAAAAAA";
    private static final String UPDATED_RECOMMANDATION = "BBBBBBBBBB";

    @Autowired
    private VulnerabiliteRefRepository vulnerabiliteRefRepository;

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

    private MockMvc restVulnerabiliteRefMockMvc;

    private VulnerabiliteRef vulnerabiliteRef;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VulnerabiliteRefResource vulnerabiliteRefResource = new VulnerabiliteRefResource(vulnerabiliteRefRepository);
        this.restVulnerabiliteRefMockMvc = MockMvcBuilders.standaloneSetup(vulnerabiliteRefResource)
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
    public static VulnerabiliteRef createEntity(EntityManager em) {
        VulnerabiliteRef vulnerabiliteRef = new VulnerabiliteRef()
            .nom(DEFAULT_NOM)
            .description(DEFAULT_DESCRIPTION)
            .probabilite(DEFAULT_PROBABILITE)
            .impact(DEFAULT_IMPACT)
            .criticite(DEFAULT_CRITICITE)
            .cvss(DEFAULT_CVSS)
            .recommandation(DEFAULT_RECOMMANDATION);
        return vulnerabiliteRef;
    }

    @Before
    public void initTest() {
        vulnerabiliteRef = createEntity(em);
    }

    @Test
    @Transactional
    public void createVulnerabiliteRef() throws Exception {
        int databaseSizeBeforeCreate = vulnerabiliteRefRepository.findAll().size();

        // Create the VulnerabiliteRef
        restVulnerabiliteRefMockMvc.perform(post("/api/vulnerabilite-refs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vulnerabiliteRef)))
            .andExpect(status().isCreated());

        // Validate the VulnerabiliteRef in the database
        List<VulnerabiliteRef> vulnerabiliteRefList = vulnerabiliteRefRepository.findAll();
        assertThat(vulnerabiliteRefList).hasSize(databaseSizeBeforeCreate + 1);
        VulnerabiliteRef testVulnerabiliteRef = vulnerabiliteRefList.get(vulnerabiliteRefList.size() - 1);
        assertThat(testVulnerabiliteRef.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testVulnerabiliteRef.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVulnerabiliteRef.getProbabilite()).isEqualTo(DEFAULT_PROBABILITE);
        assertThat(testVulnerabiliteRef.getImpact()).isEqualTo(DEFAULT_IMPACT);
        assertThat(testVulnerabiliteRef.getCriticite()).isEqualTo(DEFAULT_CRITICITE);
        assertThat(testVulnerabiliteRef.getCvss()).isEqualTo(DEFAULT_CVSS);
        assertThat(testVulnerabiliteRef.getRecommandation()).isEqualTo(DEFAULT_RECOMMANDATION);
    }

    @Test
    @Transactional
    public void createVulnerabiliteRefWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vulnerabiliteRefRepository.findAll().size();

        // Create the VulnerabiliteRef with an existing ID
        vulnerabiliteRef.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVulnerabiliteRefMockMvc.perform(post("/api/vulnerabilite-refs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vulnerabiliteRef)))
            .andExpect(status().isBadRequest());

        // Validate the VulnerabiliteRef in the database
        List<VulnerabiliteRef> vulnerabiliteRefList = vulnerabiliteRefRepository.findAll();
        assertThat(vulnerabiliteRefList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVulnerabiliteRefs() throws Exception {
        // Initialize the database
        vulnerabiliteRefRepository.saveAndFlush(vulnerabiliteRef);

        // Get all the vulnerabiliteRefList
        restVulnerabiliteRefMockMvc.perform(get("/api/vulnerabilite-refs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vulnerabiliteRef.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].probabilite").value(hasItem(DEFAULT_PROBABILITE.doubleValue())))
            .andExpect(jsonPath("$.[*].impact").value(hasItem(DEFAULT_IMPACT.doubleValue())))
            .andExpect(jsonPath("$.[*].criticite").value(hasItem(DEFAULT_CRITICITE.toString())))
            .andExpect(jsonPath("$.[*].cvss").value(hasItem(DEFAULT_CVSS.doubleValue())))
            .andExpect(jsonPath("$.[*].recommandation").value(hasItem(DEFAULT_RECOMMANDATION.toString())));
    }
    
    @Test
    @Transactional
    public void getVulnerabiliteRef() throws Exception {
        // Initialize the database
        vulnerabiliteRefRepository.saveAndFlush(vulnerabiliteRef);

        // Get the vulnerabiliteRef
        restVulnerabiliteRefMockMvc.perform(get("/api/vulnerabilite-refs/{id}", vulnerabiliteRef.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vulnerabiliteRef.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.probabilite").value(DEFAULT_PROBABILITE.doubleValue()))
            .andExpect(jsonPath("$.impact").value(DEFAULT_IMPACT.doubleValue()))
            .andExpect(jsonPath("$.criticite").value(DEFAULT_CRITICITE.toString()))
            .andExpect(jsonPath("$.cvss").value(DEFAULT_CVSS.doubleValue()))
            .andExpect(jsonPath("$.recommandation").value(DEFAULT_RECOMMANDATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVulnerabiliteRef() throws Exception {
        // Get the vulnerabiliteRef
        restVulnerabiliteRefMockMvc.perform(get("/api/vulnerabilite-refs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVulnerabiliteRef() throws Exception {
        // Initialize the database
        vulnerabiliteRefRepository.saveAndFlush(vulnerabiliteRef);

        int databaseSizeBeforeUpdate = vulnerabiliteRefRepository.findAll().size();

        // Update the vulnerabiliteRef
        VulnerabiliteRef updatedVulnerabiliteRef = vulnerabiliteRefRepository.findById(vulnerabiliteRef.getId()).get();
        // Disconnect from session so that the updates on updatedVulnerabiliteRef are not directly saved in db
        em.detach(updatedVulnerabiliteRef);
        updatedVulnerabiliteRef
            .nom(UPDATED_NOM)
            .description(UPDATED_DESCRIPTION)
            .probabilite(UPDATED_PROBABILITE)
            .impact(UPDATED_IMPACT)
            .criticite(UPDATED_CRITICITE)
            .cvss(UPDATED_CVSS)
            .recommandation(UPDATED_RECOMMANDATION);

        restVulnerabiliteRefMockMvc.perform(put("/api/vulnerabilite-refs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVulnerabiliteRef)))
            .andExpect(status().isOk());

        // Validate the VulnerabiliteRef in the database
        List<VulnerabiliteRef> vulnerabiliteRefList = vulnerabiliteRefRepository.findAll();
        assertThat(vulnerabiliteRefList).hasSize(databaseSizeBeforeUpdate);
        VulnerabiliteRef testVulnerabiliteRef = vulnerabiliteRefList.get(vulnerabiliteRefList.size() - 1);
        assertThat(testVulnerabiliteRef.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testVulnerabiliteRef.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVulnerabiliteRef.getProbabilite()).isEqualTo(UPDATED_PROBABILITE);
        assertThat(testVulnerabiliteRef.getImpact()).isEqualTo(UPDATED_IMPACT);
        assertThat(testVulnerabiliteRef.getCriticite()).isEqualTo(UPDATED_CRITICITE);
        assertThat(testVulnerabiliteRef.getCvss()).isEqualTo(UPDATED_CVSS);
        assertThat(testVulnerabiliteRef.getRecommandation()).isEqualTo(UPDATED_RECOMMANDATION);
    }

    @Test
    @Transactional
    public void updateNonExistingVulnerabiliteRef() throws Exception {
        int databaseSizeBeforeUpdate = vulnerabiliteRefRepository.findAll().size();

        // Create the VulnerabiliteRef

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVulnerabiliteRefMockMvc.perform(put("/api/vulnerabilite-refs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vulnerabiliteRef)))
            .andExpect(status().isBadRequest());

        // Validate the VulnerabiliteRef in the database
        List<VulnerabiliteRef> vulnerabiliteRefList = vulnerabiliteRefRepository.findAll();
        assertThat(vulnerabiliteRefList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVulnerabiliteRef() throws Exception {
        // Initialize the database
        vulnerabiliteRefRepository.saveAndFlush(vulnerabiliteRef);

        int databaseSizeBeforeDelete = vulnerabiliteRefRepository.findAll().size();

        // Get the vulnerabiliteRef
        restVulnerabiliteRefMockMvc.perform(delete("/api/vulnerabilite-refs/{id}", vulnerabiliteRef.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VulnerabiliteRef> vulnerabiliteRefList = vulnerabiliteRefRepository.findAll();
        assertThat(vulnerabiliteRefList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VulnerabiliteRef.class);
        VulnerabiliteRef vulnerabiliteRef1 = new VulnerabiliteRef();
        vulnerabiliteRef1.setId(1L);
        VulnerabiliteRef vulnerabiliteRef2 = new VulnerabiliteRef();
        vulnerabiliteRef2.setId(vulnerabiliteRef1.getId());
        assertThat(vulnerabiliteRef1).isEqualTo(vulnerabiliteRef2);
        vulnerabiliteRef2.setId(2L);
        assertThat(vulnerabiliteRef1).isNotEqualTo(vulnerabiliteRef2);
        vulnerabiliteRef1.setId(null);
        assertThat(vulnerabiliteRef1).isNotEqualTo(vulnerabiliteRef2);
    }
}
