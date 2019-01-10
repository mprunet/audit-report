package com.orange.secudtsi.web.rest;

import com.orange.secudtsi.AuditReportApp;

import com.orange.secudtsi.domain.Projet;
import com.orange.secudtsi.repository.ProjetRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.orange.secudtsi.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjetResource REST controller.
 *
 * @see ProjetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuditReportApp.class)
public class ProjetResourceIntTest {

    private static final String DEFAULT_BASICAT = "AAAAAAAAAA";
    private static final String UPDATED_BASICAT = "BBBBBBBBBB";

    private static final String DEFAULT_ORANGE_CARTO = "AAAAAAAAAA";
    private static final String UPDATED_ORANGE_CARTO = "BBBBBBBBBB";

    private static final String DEFAULT_PERISCOPE = "AAAAAAAAAA";
    private static final String UPDATED_PERISCOPE = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_MOE = "AAAAAAAAAA";
    private static final String UPDATED_MOE = "BBBBBBBBBB";

    private static final String DEFAULT_CDP = "AAAAAAAAAA";
    private static final String UPDATED_CDP = "BBBBBBBBBB";

    private static final String DEFAULT_RSSI = "AAAAAAAAAA";
    private static final String UPDATED_RSSI = "BBBBBBBBBB";

    private static final String DEFAULT_CSSI = "AAAAAAAAAA";
    private static final String UPDATED_CSSI = "BBBBBBBBBB";

    private static final String DEFAULT_DS = "AAAAAAAAAA";
    private static final String UPDATED_DS = "BBBBBBBBBB";

    private static final String DEFAULT_DP = "AAAAAAAAAA";
    private static final String UPDATED_DP = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_AUDIT = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_AUDIT = "BBBBBBBBBB";

    private static final String DEFAULT_ENTREPRISE_AUDITEUR = "AAAAAAAAAA";
    private static final String UPDATED_ENTREPRISE_AUDITEUR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_RESTITUTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RESTITUTION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ProjetRepository projetRepository;

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

    private MockMvc restProjetMockMvc;

    private Projet projet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProjetResource projetResource = new ProjetResource(projetRepository);
        this.restProjetMockMvc = MockMvcBuilders.standaloneSetup(projetResource)
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
    public static Projet createEntity(EntityManager em) {
        Projet projet = new Projet()
            .basicat(DEFAULT_BASICAT)
            .orangeCarto(DEFAULT_ORANGE_CARTO)
            .periscope(DEFAULT_PERISCOPE)
            .version(DEFAULT_VERSION)
            .nom(DEFAULT_NOM)
            .moe(DEFAULT_MOE)
            .cdp(DEFAULT_CDP)
            .rssi(DEFAULT_RSSI)
            .cssi(DEFAULT_CSSI)
            .ds(DEFAULT_DS)
            .dp(DEFAULT_DP)
            .typeAudit(DEFAULT_TYPE_AUDIT)
            .entrepriseAuditeur(DEFAULT_ENTREPRISE_AUDITEUR)
            .dateRestitution(DEFAULT_DATE_RESTITUTION);
        return projet;
    }

