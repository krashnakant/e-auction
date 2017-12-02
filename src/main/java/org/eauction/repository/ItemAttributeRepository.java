package org.eauction.repository;

import org.eauction.domain.ItemAttribute;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ItemAttribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAttributeRepository extends JpaRepository<ItemAttribute, Long> {

}
