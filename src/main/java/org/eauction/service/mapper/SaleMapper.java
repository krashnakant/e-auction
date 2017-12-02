package org.eauction.service.mapper;

import org.eauction.domain.*;
import org.eauction.service.dto.SaleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Sale and its DTO SaleDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, UserAccountMapper.class})
public interface SaleMapper extends EntityMapper<SaleDTO, Sale> {

    @Mapping(source = "category.id", target = "categoryId")
    SaleDTO toDto(Sale sale); 

    @Mapping(target = "items", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    Sale toEntity(SaleDTO saleDTO);

    default Sale fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sale sale = new Sale();
        sale.setId(id);
        return sale;
    }
}