package org.eauction.service.mapper;

import org.eauction.domain.*;
import org.eauction.service.dto.ItemAttributeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ItemAttribute and its DTO ItemAttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {AttributeMapper.class, ItemMapper.class})
public interface ItemAttributeMapper extends EntityMapper<ItemAttributeDTO, ItemAttribute> {

    @Mapping(source = "attribute.id", target = "attributeId")
    @Mapping(source = "attribute.attributeName", target = "attributeAttributeName")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.itemTitle", target = "itemItemTitle")
    ItemAttributeDTO toDto(ItemAttribute itemAttribute); 

    @Mapping(source = "attributeId", target = "attribute")
    @Mapping(source = "itemId", target = "item")
    ItemAttribute toEntity(ItemAttributeDTO itemAttributeDTO);

    default ItemAttribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemAttribute itemAttribute = new ItemAttribute();
        itemAttribute.setId(id);
        return itemAttribute;
    }
}
