package org.eauction.service.mapper;

import org.eauction.domain.*;
import org.eauction.service.dto.SubCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SubCategory and its DTO SubCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface SubCategoryMapper extends EntityMapper<SubCategoryDTO, SubCategory> {

    @Mapping(source = "category.id", target = "categoryId")
    SubCategoryDTO toDto(SubCategory subCategory); 

    @Mapping(target = "attributes", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    SubCategory toEntity(SubCategoryDTO subCategoryDTO);

    default SubCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubCategory subCategory = new SubCategory();
        subCategory.setId(id);
        return subCategory;
    }
}