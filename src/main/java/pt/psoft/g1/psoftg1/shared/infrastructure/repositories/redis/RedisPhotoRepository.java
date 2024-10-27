package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.redis;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.shared.repositories.PhotoRepository;

@Repository
@Profile("redis")
public class RedisPhotoRepository implements PhotoRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisPhotoRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void deleteByPhotoFile(String photoFile) {

    }
}
