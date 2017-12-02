package org.eauction.service.mapper;

import org.eauction.domain.*;
import org.eauction.service.dto.BidDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bid and its DTO BidDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class, UserAccountMapper.class})
public interface BidMapper extends EntityMapper<BidDTO, Bid> {

    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "account.id", target = "accountId")
    BidDTO toDto(Bid bid); 

    @Mapping(source = "itemId", target = "item")
    @Mapping(source = "accountId", target = "account")
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
