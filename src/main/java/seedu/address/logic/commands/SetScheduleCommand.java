package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.SessionManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Mode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.model.prioritylevel.PriorityLevelEnum;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tag.Tag;

/**
 * Sets schedule of a person in the address book to the user.
 */
public class SetScheduleCommand extends Command {

    public static final String COMMAND_WORD = "setschedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the schedule of the person identified"
            + "Parameters: INDEX (must be a positive integer)"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_TIME_START + " 1000 "
            + PREFIX_TIME_END + " 1600 "
            + PREFIX_VENUE + " Toilet\n";

    public static final String MESSAGE_SCHEDULE_SUCCESS = "Set Schedule Successful";
    public static final String MESSAGE_SCHEDULE_FAIL = "Set Schedule Failed.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    public SetScheduleCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = editPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!SessionManager.isLoggedIn()) {
            throw new CommandException(SessionManager.NOT_LOGGED_IN);
        }
        /**
         * Throws exception if user does not have the required access level.
         */
        if (!SessionManager.hasSufficientPriorityLevelForThisSession(PriorityLevelEnum.ADMINISTRATOR)) {
            throw new CommandException(String.format(PriorityLevel.INSUFFICIENT_PRIORITY_LEVEL,
                    PriorityLevelEnum.ADMINISTRATOR));
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        model.updatePerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SCHEDULE_SUCCESS, editedPerson));
    }

    /**
     * Creates an edited Person Object with given edit person descriptor
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Department updatedDepartment = editPersonDescriptor.getDepartment().orElse(personToEdit.getDepartment());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Mode updatedMode = personToEdit.getMode(); // edit command does not allow editing modes
        Set<Schedule> updatedSchedule = editPersonDescriptor.getSchedule().orElse(personToEdit.getSchedule());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, personToEdit.getNric(),
                personToEdit.getPassword(), updatedPhone, updatedEmail, updatedDepartment,
                personToEdit.getPriorityLevel(), updatedAddress, updatedMode, updatedTags, updatedSchedule);
    }
}
