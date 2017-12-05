package org.eauction.service.mapper;

import org.eauction.domain.*;
import org.eauction.service.dto.BidDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bid and its DTO BidDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class, UserMapper.class})
public interface BidMapper extends EntityMapper<BidDTO, Bid> {

    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.itemTitle", target = "itemItemTitle")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    BidDTO toDto(Bid bid); 

    @Mapping(source = "itemId", target = "item")
    @Mapping(source = "userId", target = "user")
    Bid toEntity(BidDTO bidDTO);

    default Bid fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bid bid = new Bid();
        bid.setId(id);
        return bid;
    }
}
