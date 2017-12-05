package org.eauction.repository;

import java.util.List;

import org.eauction.domain.ItemAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the ItemAttribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAttributeRepository extends JpaRepository<ItemAttribute, Long> {
	List<ItemAttribute> findAllByItem_Id(Long id);
}
