package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {

    @Rule
    public ExpectedException error = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void emptySortField_throwsNullPointerEx() {
        error.expect(NullPointerException.class);
        new SortCommand("name", null);
    }

    @Test
    public void emptySortOrder_throwsNullPointerEx() {
        error.expect(NullPointerException.class);
        new SortCommand(null, "asc");
    }

    @Test
    public void sortByName_success() throws Exception {
        SortCommand command = prepareCommand("name", "asc");
        String expected = String.format(MESSAGE_SUCCESS, "name", "asc");
        assertCommandSuccess(command, model, commandHistory, expected, model);
    }

    @Test
    public void sortByNameDesc_success() throws Exception {
        SortCommand command = prepareCommand("name", "desc");
        String expected = String.format(MESSAGE_SUCCESS, "name", "desc");
        assertCommandSuccess(command, model, commandHistory, expected, model);
    }

    /**
     * Returns a {@code sortCommand} with the parameters {@code field and @code order}.
     */
    private SortCommand prepareCommand(String field, String order) {
        SortCommand sortCommand = new SortCommand(field, order);
        return sortCommand;
    }
}
