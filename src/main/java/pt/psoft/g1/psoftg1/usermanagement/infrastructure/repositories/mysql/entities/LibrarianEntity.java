package pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.entities;

import jakarta.persistence.Entity;

@Entity
public class LibrarianEntity extends UserEntity {
    protected LibrarianEntity() {
        // for ORM only
    }
}
