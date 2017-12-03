package org.eauction.service;

import org.eauction.domain.UserAccount;
import org.eauction.service.dto.UserAccountDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing UserAccount.
 */
public interface UserAccountService {

    /**
     * Save a userAccount.
     *
     * @param userAccountDTO the entity to save
     * @return the persisted entity
     */
    UserAccountDTO save(UserAccountDTO userAccountDTO);

    /**
     * Get all the userAccounts.
     *
     * @return the list of entities
     */
    List<UserAccountDTO> findAll();

    /**
     * Get the "id" userAccount.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserAccountDTO findOne(Long id);

    /**
     * Delete the "id" userAccount.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    Optional<UserAccount> findOneByUser(String login);
}
