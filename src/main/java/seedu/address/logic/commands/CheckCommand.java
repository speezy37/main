package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;

import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Mode;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNricContainsKeywordsPredicate;

/**
 * Check in or out to work.
 */
public class CheckCommand extends Command {

    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks in/out to work. "
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_PASSWORD + "PASSWORD "
            + PREFIX_MODE + "MODE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "G1234567T "
            + PREFIX_PASSWORD + "HEllo12 "
            + PREFIX_MODE + "in ";

    public static final String MESSAGE_SUCCESS = "Successfully checked %1$s";
    public static final String MESSAGE_DUPLICATE = "User has already checked %1$s";
    public static final String MESSAGE_NOT_FOUND = "User is not found!";

    private final String password;
    private final Mode mode;
    private final PersonNricContainsKeywordsPredicate predicate;


    public CheckCommand(PersonNricContainsKeywordsPredicate predicate, String password, Mode mode) {
        requireAllNonNull(predicate, password, mode);
        this.predicate = predicate;
        this.password = password;
        this.mode = mode;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.size() == 0){
            throw new CommandException(MESSAGE_NOT_FOUND);
        }

        Person personToEdit = lastShownList.get(0);
        if (personToEdit.getMode().equals(mode)){
            throw new CommandException(String.format(MESSAGE_DUPLICATE, mode));
        }

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getNric(), personToEdit.getPassword(),
            personToEdit.getPhone(), personToEdit.getEmail(), personToEdit.getDepartment(), personToEdit.getPriorityLevel(),
            personToEdit.getAddress(), mode, personToEdit.getTags(), personToEdit.getSchedule());

        model.updatePerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(predicate);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_SUCCESS, mode));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckCommand)) {
            return false;
        }

        // state check
        CheckCommand e = (CheckCommand) other;
        return predicate.equals(e.predicate)
                && password.equals(e.password)
                && mode.equals(e.mode);
    }
}
