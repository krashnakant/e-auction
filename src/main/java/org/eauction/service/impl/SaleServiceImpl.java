package org.eauction.service.impl;

import org.eauction.service.SaleService;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.eauction.domain.Sale;
import org.eauction.repository.SaleRepository;
import org.eauction.service.dto.SaleDTO;
import org.eauction.service.mapper.SaleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Sale.
 */
@Service
@Transactional
public class SaleServiceImpl implements SaleService{

    private final Logger log = LoggerFactory.getLogger(SaleServiceImpl.class);

    private final SaleRepository saleRepository;

    private final SaleMapper saleMapper;

    public SaleServiceImpl(SaleRepository saleRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    /**
     * Save a sale.
     *
     * @param saleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SaleDTO save(SaleDTO saleDTO) {
        log.debug("Request to save Sale : {}", saleDTO);
        Sale sale = saleMapper.toEntity(saleDTO);
        sale = saleRepository.save(sale);
        return saleMapper.toDto(sale);
    }

    /**
     * Get all the sales.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SaleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sales");
        return saleRepository.findAll(pageable)
            .map(saleMapper::toDto);
    }

    /**
     * Get one sale by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SaleDTO findOne(Long id) {
        log.debug("Request to get Sale : {}", id);
        Sale sale = saleRepository.findOneWithEagerRelationships(id);
        return saleMapper.toDto(sale);
    }

    /**
     * Delete the sale by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sale : {}", id);
        saleRepository.delete(id);
    }

	@Override
	@Transactional(readOnly = true)
	public List<SaleDTO> findAllByCategory_Id(Long id) {
		log.debug("Request to get all Sales by Category Id");
        return saleRepository.findAllByCategory_Id(id).stream()
            .map(saleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
	}
}
