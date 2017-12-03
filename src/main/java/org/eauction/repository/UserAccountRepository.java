package org.eauction.repository;

import java.util.Optional;

import org.eauction.domain.UserAccount;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the UserAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	Optional<UserAccount> findOneByUser_Login(String login);
}
