package org.eauction.service.mapper;

import org.eauction.domain.*;
import org.eauction.service.dto.UserAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserAccount and its DTO UserAccountDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserAccountMapper extends EntityMapper<UserAccountDTO, UserAccount> {

    @Mapping(source = "user.id", target = "userId")
    UserAccountDTO toDto(UserAccount userAccount); 

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "sales", ignore = true)
    UserAccount toEntity(UserAccountDTO userAccountDTO);

    default UserAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);
        return userAccount;
    }
}
