package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.ForbiddenNameMapper;
import pt.psoft.g1.psoftg1.shared.model.ForbiddenName;
import pt.psoft.g1.psoftg1.shared.repositories.ForbiddenNameRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MysqlForbiddenNameRepositoryImpl implements ForbiddenNameRepository {

    private final MysqlForbiddenNameRepository mysqlRepository;

    @Autowired
    public MysqlForbiddenNameRepositoryImpl(@Lazy MysqlForbiddenNameRepository mysqlRepository) {
        this.mysqlRepository = mysqlRepository;
    }

    @Override
    public Optional<ForbiddenName> findByForbiddenName(String forbiddenName) {
        return mysqlRepository.findByForbiddenName(forbiddenName)
                .map(ForbiddenNameMapper::toServiceModelFromMySQLEntity);
    }

    @Override
    @Transactional
    public ForbiddenName save(ForbiddenName forbiddenName) {
        ForbiddenNameEntity entity = ForbiddenNameMapper.toMySQLEntity(forbiddenName);
        ForbiddenNameEntity savedEntity = mysqlRepository.save(entity);
        return ForbiddenNameMapper.toServiceModelFromMySQLEntity(savedEntity);
    }

    @Override
    public List<ForbiddenName> findAll() {
        return mysqlRepository.findAll()
                .stream()
                .map(ForbiddenNameMapper::toServiceModelFromMySQLEntity)
                .collect(Collectors.toList());

    }

    public List<ForbiddenName> findByForbiddenNameContaining(String pat) {
        return mysqlRepository.findByForbiddenNameContaining(pat)
                .stream()
                .map(ForbiddenNameMapper::toServiceModelFromMySQLEntity)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public int deleteForbiddenName(String forbiddenName) {
        return mysqlRepository.deleteForbiddenName(forbiddenName);
    }
}
