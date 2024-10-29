package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.psoft.g1.psoftg1.shared.model.ForbiddenName;
import pt.psoft.g1.psoftg1.shared.repositories.ForbiddenNameRepository;

import java.util.List;
import java.util.Optional;

public interface MysqlForbiddenNameRepository extends ForbiddenNameRepository, JpaRepository<ForbiddenName, Long> {
    @Query("SELECT fn FROM ForbiddenName fn" +
            " WHERE :pat LIKE CONCAT('%', fn.forbiddenName, '%') ")
    List<ForbiddenName> findByForbiddenNameContaining(String pat);

    @Override
    @Query("SELECT fn " +
            "FROM ForbiddenName fn " +
            "WHERE fn.forbiddenName = :forbiddenName")
    Optional<ForbiddenName> findByForbiddenName(String forbiddenName);

    @Override
    @Modifying
    @Query("DELETE FROM ForbiddenName fn WHERE fn.forbiddenName = :forbiddenName")
    int deleteForbiddenName(String forbiddenName);

}