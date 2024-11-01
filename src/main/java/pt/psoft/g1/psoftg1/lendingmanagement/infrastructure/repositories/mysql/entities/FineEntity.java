package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@code FineEntity} class is the database model representing a fine record in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "fine")
public class FineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @PositiveOrZero
    @Column(updatable = false)
    private int fineValuePerDayInCents;

    /**Fine value in Euro cents*/
    @PositiveOrZero
    int centsValue;

    @Setter
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "lending_pk", nullable = false, unique = true)
    private LendingEntity lending;

    // Protected constructor for JPA
    public FineEntity() {}
}
