package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.person.DepartmentContainsKeywordsPredicate;

import java.util.logging.Filter;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose department contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "filterdepartment";
    public static final String COMMAND_ALIAS = "fd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose department contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " junior management";

    private final DepartmentContainsKeywordsPredicate predicate;

    public FilterDepartmentCommand(DepartmentContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredDepartmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredDepartmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterDepartmentCommand // instanceof handles nulls
                && predicate.equals(((FilterDepartmentCommand) other).predicate)); // state check
    }
}
