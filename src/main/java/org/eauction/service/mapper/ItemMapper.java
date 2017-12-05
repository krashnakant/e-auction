package org.eauction.service.mapper;

import org.eauction.domain.Item;
import org.eauction.service.dto.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Item and its DTO ItemDTO.
 */
@Mapper(componentModel = "spring", uses = {SubCategoryMapper.class, UserMapper.class, SaleMapper.class})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    @Mapping(source = "subCategory.id", target = "subCategoryId")
    @Mapping(source = "subCategory.subCategoryName", target = "subCategorySubCategoryName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "sale.id", target = "saleId")
    @Mapping(source = "sale.auctionTitle", target = "saleAuctionTitle")
    ItemDTO toDto(Item item); 

    @Mapping(target = "itemAttributes", ignore = true)
    @Mapping(source = "subCategoryId", target = "subCategory")
    @Mapping(source = "userId", target = "user")
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
