package pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import pt.psoft.g1.psoftg1.shared.services.Page;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.UserMapper;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.entities.UserEntity;
import pt.psoft.g1.psoftg1.usermanagement.model.User;
import pt.psoft.g1.psoftg1.usermanagement.repositories.UserRepository;
import pt.psoft.g1.psoftg1.usermanagement.services.SearchUsersQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * use JPA Criteria API to build the custom query
 *
 * @see <a href="https://www.baeldung.com/hibernate-criteria-queries">...</a>
 */
@RequiredArgsConstructor
@Repository
class UserRepoCustomImpl implements UserRepository {

    // get the underlying JPA Entity Manager via spring through constructor dependency
    // injection
    private final EntityManager em;
    private final MysqlUserRepository mysqlUserRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserRepoCustomImpl.class);

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("Entities to save cannot be null");
        }

        List<S> entityList = StreamSupport.stream(entities.spliterator(), false).toList();
        if (entityList.isEmpty()) {
            return List.of();  // Return empty list if no entities to save
        }

        // Logging the saveAll operation
        logger.debug("Saving batch of {} user entities", entityList.size());

        // Convert domain models to database entities
        List<UserEntity> dbEntities = entityList.stream()
                .map(UserMapper::toEntity)
                .toList();

        // Save all database entities in batch
        List<UserEntity> savedDbEntities = mysqlUserRepository.saveAll(dbEntities);

        // Convert saved database entities back to domain models
        List<S> savedModels = savedDbEntities.stream()
                .map(dbEntity -> (S) UserMapper.toModel(dbEntity))
                .toList();

        logger.debug("Saved batch of {} user models", savedModels.size());

        return savedModels;
    }

    @Override
    public <S extends User> S save(S entity) {
        logger.debug("Saving user entity: {}", entity);
        var dbEntity = UserMapper.toEntity(entity);

        // Save to database
        var savedDbEntity = mysqlUserRepository.save(dbEntity);

        // Convert back to domain model
        S savedModel = (S) UserMapper.toModel(savedDbEntity);

        logger.debug("Saved user model: {}", savedModel);
        return savedModel;
    }

    @Override
    public Optional<User> findById(Long objectId) {
        return mysqlUserRepository.findById(objectId).map(UserMapper::toModel);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return mysqlUserRepository.findByUsername(username).map(UserMapper::toModel);
    }

    @Override
    public List<User> searchUsers(final Page page, final SearchUsersQuery query) {

        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<User> cq = cb.createQuery(User.class);
        final Root<User> root = cq.from(User.class);
        cq.select(root);

        final List<Predicate> where = new ArrayList<>();
        if (StringUtils.hasText(query.getUsername())) {
            where.add(cb.equal(root.get("username"), query.getUsername()));
        }
        if (StringUtils.hasText(query.getFullName())) {
            where.add(cb.like(root.get("fullName"), "%" + query.getFullName() + "%"));
        }

        // search using OR
        if (!where.isEmpty()) {
            cq.where(cb.or(where.toArray(new Predicate[0])));
        }

        cq.orderBy(cb.desc(root.get("createdAt")));

        final TypedQuery<User> q = em.createQuery(cq);
        q.setFirstResult((page.getNumber() - 1) * page.getLimit());
        q.setMaxResults(page.getLimit());

        return q.getResultList();
    }

    @Override
    public List<User> findByNameName(String name) {
        return mysqlUserRepository.findByNameName(name).stream().map(UserMapper::toModel).toList();
    }

    @Override
    public List<User> findByNameNameContains(String name) {
        return List.of();
    }

    @Override
    public void delete(User user) {
        mysqlUserRepository.delete(UserMapper.toEntity(user));
    }


}
