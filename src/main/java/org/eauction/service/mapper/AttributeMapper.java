package org.eauction.service.mapper;

import org.eauction.domain.*;
import org.eauction.service.dto.AttributeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Attribute and its DTO AttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {SubCategoryMapper.class})
public interface AttributeMapper extends EntityMapper<AttributeDTO, Attribute> {

    @Mapping(source = "subCategory.id", target = "subCategoryId")
    AttributeDTO toDto(Attribute attribute); 

    @Mapping(source = "subCategoryId", target = "subCategory")
    Attribute toEntity(AttributeDTO attributeDTO);

    default Attribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attribute attribute = new Attribute();
        attribute.setId(id);
        return attribute;
    }
}
