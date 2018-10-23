package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.leave.Leave;
import seedu.address.session.SessionManager;

/**
 * Adds a leave request to the Leave List.
 */
public class AddLeaveCommand extends Command {

    public static final String COMMAND_WORD = "leave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Request leave. "
            + "Parameters: "
            + PREFIX_DATE + "DATE (DD/MM/YYYY) ";


    public static final String MESSAGE_SUCCESS = "Leave application requested.";
    public static final String MESSAGE_DUPLICATE_LEAVE = "This request already exist in the database.";
    public static final String STATUS_NOT_LOGGED_IN = "Not login yet.";
    private static boolean isLogin = true;

    private final Leave toAdd;

    public AddLeaveCommand(Leave leave) {
        requireNonNull(leave);
        toAdd = leave;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        SessionManager sessionManager = SessionManager.getInstance(model);

        if (isLogin && !sessionManager.isLoggedIn()) {
            throw new CommandException(STATUS_NOT_LOGGED_IN);
        }
        Leave personLeaveToAdd = new Leave(sessionManager.getLoggedInSessionNric(),
                toAdd.getDate(), toAdd.getApproval());

        if (model.hasLeave(personLeaveToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LEAVE);
        } else {
            model.addLeave(personLeaveToAdd);
            model.commitLeaveList();
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }
    }
}