    @Before
    public void initTest() {
        projet = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjet() throws Exception {
        int databaseSizeBeforeCreate = projetRepository.findAll().size();

        // Create the Projet
        restProjetMockMvc.perform(post("/api/projets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projet)))
            .andExpect(status().isCreated());

        // Validate the Projet in the database
        List<Projet> projetList = projetRepository.findAll();
        assertThat(projetList).hasSize(databaseSizeBeforeCreate + 1);
        Projet testProjet = projetList.get(projetList.size() - 1);
        assertThat(testProjet.getBasicat()).isEqualTo(DEFAULT_BASICAT);
        assertThat(testProjet.getOrangeCarto()).isEqualTo(DEFAULT_ORANGE_CARTO);
        assertThat(testProjet.getPeriscope()).isEqualTo(DEFAULT_PERISCOPE);
        assertThat(testProjet.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testProjet.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testProjet.getMoe()).isEqualTo(DEFAULT_MOE);
        assertThat(testProjet.getCdp()).isEqualTo(DEFAULT_CDP);
        assertThat(testProjet.getRssi()).isEqualTo(DEFAULT_RSSI);
        assertThat(testProjet.getCssi()).isEqualTo(DEFAULT_CSSI);
        assertThat(testProjet.getDs()).isEqualTo(DEFAULT_DS);
        assertThat(testProjet.getDp()).isEqualTo(DEFAULT_DP);
        assertThat(testProjet.getTypeAudit()).isEqualTo(DEFAULT_TYPE_AUDIT);
        assertThat(testProjet.getEntrepriseAuditeur()).isEqualTo(DEFAULT_ENTREPRISE_AUDITEUR);
        assertThat(testProjet.getDateRestitution()).isEqualTo(DEFAULT_DATE_RESTITUTION);
    }

    @Test
    @Transactional
    public void createProjetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projetRepository.findAll().size();

        // Create the Projet with an existing ID
        projet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjetMockMvc.perform(post("/api/projets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projet)))
            .andExpect(status().isBadRequest());

        // Validate the Projet in the database
        List<Projet> projetList = projetRepository.findAll();
        assertThat(projetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProjets() throws Exception {
        // Initialize the database
        projetRepository.saveAndFlush(projet);

        // Get all the projetList
        restProjetMockMvc.perform(get("/api/projets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projet.getId().intValue())))
            .andExpect(jsonPath("$.[*].basicat").value(hasItem(DEFAULT_BASICAT.toString())))
            .andExpect(jsonPath("$.[*].orangeCarto").value(hasItem(DEFAULT_ORANGE_CARTO.toString())))
            .andExpect(jsonPath("$.[*].periscope").value(hasItem(DEFAULT_PERISCOPE.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].moe").value(hasItem(DEFAULT_MOE.toString())))
            .andExpect(jsonPath("$.[*].cdp").value(hasItem(DEFAULT_CDP.toString())))
            .andExpect(jsonPath("$.[*].rssi").value(hasItem(DEFAULT_RSSI.toString())))
            .andExpect(jsonPath("$.[*].cssi").value(hasItem(DEFAULT_CSSI.toString())))
            .andExpect(jsonPath("$.[*].ds").value(hasItem(DEFAULT_DS.toString())))
            .andExpect(jsonPath("$.[*].dp").value(hasItem(DEFAULT_DP.toString())))
            .andExpect(jsonPath("$.[*].typeAudit").value(hasItem(DEFAULT_TYPE_AUDIT.toString())))
            .andExpect(jsonPath("$.[*].entrepriseAuditeur").value(hasItem(DEFAULT_ENTREPRISE_AUDITEUR.toString())))
            .andExpect(jsonPath("$.[*].dateRestitution").value(hasItem(DEFAULT_DATE_RESTITUTION.toString())));
    }
    
    @Test
    @Transactional
    public void getProjet() throws Exception {
        // Initialize the database
        projetRepository.saveAndFlush(projet);

        // Get the projet
        restProjetMockMvc.perform(get("/api/projets/{id}", projet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projet.getId().intValue()))
            .andExpect(jsonPath("$.basicat").value(DEFAULT_BASICAT.toString()))
            .andExpect(jsonPath("$.orangeCarto").value(DEFAULT_ORANGE_CARTO.toString()))
            .andExpect(jsonPath("$.periscope").value(DEFAULT_PERISCOPE.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.moe").value(DEFAULT_MOE.toString()))
            .andExpect(jsonPath("$.cdp").value(DEFAULT_CDP.toString()))
            .andExpect(jsonPath("$.rssi").value(DEFAULT_RSSI.toString()))
            .andExpect(jsonPath("$.cssi").value(DEFAULT_CSSI.toString()))
            .andExpect(jsonPath("$.ds").value(DEFAULT_DS.toString()))
            .andExpect(jsonPath("$.dp").value(DEFAULT_DP.toString()))
            .andExpect(jsonPath("$.typeAudit").value(DEFAULT_TYPE_AUDIT.toString()))
            .andExpect(jsonPath("$.entrepriseAuditeur").value(DEFAULT_ENTREPRISE_AUDITEUR.toString()))
            .andExpect(jsonPath("$.dateRestitution").value(DEFAULT_DATE_RESTITUTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProjet() throws Exception {
        // Get the projet
        restProjetMockMvc.perform(get("/api/projets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjet() throws Exception {
        // Initialize the database
        projetRepository.saveAndFlush(projet);

        int databaseSizeBeforeUpdate = projetRepository.findAll().size();

        // Update the projet
        Projet updatedProjet = projetRepository.findById(projet.getId()).get();
        // Disconnect from session so that the updates on updatedProjet are not directly saved in db
        em.detach(updatedProjet);
        updatedProjet
            .basicat(UPDATED_BASICAT)
            .orangeCarto(UPDATED_ORANGE_CARTO)
            .periscope(UPDATED_PERISCOPE)
            .version(UPDATED_VERSION)
            .nom(UPDATED_NOM)
            .moe(UPDATED_MOE)
            .cdp(UPDATED_CDP)
            .rssi(UPDATED_RSSI)
            .cssi(UPDATED_CSSI)
            .ds(UPDATED_DS)
            .dp(UPDATED_DP)
            .typeAudit(UPDATED_TYPE_AUDIT)
            .entrepriseAuditeur(UPDATED_ENTREPRISE_AUDITEUR)
            .dateRestitution(UPDATED_DATE_RESTITUTION);

        restProjetMockMvc.perform(put("/api/projets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjet)))
            .andExpect(status().isOk());

        // Validate the Projet in the database
        List<Projet> projetList = projetRepository.findAll();
        assertThat(projetList).hasSize(databaseSizeBeforeUpdate);
        Projet testProjet = projetList.get(projetList.size() - 1);
        assertThat(testProjet.getBasicat()).isEqualTo(UPDATED_BASICAT);
        assertThat(testProjet.getOrangeCarto()).isEqualTo(UPDATED_ORANGE_CARTO);
        assertThat(testProjet.getPeriscope()).isEqualTo(UPDATED_PERISCOPE);
        assertThat(testProjet.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProjet.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testProjet.getMoe()).isEqualTo(UPDATED_MOE);
        assertThat(testProjet.getCdp()).isEqualTo(UPDATED_CDP);
        assertThat(testProjet.getRssi()).isEqualTo(UPDATED_RSSI);
        assertThat(testProjet.getCssi()).isEqualTo(UPDATED_CSSI);
        assertThat(testProjet.getDs()).isEqualTo(UPDATED_DS);
        assertThat(testProjet.getDp()).isEqualTo(UPDATED_DP);
        assertThat(testProjet.getTypeAudit()).isEqualTo(UPDATED_TYPE_AUDIT);
        assertThat(testProjet.getEntrepriseAuditeur()).isEqualTo(UPDATED_ENTREPRISE_AUDITEUR);
        assertThat(testProjet.getDateRestitution()).isEqualTo(UPDATED_DATE_RESTITUTION);
    }

    @Test
    @Transactional
    public void updateNonExistingProjet() throws Exception {
        int databaseSizeBeforeUpdate = projetRepository.findAll().size();

        // Create the Projet

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjetMockMvc.perform(put("/api/projets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projet)))
            .andExpect(status().isBadRequest());

        // Validate the Projet in the database
        List<Projet> projetList = projetRepository.findAll();
        assertThat(projetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjet() throws Exception {
        // Initialize the database
        projetRepository.saveAndFlush(projet);

        int databaseSizeBeforeDelete = projetRepository.findAll().size();

        // Get the projet
        restProjetMockMvc.perform(delete("/api/projets/{id}", projet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projet> projetList = projetRepository.findAll();
        assertThat(projetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projet.class);
        Projet projet1 = new Projet();
        projet1.setId(1L);
        Projet projet2 = new Projet();
        projet2.setId(projet1.getId());
        assertThat(projet1).isEqualTo(projet2);
        projet2.setId(2L);
        assertThat(projet1).isNotEqualTo(projet2);
        projet1.setId(null);
        assertThat(projet1).isNotEqualTo(projet2);
    }
}
