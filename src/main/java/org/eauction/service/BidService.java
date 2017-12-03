package org.eauction.service;

import org.eauction.service.dto.BidDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Bid.
 */
public interface BidService {

	/**
	 * Save a bid.
	 *
	 * @param bidDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	BidDTO save(BidDTO bidDTO);

	/**
	 * Get all the bids.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<BidDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" bid.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	BidDTO findOne(Long id);

	/**
	 * Delete the "id" bid.
	 *
	 * @param id
	 *            the id of the entity
	 */
	void delete(Long id);

	BidDTO findOneByItemAndUserAccount(Long itemid, Long accid);
	
	Long count();
}
