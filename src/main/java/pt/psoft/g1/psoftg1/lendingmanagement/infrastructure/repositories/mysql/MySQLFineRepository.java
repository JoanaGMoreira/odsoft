package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.entities.FineEntity;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Fine;
import pt.psoft.g1.psoftg1.lendingmanagement.repositories.FineRepository;

import java.util.Optional;

@Repository
@Profile("mysql")
public interface MySQLFineRepository extends JpaRepository<FineEntity, Long> {

    @Query("SELECT f " +
            "FROM FineEntity f " +
            "JOIN LendingEntity l ON f.lending.pk = l.pk " +
            "WHERE l.lendingNumber.lendingNumber = :lendingNumber")
    Optional<FineEntity> findByLendingNumber(String lendingNumber);

}
