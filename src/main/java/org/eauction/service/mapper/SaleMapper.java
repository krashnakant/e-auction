package org.eauction.service.mapper;

import org.eauction.domain.Sale;
import org.eauction.service.dto.SaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Sale and its DTO SaleDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface SaleMapper extends EntityMapper<SaleDTO, Sale> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryCategoryName")
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
