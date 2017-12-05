package org.eauction.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eauction.web.rest.TestUtil.createFormattingConversionService;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

import org.eauction.EauctionApp;
import org.eauction.domain.Attribute;
import org.eauction.domain.Item;
import org.eauction.domain.ItemAttribute;
import org.eauction.repository.ItemAttributeRepository;
import org.eauction.service.dto.ItemAttributeDTO;
import org.eauction.service.mapper.ItemAttributeMapper;
import org.eauction.web.rest.errors.ExceptionTranslator;
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

/**
 * Test class for the ItemAttributeResource REST controller.
 *
 * @see ItemAttributeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EauctionApp.class)
public class ItemAttributeResourceIntTest {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private ItemAttributeRepository itemAttributeRepository;

    @Autowired
    private ItemAttributeMapper itemAttributeMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemAttributeMockMvc;

    private ItemAttribute itemAttribute;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemAttributeResource itemAttributeResource = new ItemAttributeResource(itemAttributeRepository, itemAttributeMapper);
        this.restItemAttributeMockMvc = MockMvcBuilders.standaloneSetup(itemAttributeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemAttribute createEntity(EntityManager em) {
        ItemAttribute itemAttribute = new ItemAttribute()
            .value(DEFAULT_VALUE);
        // Add required entity
        Attribute attribute = AttributeResourceIntTest.createEntity(em);
        em.persist(attribute);
        em.flush();
        itemAttribute.setAttribute(attribute);
        // Add required entity
        Item item = ItemResourceIntTest.createEntity(em);
        em.persist(item);
        em.flush();
        itemAttribute.setItem(item);
        return itemAttribute;
    }

    @Before
    public void initTest() {
        itemAttribute = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemAttribute() throws Exception {
        int databaseSizeBeforeCreate = itemAttributeRepository.findAll().size();

        // Create the ItemAttribute
        ItemAttributeDTO itemAttributeDTO = itemAttributeMapper.toDto(itemAttribute);
        restItemAttributeMockMvc.perform(post("/api/item-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemAttribute in the database
        List<ItemAttribute> itemAttributeList = itemAttributeRepository.findAll();
        assertThat(itemAttributeList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAttribute testItemAttribute = itemAttributeList.get(itemAttributeList.size() - 1);
        assertThat(testItemAttribute.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createItemAttributeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAttributeRepository.findAll().size();

        // Create the ItemAttribute with an existing ID
        itemAttribute.setId(1L);
        ItemAttributeDTO itemAttributeDTO = itemAttributeMapper.toDto(itemAttribute);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAttributeMockMvc.perform(post("/api/item-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAttribute in the database
        List<ItemAttribute> itemAttributeList = itemAttributeRepository.findAll();
        assertThat(itemAttributeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAttributeRepository.findAll().size();
        // set the field null
        itemAttribute.setValue(null);

        // Create the ItemAttribute, which fails.
        ItemAttributeDTO itemAttributeDTO = itemAttributeMapper.toDto(itemAttribute);

        restItemAttributeMockMvc.perform(post("/api/item-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAttribute> itemAttributeList = itemAttributeRepository.findAll();
        assertThat(itemAttributeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemAttributes() throws Exception {
        // Initialize the database
        itemAttributeRepository.saveAndFlush(itemAttribute);

        // Get all the itemAttributeList
        restItemAttributeMockMvc.perform(get("/api/item-attributes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemAttribute.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }

    @Test
    @Transactional
    public void getItemAttribute() throws Exception {
        // Initialize the database
        itemAttributeRepository.saveAndFlush(itemAttribute);

        // Get the itemAttribute
        restItemAttributeMockMvc.perform(get("/api/item-attributes/{id}", itemAttribute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemAttribute.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemAttribute() throws Exception {
        // Get the itemAttribute
        restItemAttributeMockMvc.perform(get("/api/item-attributes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemAttribute() throws Exception {
        // Initialize the database
        itemAttributeRepository.saveAndFlush(itemAttribute);
        int databaseSizeBeforeUpdate = itemAttributeRepository.findAll().size();

        // Update the itemAttribute
        ItemAttribute updatedItemAttribute = itemAttributeRepository.findOne(itemAttribute.getId());
        // Disconnect from session so that the updates on updatedItemAttribute are not directly saved in db
        em.detach(updatedItemAttribute);
        updatedItemAttribute
            .value(UPDATED_VALUE);
        ItemAttributeDTO itemAttributeDTO = itemAttributeMapper.toDto(updatedItemAttribute);

        restItemAttributeMockMvc.perform(put("/api/item-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeDTO)))
            .andExpect(status().isOk());

        // Validate the ItemAttribute in the database
        List<ItemAttribute> itemAttributeList = itemAttributeRepository.findAll();
        assertThat(itemAttributeList).hasSize(databaseSizeBeforeUpdate);
        ItemAttribute testItemAttribute = itemAttributeList.get(itemAttributeList.size() - 1);
        assertThat(testItemAttribute.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingItemAttribute() throws Exception {
        int databaseSizeBeforeUpdate = itemAttributeRepository.findAll().size();

        // Create the ItemAttribute
        ItemAttributeDTO itemAttributeDTO = itemAttributeMapper.toDto(itemAttribute);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restItemAttributeMockMvc.perform(put("/api/item-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemAttribute in the database
        List<ItemAttribute> itemAttributeList = itemAttributeRepository.findAll();
        assertThat(itemAttributeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteItemAttribute() throws Exception {
        // Initialize the database
        itemAttributeRepository.saveAndFlush(itemAttribute);
        int databaseSizeBeforeDelete = itemAttributeRepository.findAll().size();

        // Get the itemAttribute
        restItemAttributeMockMvc.perform(delete("/api/item-attributes/{id}", itemAttribute.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemAttribute> itemAttributeList = itemAttributeRepository.findAll();
        assertThat(itemAttributeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAttribute.class);
        ItemAttribute itemAttribute1 = new ItemAttribute();
        itemAttribute1.setId(1L);
        ItemAttribute itemAttribute2 = new ItemAttribute();
        itemAttribute2.setId(itemAttribute1.getId());
        assertThat(itemAttribute1).isEqualTo(itemAttribute2);
        itemAttribute2.setId(2L);
        assertThat(itemAttribute1).isNotEqualTo(itemAttribute2);
        itemAttribute1.setId(null);
        assertThat(itemAttribute1).isNotEqualTo(itemAttribute2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAttributeDTO.class);
        ItemAttributeDTO itemAttributeDTO1 = new ItemAttributeDTO();
        itemAttributeDTO1.setId(1L);
        ItemAttributeDTO itemAttributeDTO2 = new ItemAttributeDTO();
        assertThat(itemAttributeDTO1).isNotEqualTo(itemAttributeDTO2);
        itemAttributeDTO2.setId(itemAttributeDTO1.getId());
        assertThat(itemAttributeDTO1).isEqualTo(itemAttributeDTO2);
        itemAttributeDTO2.setId(2L);
        assertThat(itemAttributeDTO1).isNotEqualTo(itemAttributeDTO2);
        itemAttributeDTO1.setId(null);
        assertThat(itemAttributeDTO1).isNotEqualTo(itemAttributeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(itemAttributeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(itemAttributeMapper.fromId(null)).isNull();
    }
}
