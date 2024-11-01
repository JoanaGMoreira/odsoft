package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;

import lombok.RequiredArgsConstructor;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.FineMapper;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Fine;
import pt.psoft.g1.psoftg1.lendingmanagement.repositories.FineRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MySQLLendingRepositoryImpl implements FineRepository {

    private final MySQLFineRepository mySQLFineRepository;

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
