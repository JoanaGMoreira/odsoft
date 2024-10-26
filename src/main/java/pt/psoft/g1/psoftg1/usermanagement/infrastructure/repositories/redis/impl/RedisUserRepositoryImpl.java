package pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.redis.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.shared.services.Page;
import pt.psoft.g1.psoftg1.usermanagement.model.User;
import pt.psoft.g1.psoftg1.usermanagement.repositories.UserRepository;
import pt.psoft.g1.psoftg1.usermanagement.services.SearchUsersQuery;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("redis")
public class RedisUserRepositoryImpl implements UserRepository {
    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public Optional<User> findById(Long objectId) {
        return Optional.empty();
    }

    @Override
    public User getById(Long id) {
        return UserRepository.super.getById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public List<User> searchUsers(Page page, SearchUsersQuery query) {
        return List.of();
    }

    @Override
    public List<User> findByNameName(String name) {
        return List.of();
    }

    @Override
    public List<User> findByNameNameContains(String name) {
        return List.of();
    }

    @Override
    public void delete(User user) {

    }
}
