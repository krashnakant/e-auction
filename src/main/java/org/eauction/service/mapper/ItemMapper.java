package org.eauction.service.mapper;

import org.eauction.domain.*;
import org.eauction.service.dto.ItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Item and its DTO ItemDTO.
 */
@Mapper(componentModel = "spring", uses = {SaleMapper.class})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    @Mapping(source = "sale.id", target = "saleId")
    ItemDTO toDto(Item item); 

    @Mapping(target = "itemAttributes", ignore = true)
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
