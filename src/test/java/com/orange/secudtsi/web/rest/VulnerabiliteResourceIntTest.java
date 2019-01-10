package com.orange.secudtsi.web.rest;

import com.orange.secudtsi.AuditReportApp;

import com.orange.secudtsi.domain.Vulnerabilite;
import com.orange.secudtsi.repository.VulnerabiliteRepository;
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
 * Test class for the VulnerabiliteResource REST controller.
 *
 * @see VulnerabiliteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuditReportApp.class)
public class VulnerabiliteResourceIntTest {

    private static final String DEFAULT_REF = "AAAAAAAAAA";
    private static final String UPDATED_REF = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIE = "BBBBBBBBBB";

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
    private VulnerabiliteRepository vulnerabiliteRepository;

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

    private MockMvc restVulnerabiliteMockMvc;

    private Vulnerabilite vulnerabilite;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VulnerabiliteResource vulnerabiliteResource = new VulnerabiliteResource(vulnerabiliteRepository);
        this.restVulnerabiliteMockMvc = MockMvcBuilders.standaloneSetup(vulnerabiliteResource)
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
    public static Vulnerabilite createEntity(EntityManager em) {
        Vulnerabilite vulnerabilite = new Vulnerabilite()
            .ref(DEFAULT_REF)
            .categorie(DEFAULT_CATEGORIE)
            .nom(DEFAULT_NOM)
            .description(DEFAULT_DESCRIPTION)
            .probabilite(DEFAULT_PROBABILITE)
            .impact(DEFAULT_IMPACT)
            .criticite(DEFAULT_CRITICITE)
            .cvss(DEFAULT_CVSS)
            .recommandation(DEFAULT_RECOMMANDATION);
        return vulnerabilite;
    }

    @Before
    public void initTest() {
        vulnerabilite = createEntity(em);
    }

