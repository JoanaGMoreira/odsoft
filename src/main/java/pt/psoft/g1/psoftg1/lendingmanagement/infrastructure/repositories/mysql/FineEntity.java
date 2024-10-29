package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;

import jakarta.persistence.*;
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

    @Column(name = "fine_value_per_day_in_cents", updatable = false)
    private int fineValuePerDayInCents;

    @Column(name = "cents_value")
    private int centsValue;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "lending_pk", nullable = false, unique = true)
    private LendingEntity lending;

    // Protected constructor for JPA
    protected FineEntity() {}

    public FineEntity(int fineValuePerDayInCents, int centsValue, LendingEntity lending) {
        this.fineValuePerDayInCents = fineValuePerDayInCents;
        this.centsValue = centsValue;
        this.lending = lending;
    }
}
