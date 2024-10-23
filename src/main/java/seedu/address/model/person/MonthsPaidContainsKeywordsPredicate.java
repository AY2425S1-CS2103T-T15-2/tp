package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that a {@code Person}'s {@code MonthsPaid} matches any of the keywords given.
 */
public class MonthsPaidContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    public MonthsPaidContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getMonthsPaid().stream()
                        .anyMatch(monthPaid -> regexMatch(monthPaid.toString(), keyword)));
    }

    private boolean regexMatch(String name, String keyword) {
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(name).find();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MonthsPaidContainsKeywordsPredicate)) {
            return false;
        }

        MonthsPaidContainsKeywordsPredicate otherMonthsPaidContainsKeywordsPredicate =
                (MonthsPaidContainsKeywordsPredicate) other;
        return keywords.equals(otherMonthsPaidContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
