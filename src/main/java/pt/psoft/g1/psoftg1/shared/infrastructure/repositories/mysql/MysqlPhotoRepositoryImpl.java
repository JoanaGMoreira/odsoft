package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import pt.psoft.g1.psoftg1.shared.repositories.PhotoRepository;

public class MysqlPhotoRepositoryImpl implements PhotoRepository {

    private final MysqlPhotoRepository mysqlRepository;

    public MysqlPhotoRepositoryImpl(MysqlPhotoRepository mysqlRepository) {
        this.mysqlRepository = mysqlRepository;
    }

    @Override
    public void deleteByPhotoFile(String photoFile) {
        mysqlRepository.deleteByPhotoFile(photoFile);
    }
}
