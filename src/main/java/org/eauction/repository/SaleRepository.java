package org.eauction.repository;

import org.eauction.domain.Sale;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Sale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("select distinct sale from Sale sale left join fetch sale.accounts")
    List<Sale> findAllWithEagerRelationships();

    @Query("select sale from Sale sale left join fetch sale.accounts where sale.id =:id")
    Sale findOneWithEagerRelationships(@Param("id") Long id);

}
