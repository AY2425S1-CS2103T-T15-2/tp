package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents a Person's months paid in the address book.
 */
public class MonthPaid {
    public static final String MESSAGE_CONSTRAINTS = "MonthPaid should take a string with the pattern"
            + " YYYY-MM, where one or of such patterns can be included if separated by a space.";
    public static final String SPLIT_VALIDATION_REGEX = "^[0-9]{4}-[0-9]{2}$";
    public final String value;

    public MonthPaid(String monthsPaid) {
        this.value = monthsPaid;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if a given string is a valid monthsPaid.
     */
    public static boolean isValidMonthPaid(String monthPaid) {
        return monthPaid.matches(SPLIT_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MonthPaid otherMonthPaid)) {
            return false;
        }

        return Objects.equals(value, otherMonthPaid.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
