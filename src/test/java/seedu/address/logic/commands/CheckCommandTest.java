package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CheckCommand.MESSAGE_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Mode;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class CheckCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Mode mode = new Mode("in");

        assertCommandFailure(new CheckCommand(VALID_NRIC_AMY, VALID_PASSWORD_AMY, mode), model, new CommandHistory(),
                String.format(MESSAGE_ARGUMENTS, VALID_NRIC_AMY, VALID_PASSWORD_AMY, mode));
    }

    @Test
    public void equals() {
        final CheckCommand standardCommand = new CheckCommand(VALID_NRIC_AMY, VALID_PASSWORD_AMY,
            new Mode(VALID_MODE_AMY));

        // same values -> returns true
        CheckCommand commandWithSameValues = new CheckCommand(VALID_NRIC_AMY, VALID_PASSWORD_AMY,
            new Mode(VALID_MODE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different nric -> returns false
        assertFalse(standardCommand.equals(new CheckCommand(VALID_NRIC_BOB, VALID_PASSWORD_AMY,
            new Mode(VALID_MODE_AMY))));

        // different password -> returns false
        assertFalse(standardCommand.equals(new CheckCommand(VALID_NRIC_AMY, VALID_PASSWORD_BOB,
            new Mode(VALID_MODE_AMY))));

        // different mode -> returns false
        assertFalse(standardCommand.equals(new CheckCommand(VALID_NRIC_AMY, VALID_PASSWORD_AMY,
            new Mode(VALID_MODE_BOB))));
    }
}
