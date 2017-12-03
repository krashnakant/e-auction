package org.eauction.service.impl;

import org.eauction.domain.Bid;
import org.eauction.repository.BidRepository;
import org.eauction.service.BidService;
import org.eauction.service.dto.BidDTO;
import org.eauction.service.mapper.BidMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Bid.
 */
@Service
@Transactional
public class BidServiceImpl implements BidService {

	private final Logger log = LoggerFactory.getLogger(BidServiceImpl.class);

	private final BidRepository bidRepository;

	private final BidMapper bidMapper;

	public BidServiceImpl(BidRepository bidRepository, BidMapper bidMapper) {
		this.bidRepository = bidRepository;
		this.bidMapper = bidMapper;
	}

	/**
	 * Save a bid.
	 *
	 * @param bidDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public BidDTO save(BidDTO bidDTO) {
		log.debug("Request to save Bid : {}", bidDTO);
		Bid bid = bidMapper.toEntity(bidDTO);
		bid = bidRepository.save(bid);
		return bidMapper.toDto(bid);
	}

	/**
	 * Get all the bids.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<BidDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Bids");
		return bidRepository.findAll(pageable).map(bidMapper::toDto);
	}

	/**
	 * Get one bid by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public BidDTO findOne(Long id) {
		log.debug("Request to get Bid : {}", id);
		Bid bid = bidRepository.findOne(id);
		return bidMapper.toDto(bid);
	}

	/**
	 * Delete the bid by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Bid : {}", id);
		bidRepository.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public BidDTO findOneByItemAndUserAccount(Long itemid, Long accid) {
		log.debug("Request to get Bid by Item and Account : {}, {}", itemid, accid);
		Bid bid = bidRepository.findOneByItemAndUserAccount(itemid, accid);
		return bidMapper.toDto(bid);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		// TODO Auto-generated method stub
		return bidRepository.count();
	}

}
