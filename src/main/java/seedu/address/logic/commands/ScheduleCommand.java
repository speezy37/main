package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.model.prioritylevel.PriorityLevelEnum;
import seedu.address.session.SessionManager;

/**
 * List schedule of a person in the address book to the user.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the schedule of the person identified"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: "
            + COMMAND_WORD + " 1";

    public static final String MESSAGE_SCHEDULE_SUCCESS = "Listed Schedule:";
    public static final String MESSAGE_SCHEDULE_FAIL = "Schedule Command Failed.";

    private final Index index;

    public ScheduleCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        SessionManager sessionManager = SessionManager.getInstance(model);

        if (!sessionManager.isLoggedIn()) {
            throw new CommandException(SessionManager.NOT_LOGGED_IN);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPerson = lastShownList.get(index.getZeroBased());
        /**
         * Throws exception if user does not have the required access level and is not the logged in person
         */
        if (!sessionManager.hasSufficientPriorityLevelForThisSession(PriorityLevelEnum.ADMINISTRATOR)
                && targetPerson.getNric() != sessionManager.getLoggedInSessionNric()) {
            throw new CommandException(String.format(PriorityLevel.INSUFFICIENT_PRIORITY_LEVEL,
                    PriorityLevelEnum.ADMINISTRATOR));
        }

        String schedule = targetPerson.getSchedule().toString();

        return new CommandResult(MESSAGE_SCHEDULE_SUCCESS + " " + schedule);
    }
}
