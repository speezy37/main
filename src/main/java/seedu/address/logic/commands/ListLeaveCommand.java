package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyLeaveList;

import static java.util.Objects.requireNonNull;

public class ListLeaveCommand extends Command {

    public static final String COMMAND_WORD = "listleave";

    public static final String MESSAGE_SUCCESS = "Listed all leaves";

    //public ReadOnlyLeaveList list;

    //public String nric;


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredLeaveList(Model.PREDICATE_SHOW_ALL_LEAVES);
        //list.getRequestList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
