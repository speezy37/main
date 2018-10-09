package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * List schedule of a person in the address book to the user.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the schedule of the person identified"
            + "Parameters: INDEX (must be a positive integer)";

    public static final String MESSAGE_SCHEDULE_SUCCESS = "Listed Schedule:";
    public static final String MESSAGE_SCHEDULE_FAIL = "Person not found in address book.";

    private final Index index;

    public ScheduleCommand(Index index){
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        requireNonNull(history);

        String schedule = model.getAddressBook().getPersonList().get(index.getZeroBased())
                .getSchedule().value;

        return new CommandResult(MESSAGE_SCHEDULE_SUCCESS + "\n" + schedule);
    }
}
