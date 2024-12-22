package pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pt.psoft.g1.psoftg1.exceptions.NotFoundException;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.entities.UserEntity;
import pt.psoft.g1.psoftg1.usermanagement.model.User;

/**
 * Based on https://github.com/Yoh0xFF/java-spring-security-example
 *
 */

public interface MysqlUserRepository extends JpaRepository<UserEntity, Long> {

	@Override
	@CacheEvict(allEntries = true)
	<S extends UserEntity> List<S> saveAll(Iterable<S> entities);

	@Override
	@Caching(evict = { @CacheEvict(key = "#p0.id", condition = "#p0.id != null"),
			@CacheEvict(key = "#p0.username", condition = "#p0.username != null") })
	<S extends UserEntity> S save(S entity);

	/**
	 * findById searches a specific user and returns an optional
	 */
	@Override
	@Cacheable
	Optional<UserEntity> findById(Long objectId);

	/**
	 * getById explicitly loads a user or throws an exception if the user does not
	 * exist or the account is not enabled
	 *
	 * @param id
	 * @return
	 */
	@Cacheable
	default UserEntity getById(final Long id) {
		final Optional<UserEntity> maybeUser = findById(id);
		// throws 404 Not Found if the user does not exist or is not enabled
		return maybeUser.filter(UserEntity::isEnabled).orElseThrow(() -> new NotFoundException(User.class, id));
	}

	@Cacheable
//	@Query("SELECT u " +
//			"FROM UserEntity u " +
//			"WHERE u.username LIKE :username")
	Optional<UserEntity> findByUsername(String username);

	@Cacheable
	List<UserEntity> findByNameName(String name);
}
