package org.eauction.service.mapper;

import org.eauction.domain.*;
import org.eauction.service.dto.ItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Item and its DTO ItemDTO.
 */
@Mapper(componentModel = "spring", uses = {SubCategoryMapper.class, UserAccountMapper.class, SaleMapper.class})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    @Mapping(source = "subCategory.id", target = "subCategoryId")
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "sale.id", target = "saleId")
    ItemDTO toDto(Item item); 

    @Mapping(target = "itemAttributes", ignore = true)
    @Mapping(source = "subCategoryId", target = "subCategory")
    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "saleId", target = "sale")
    Item toEntity(ItemDTO itemDTO);

    default Item fromId(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
