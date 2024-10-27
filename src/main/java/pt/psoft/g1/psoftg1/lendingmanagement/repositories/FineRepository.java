package pt.psoft.g1.psoftg1.lendingmanagement.repositories;

import pt.psoft.g1.psoftg1.lendingmanagement.model.Fine;

import java.util.List;
import java.util.Optional;

public interface FineRepository {

    Optional<Fine> findByLendingNumber(String lendingNumber);
    List<Fine> findAll();
    Fine save(Fine fine);

}
