package org.eauction.web.rest;

import org.eauction.EauctionApp;

import org.eauction.domain.Bid;
import org.eauction.repository.BidRepository;
import org.eauction.service.BidService;
import org.eauction.service.dto.BidDTO;
import org.eauction.service.mapper.BidMapper;
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

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.eauction.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BidResource REST controller.
 *
 * @see BidResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EauctionApp.class)
public class BidResourceIntTest {

    private static final BigDecimal DEFAULT_BID_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BID_PRICE = new BigDecimal(2);

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BidMapper bidMapper;

    @Autowired
    private BidService bidService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBidMockMvc;

    private Bid bid;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BidResource bidResource = new BidResource(bidService);
        this.restBidMockMvc = MockMvcBuilders.standaloneSetup(bidResource)
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
    public static Bid createEntity(EntityManager em) {
        Bid bid = new Bid()
            .bidPrice(DEFAULT_BID_PRICE);
        return bid;
    }

    @Before
    public void initTest() {
        bid = createEntity(em);
    }

    @Test
    @Transactional
    public void createBid() throws Exception {
        int databaseSizeBeforeCreate = bidRepository.findAll().size();

        // Create the Bid
        BidDTO bidDTO = bidMapper.toDto(bid);
        restBidMockMvc.perform(post("/api/bids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bidDTO)))
            .andExpect(status().isCreated());

        // Validate the Bid in the database
        List<Bid> bidList = bidRepository.findAll();
        assertThat(bidList).hasSize(databaseSizeBeforeCreate + 1);
        Bid testBid = bidList.get(bidList.size() - 1);
        assertThat(testBid.getBidPrice()).isEqualTo(DEFAULT_BID_PRICE);
    }

    @Test
    @Transactional
    public void createBidWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bidRepository.findAll().size();

        // Create the Bid with an existing ID
        bid.setId(1L);
        BidDTO bidDTO = bidMapper.toDto(bid);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBidMockMvc.perform(post("/api/bids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bidDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bid in the database
        List<Bid> bidList = bidRepository.findAll();
        assertThat(bidList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBidPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = bidRepository.findAll().size();
        // set the field null
        bid.setBidPrice(null);

        // Create the Bid, which fails.
        BidDTO bidDTO = bidMapper.toDto(bid);

        restBidMockMvc.perform(post("/api/bids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bidDTO)))
            .andExpect(status().isBadRequest());

        List<Bid> bidList = bidRepository.findAll();
        assertThat(bidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBids() throws Exception {
        // Initialize the database
        bidRepository.saveAndFlush(bid);

        // Get all the bidList
        restBidMockMvc.perform(get("/api/bids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bid.getId().intValue())))
            .andExpect(jsonPath("$.[*].bidPrice").value(hasItem(DEFAULT_BID_PRICE.intValue())));
    }

    @Test
    @Transactional
    public void getBid() throws Exception {
        // Initialize the database
        bidRepository.saveAndFlush(bid);

        // Get the bid
        restBidMockMvc.perform(get("/api/bids/{id}", bid.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bid.getId().intValue()))
            .andExpect(jsonPath("$.bidPrice").value(DEFAULT_BID_PRICE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBid() throws Exception {
        // Get the bid
        restBidMockMvc.perform(get("/api/bids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBid() throws Exception {
        // Initialize the database
        bidRepository.saveAndFlush(bid);
        int databaseSizeBeforeUpdate = bidRepository.findAll().size();

        // Update the bid
        Bid updatedBid = bidRepository.findOne(bid.getId());
        // Disconnect from session so that the updates on updatedBid are not directly saved in db
        em.detach(updatedBid);
        updatedBid
            .bidPrice(UPDATED_BID_PRICE);
        BidDTO bidDTO = bidMapper.toDto(updatedBid);

        restBidMockMvc.perform(put("/api/bids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bidDTO)))
            .andExpect(status().isOk());

        // Validate the Bid in the database
        List<Bid> bidList = bidRepository.findAll();
        assertThat(bidList).hasSize(databaseSizeBeforeUpdate);
        Bid testBid = bidList.get(bidList.size() - 1);
        assertThat(testBid.getBidPrice()).isEqualTo(UPDATED_BID_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingBid() throws Exception {
        int databaseSizeBeforeUpdate = bidRepository.findAll().size();

        // Create the Bid
        BidDTO bidDTO = bidMapper.toDto(bid);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBidMockMvc.perform(put("/api/bids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bidDTO)))
            .andExpect(status().isCreated());

        // Validate the Bid in the database
        List<Bid> bidList = bidRepository.findAll();
        assertThat(bidList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBid() throws Exception {
        // Initialize the database
        bidRepository.saveAndFlush(bid);
        int databaseSizeBeforeDelete = bidRepository.findAll().size();

        // Get the bid
        restBidMockMvc.perform(delete("/api/bids/{id}", bid.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bid> bidList = bidRepository.findAll();
        assertThat(bidList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bid.class);
        Bid bid1 = new Bid();
        bid1.setId(1L);
        Bid bid2 = new Bid();
        bid2.setId(bid1.getId());
        assertThat(bid1).isEqualTo(bid2);
        bid2.setId(2L);
        assertThat(bid1).isNotEqualTo(bid2);
        bid1.setId(null);
        assertThat(bid1).isNotEqualTo(bid2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BidDTO.class);
        BidDTO bidDTO1 = new BidDTO();
        bidDTO1.setId(1L);
        BidDTO bidDTO2 = new BidDTO();
        assertThat(bidDTO1).isNotEqualTo(bidDTO2);
        bidDTO2.setId(bidDTO1.getId());
        assertThat(bidDTO1).isEqualTo(bidDTO2);
        bidDTO2.setId(2L);
        assertThat(bidDTO1).isNotEqualTo(bidDTO2);
        bidDTO1.setId(null);
        assertThat(bidDTO1).isNotEqualTo(bidDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bidMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bidMapper.fromId(null)).isNull();
    }
}
