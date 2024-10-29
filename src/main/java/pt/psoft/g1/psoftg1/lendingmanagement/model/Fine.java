package pt.psoft.g1.psoftg1.lendingmanagement.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

/**
 * The {@code Fine} class represents a fine applied when a lending is past its due date.
 * This is the service layer model, without persistence annotations.
 */
@Getter
public class Fine {

    private int fineValuePerDayInCents;

    private int centsValue;

    @Setter
    private Lending lending;

    /**
     * Constructs a new {@code Fine} object with business logic for delayed lendings.
     *
     * @param lending the transaction which generates this fine.
     */
    public Fine(Lending lending) {
        if (lending.getDaysDelayed() <= 0)
            throw new IllegalArgumentException("Lending is not overdue");

        this.fineValuePerDayInCents = lending.getFineValuePerDayInCents();
        this.centsValue = fineValuePerDayInCents * lending.getDaysDelayed();
        this.lending = Objects.requireNonNull(lending);
    }
}
