package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ClassIdContainsKeywordsPredicate;
import seedu.address.model.person.NameAndClassIdContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;



/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    private final ClassIdContainsKeywordsPredicate predicateClassId;

    private final NameAndClassIdContainsKeywordsPredicate predicateNameAndClassId;


    /**
     * Stores the predicate to be used to filter the list of persons by name
     * @param predicate the predicate to be used to filter the list of persons
     */
    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
        this.predicateClassId = null;
        this.predicateNameAndClassId = null;
    }

    /**
     * Stores the predicate to be used to filter the list of persons by class ID
     * @param predicate the predicate to be used to filter the list of persons
     */
    public FindCommand(ClassIdContainsKeywordsPredicate predicate) {
        this.predicateClassId = predicate;
        this.predicate = null;
        this.predicateNameAndClassId = null;
    }

    /**
     * Stores the predicate to be used to filter the list of persons by name and class ID
     * @param predicate the predicate to be used to filter the list of persons
     */
    public FindCommand(NameAndClassIdContainsKeywordsPredicate predicate) {
        this.predicateNameAndClassId = predicate;
        this.predicate = null;
        this.predicateClassId = null;
    }



    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (predicate != null) {
            model.updateFilteredPersonList(predicate);
        } else if (predicateClassId != null) {
            model.updateFilteredPersonList(predicateClassId);
        } else {
            model.updateFilteredPersonList(predicateNameAndClassId);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;

        if (predicate != null) {
            return predicate.equals(otherFindCommand.predicate);
        } else if (predicateClassId != null) {
            return predicateClassId.equals(otherFindCommand.predicateClassId);
        } else {
            return predicateNameAndClassId.equals(otherFindCommand.predicateNameAndClassId);
        }

    }

    @Override
    public String toString() {
        if (predicate != null) {
            return new ToStringBuilder(this)
                    .add("predicate", predicate)
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("predicate", predicateClassId)
                    .toString();
        }

    }
}
