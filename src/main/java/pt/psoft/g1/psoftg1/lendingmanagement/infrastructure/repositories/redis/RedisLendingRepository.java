package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.redis;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Fine;
import pt.psoft.g1.psoftg1.lendingmanagement.repositories.FineRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("redis")
public class RedisLendingRepository implements FineRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisLendingRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<Fine> findByLendingNumber(String lendingNumber) {
        return Optional.empty();
    }

    @Override
    public List<Fine> findAll() {
        return null;
    }

    @Override
    public Fine save(Fine fine) {
        return null;
    }
}
