package org.eauction.repository;

import java.util.List;

import org.eauction.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Item entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select item from Item item where item.user.login = ?#{principal.username}")
    List<Item> findByUserIsCurrentUser();
    
    Page<Item> findAllBySale_Id(Pageable pageable, Long id);
}
