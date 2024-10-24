package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code MonthsPaid} does not match any of the keywords given.
 */
public class MonthsNotPaidContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public MonthsNotPaidContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .noneMatch(keyword -> person.getMonthsPaid().stream()
                        .anyMatch(monthPaid -> StringUtil.containsWordIgnoreCase(monthPaid.monthPaidValue, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MonthsNotPaidContainsKeywordsPredicate)) {
            return false;
        }

        MonthsNotPaidContainsKeywordsPredicate otherPredicate = (MonthsNotPaidContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keywords", keywords).toString();
    }
}
