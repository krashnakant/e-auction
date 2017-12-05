package org.eauction.repository;

import java.util.List;

import org.eauction.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Bid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("select bid from Bid bid where bid.user.login = ?#{principal.username}")
    List<Bid> findByUserIsCurrentUser();

}
