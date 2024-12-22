package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.FineMapper;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Fine;
import pt.psoft.g1.psoftg1.lendingmanagement.repositories.FineRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("h2")
public class MySQLFineRepositoryImpl implements FineRepository {

    private final MySQLFineRepository mySQLFineRepository;

    @Autowired
    public MySQLFineRepositoryImpl(@Lazy MySQLFineRepository mySQLFineRepository) {
        this.mySQLFineRepository = mySQLFineRepository;
    }

    @Override
    public Optional<Fine> findByLendingNumber(String lendingNumber) {
        return mySQLFineRepository.findByLendingNumber(lendingNumber).map(FineMapper::toModel);
    }

    @Override
    public List<Fine> findAll() {
        return mySQLFineRepository.findAll().stream().map(FineMapper::toModel).toList();
    }

    @Override
    public Fine save(Fine fine) {
        return FineMapper.toModel(mySQLFineRepository.save(FineMapper.toEntity(fine)));
    }
}
