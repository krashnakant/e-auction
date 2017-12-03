package org.eauction.repository;

import org.eauction.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Bid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
	@Query("select bid from Bid bid where bid.item.id =:itemid and bid.account.id =:accid")
	Bid findOneByItemAndUserAccount(@Param("itemid") Long itemid, @Param("accid") Long accid);
}