    @Test
    @Transactional
    public void createVulnerabilite() throws Exception {
        int databaseSizeBeforeCreate = vulnerabiliteRepository.findAll().size();

        // Create the Vulnerabilite
        restVulnerabiliteMockMvc.perform(post("/api/vulnerabilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vulnerabilite)))
            .andExpect(status().isCreated());

        // Validate the Vulnerabilite in the database
        List<Vulnerabilite> vulnerabiliteList = vulnerabiliteRepository.findAll();
        assertThat(vulnerabiliteList).hasSize(databaseSizeBeforeCreate + 1);
        Vulnerabilite testVulnerabilite = vulnerabiliteList.get(vulnerabiliteList.size() - 1);
        assertThat(testVulnerabilite.getRef()).isEqualTo(DEFAULT_REF);
        assertThat(testVulnerabilite.getCategorie()).isEqualTo(DEFAULT_CATEGORIE);
        assertThat(testVulnerabilite.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testVulnerabilite.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVulnerabilite.getProbabilite()).isEqualTo(DEFAULT_PROBABILITE);
        assertThat(testVulnerabilite.getImpact()).isEqualTo(DEFAULT_IMPACT);
        assertThat(testVulnerabilite.getCriticite()).isEqualTo(DEFAULT_CRITICITE);
        assertThat(testVulnerabilite.getCvss()).isEqualTo(DEFAULT_CVSS);
        assertThat(testVulnerabilite.getRecommandation()).isEqualTo(DEFAULT_RECOMMANDATION);
    }

    @Test
    @Transactional
    public void createVulnerabiliteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vulnerabiliteRepository.findAll().size();

        // Create the Vulnerabilite with an existing ID
        vulnerabilite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVulnerabiliteMockMvc.perform(post("/api/vulnerabilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vulnerabilite)))
            .andExpect(status().isBadRequest());

        // Validate the Vulnerabilite in the database
        List<Vulnerabilite> vulnerabiliteList = vulnerabiliteRepository.findAll();
        assertThat(vulnerabiliteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVulnerabilites() throws Exception {
        // Initialize the database
        vulnerabiliteRepository.saveAndFlush(vulnerabilite);

        // Get all the vulnerabiliteList
        restVulnerabiliteMockMvc.perform(get("/api/vulnerabilites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vulnerabilite.getId().intValue())))
            .andExpect(jsonPath("$.[*].ref").value(hasItem(DEFAULT_REF.toString())))
            .andExpect(jsonPath("$.[*].categorie").value(hasItem(DEFAULT_CATEGORIE.toString())))
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
    public void getVulnerabilite() throws Exception {
        // Initialize the database
        vulnerabiliteRepository.saveAndFlush(vulnerabilite);

        // Get the vulnerabilite
        restVulnerabiliteMockMvc.perform(get("/api/vulnerabilites/{id}", vulnerabilite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vulnerabilite.getId().intValue()))
            .andExpect(jsonPath("$.ref").value(DEFAULT_REF.toString()))
            .andExpect(jsonPath("$.categorie").value(DEFAULT_CATEGORIE.toString()))
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
    public void getNonExistingVulnerabilite() throws Exception {
        // Get the vulnerabilite
        restVulnerabiliteMockMvc.perform(get("/api/vulnerabilites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVulnerabilite() throws Exception {
        // Initialize the database
        vulnerabiliteRepository.saveAndFlush(vulnerabilite);

        int databaseSizeBeforeUpdate = vulnerabiliteRepository.findAll().size();

        // Update the vulnerabilite
        Vulnerabilite updatedVulnerabilite = vulnerabiliteRepository.findById(vulnerabilite.getId()).get();
        // Disconnect from session so that the updates on updatedVulnerabilite are not directly saved in db
        em.detach(updatedVulnerabilite);
        updatedVulnerabilite
            .ref(UPDATED_REF)
            .categorie(UPDATED_CATEGORIE)
            .nom(UPDATED_NOM)
            .description(UPDATED_DESCRIPTION)
            .probabilite(UPDATED_PROBABILITE)
            .impact(UPDATED_IMPACT)
            .criticite(UPDATED_CRITICITE)
            .cvss(UPDATED_CVSS)
            .recommandation(UPDATED_RECOMMANDATION);

        restVulnerabiliteMockMvc.perform(put("/api/vulnerabilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVulnerabilite)))
            .andExpect(status().isOk());

        // Validate the Vulnerabilite in the database
        List<Vulnerabilite> vulnerabiliteList = vulnerabiliteRepository.findAll();
        assertThat(vulnerabiliteList).hasSize(databaseSizeBeforeUpdate);
        Vulnerabilite testVulnerabilite = vulnerabiliteList.get(vulnerabiliteList.size() - 1);
        assertThat(testVulnerabilite.getRef()).isEqualTo(UPDATED_REF);
        assertThat(testVulnerabilite.getCategorie()).isEqualTo(UPDATED_CATEGORIE);
        assertThat(testVulnerabilite.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testVulnerabilite.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVulnerabilite.getProbabilite()).isEqualTo(UPDATED_PROBABILITE);
        assertThat(testVulnerabilite.getImpact()).isEqualTo(UPDATED_IMPACT);
        assertThat(testVulnerabilite.getCriticite()).isEqualTo(UPDATED_CRITICITE);
        assertThat(testVulnerabilite.getCvss()).isEqualTo(UPDATED_CVSS);
        assertThat(testVulnerabilite.getRecommandation()).isEqualTo(UPDATED_RECOMMANDATION);
    }

    @Test
    @Transactional
    public void updateNonExistingVulnerabilite() throws Exception {
        int databaseSizeBeforeUpdate = vulnerabiliteRepository.findAll().size();

        // Create the Vulnerabilite

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVulnerabiliteMockMvc.perform(put("/api/vulnerabilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vulnerabilite)))
            .andExpect(status().isBadRequest());

        // Validate the Vulnerabilite in the database
        List<Vulnerabilite> vulnerabiliteList = vulnerabiliteRepository.findAll();
        assertThat(vulnerabiliteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVulnerabilite() throws Exception {
        // Initialize the database
        vulnerabiliteRepository.saveAndFlush(vulnerabilite);

        int databaseSizeBeforeDelete = vulnerabiliteRepository.findAll().size();

        // Get the vulnerabilite
        restVulnerabiliteMockMvc.perform(delete("/api/vulnerabilites/{id}", vulnerabilite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vulnerabilite> vulnerabiliteList = vulnerabiliteRepository.findAll();
        assertThat(vulnerabiliteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vulnerabilite.class);
        Vulnerabilite vulnerabilite1 = new Vulnerabilite();
        vulnerabilite1.setId(1L);
        Vulnerabilite vulnerabilite2 = new Vulnerabilite();
        vulnerabilite2.setId(vulnerabilite1.getId());
        assertThat(vulnerabilite1).isEqualTo(vulnerabilite2);
        vulnerabilite2.setId(2L);
        assertThat(vulnerabilite1).isNotEqualTo(vulnerabilite2);
        vulnerabilite1.setId(null);
        assertThat(vulnerabilite1).isNotEqualTo(vulnerabilite2);
    }
}
