package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Mode;
import seedu.address.model.person.Nric;
import seedu.address.model.person.password.Password;
import seedu.address.session.SessionManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class CheckCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private SessionManager sessionManager;

    @Before
    public void setUp() {
        sessionManager = SessionManager.getInstance(model);
    }

    @Test
    public void execute() throws CommandException {
        final Mode inMode = new Mode("in");
        sessionManager.destroy();

        //user not login
        assertCommandFailure(new CheckCommand(GEORGE.getNric(), GEORGE.getPassword(), inMode),
            model, new CommandHistory(),
            CheckCommand.MESSAGE_NOT_LOGIN);

        sessionManager = SessionManager.getInstance(model);
        sessionManager.loginToSession(GEORGE.getNric(), GEORGE.getPassword());

        //duplicate mode
        assertCommandFailure(new CheckCommand(GEORGE.getNric(), GEORGE.getPassword(), inMode),
            model, new CommandHistory(),
            String.format(CheckCommand.MESSAGE_DUPLICATE, inMode));
        //user not found (invalid NRIC)
        assertCommandFailure(new CheckCommand(new Nric("S1112222T"), GEORGE.getPassword(), inMode),
            model, new CommandHistory(),
            CheckCommand.MESSAGE_NOT_FOUND);
        //user not found (invalid password)
        assertCommandFailure(new CheckCommand(GEORGE.getNric(), new Password("AAAA1234"), inMode),
            model, new CommandHistory(),
            CheckCommand.MESSAGE_NOT_FOUND);
        //user not authorised
        assertCommandFailure(new CheckCommand(FIONA.getNric(), FIONA.getPassword(), inMode),
            model, new CommandHistory(),
            CheckCommand.MESSAGE_NOT_AUTHORISED);
    }

    @Test
    public void equals() {
        final CheckCommand standardCommand = new CheckCommand(new Nric(VALID_NRIC_AMY), new Password(VALID_PASSWORD_AMY),
            new Mode(VALID_MODE_AMY));

        // same values -> returns true
        CheckCommand commandWithSameValues = new CheckCommand(new Nric(VALID_NRIC_AMY), new Password(VALID_PASSWORD_AMY),
            new Mode(VALID_MODE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different nric -> returns false
        assertFalse(standardCommand.equals(new CheckCommand(new Nric(VALID_NRIC_BOB), new Password(VALID_PASSWORD_AMY),
            new Mode(VALID_MODE_AMY))));

        // different password -> returns false
        assertFalse(standardCommand.equals(new CheckCommand(new Nric(VALID_NRIC_AMY), new Password(VALID_PASSWORD_BOB),
            new Mode(VALID_MODE_BOB))));

        // different mode -> returns false
        assertFalse(standardCommand.equals(new CheckCommand(new Nric(VALID_NRIC_AMY), new Password(VALID_PASSWORD_AMY),
            new Mode(VALID_MODE_BOB))));
    }
}
