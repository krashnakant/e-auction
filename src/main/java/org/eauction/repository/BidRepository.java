package org.eauction.repository;

import org.eauction.domain.Bid;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Bid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

}
