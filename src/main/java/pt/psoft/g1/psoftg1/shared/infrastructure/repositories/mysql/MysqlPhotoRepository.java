package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pt.psoft.g1.psoftg1.shared.repositories.PhotoRepository;

public interface MysqlPhotoRepository extends JpaRepository<PhotoEntity, Long> {
    /*@Override
    @Query("SELECT p " +
            "FROM Photo p " +
            "WHERE p.pk = :photo_id")
    Optional<Photo> findById(@Param("photo_id") long id);*/
    @Modifying
    @Transactional
    @Query("DELETE " +
            "FROM PhotoEntity p " +
            "WHERE p.photoFile = :photoFile")
    void deleteByPhotoFile(String photoFile);
}
