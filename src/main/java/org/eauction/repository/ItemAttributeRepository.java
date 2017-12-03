package org.eauction.repository;

import java.util.List;

import org.eauction.domain.ItemAttribute;
import org.eauction.domain.Sale;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ItemAttribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAttributeRepository extends JpaRepository<ItemAttribute, Long> {
	List<ItemAttribute> findAllByItem_Id(Long id);
}
