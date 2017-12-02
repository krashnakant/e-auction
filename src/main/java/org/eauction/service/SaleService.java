package org.eauction.service;

import org.eauction.service.dto.SaleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Sale.
 */
public interface SaleService {

    /**
     * Save a sale.
     *
     * @param saleDTO the entity to save
     * @return the persisted entity
     */
    SaleDTO save(SaleDTO saleDTO);

    /**
     * Get all the sales.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SaleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sale.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SaleDTO findOne(Long id);

    /**
     * Delete the "id" sale.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
