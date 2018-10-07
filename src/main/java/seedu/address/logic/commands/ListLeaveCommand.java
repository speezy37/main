package seedu.address.logic.commands;


import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Lists all leave request in the leave list to the user.
 */
public class ListLeaveCommand extends Command {
    public static final String COMMAND_WORD = "listleave";

    public static final String MESSAGE_SUCCESS = "Listed all leave requests.";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_LEAVES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
