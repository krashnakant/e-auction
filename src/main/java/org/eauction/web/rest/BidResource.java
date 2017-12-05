package org.eauction.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.eauction.service.BidService;
import org.eauction.service.dto.BidDTO;
import org.eauction.web.rest.errors.BadRequestAlertException;
import org.eauction.web.rest.util.HeaderUtil;
import org.eauction.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Bid.
 */
@RestController
@RequestMapping("/api")
public class BidResource {

    private final Logger log = LoggerFactory.getLogger(BidResource.class);

    private static final String ENTITY_NAME = "bid";

    private final BidService bidService;

    public BidResource(BidService bidService) {
        this.bidService = bidService;
    }

    /**
     * POST  /bids : Create a new bid.
     *
     * @param bidDTO the bidDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bidDTO, or with status 400 (Bad Request) if the bid has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bids")
    @Timed
    public ResponseEntity<BidDTO> createBid(@Valid @RequestBody BidDTO bidDTO) throws URISyntaxException {
        log.debug("REST request to save Bid : {}", bidDTO);
        if (bidDTO.getId() != null) {
            throw new BadRequestAlertException("A new bid cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidDTO result = bidService.save(bidDTO);
        return ResponseEntity.created(new URI("/api/bids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bids : Updates an existing bid.
     *
     * @param bidDTO the bidDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bidDTO,
     * or with status 400 (Bad Request) if the bidDTO is not valid,
     * or with status 500 (Internal Server Error) if the bidDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bids")
    @Timed
    public ResponseEntity<BidDTO> updateBid(@Valid @RequestBody BidDTO bidDTO) throws URISyntaxException {
        log.debug("REST request to update Bid : {}", bidDTO);
        if (bidDTO.getId() == null) {
            return createBid(bidDTO);
        }
        BidDTO result = bidService.save(bidDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bidDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bids : get all the bids.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bids in body
     */
    @GetMapping("/bids")
    @Timed
    public ResponseEntity<List<BidDTO>> getAllBids(Pageable pageable) {
        log.debug("REST request to get a page of Bids");
        Page<BidDTO> page = bidService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bids");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /bids/:id : get the "id" bid.
     *
     * @param id the id of the bidDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bidDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bids/{id}")
    @Timed
    public ResponseEntity<BidDTO> getBid(@PathVariable Long id) {
        log.debug("REST request to get Bid : {}", id);
        BidDTO bidDTO = bidService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bidDTO));
    }

    /**
     * DELETE  /bids/:id : delete the "id" bid.
     *
     * @param id the id of the bidDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bids/{id}")
    @Timed
    public ResponseEntity<Void> deleteBid(@PathVariable Long id) {
        log.debug("REST request to delete Bid : {}", id);
        bidService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
