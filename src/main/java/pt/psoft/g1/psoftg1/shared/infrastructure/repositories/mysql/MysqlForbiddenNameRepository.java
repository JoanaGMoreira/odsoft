package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MysqlForbiddenNameRepository extends JpaRepository<ForbiddenNameEntity, Long>  {

    @Query("SELECT fn FROM ForbiddenNameEntity fn " +
            "WHERE :pat LIKE CONCAT('%', fn.forbiddenName, '%')")
    List<ForbiddenNameEntity> findByForbiddenNameContaining(String pat);

    @Query("SELECT fn " +
            "FROM ForbiddenNameEntity fn " +
            "WHERE fn.forbiddenName = :forbiddenName")
    Optional<ForbiddenNameEntity> findByForbiddenName(String forbiddenName);

    @Modifying
    @Query("DELETE FROM ForbiddenNameEntity fn WHERE fn.forbiddenName = :forbiddenName")
    int deleteForbiddenName(String forbiddenName);

}
