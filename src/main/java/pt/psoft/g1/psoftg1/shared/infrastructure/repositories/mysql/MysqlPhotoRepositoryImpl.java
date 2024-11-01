package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.shared.repositories.PhotoRepository;

@Repository
public class MysqlPhotoRepositoryImpl implements PhotoRepository {

    private final MysqlPhotoRepository mysqlRepository;

    public MysqlPhotoRepositoryImpl(@Lazy MysqlPhotoRepository mysqlRepository) {
        this.mysqlRepository = mysqlRepository;
    }

    @Override
    public void deleteByPhotoFile(String photoFile) {
        mysqlRepository.deleteByPhotoFile(photoFile);
    }
}
