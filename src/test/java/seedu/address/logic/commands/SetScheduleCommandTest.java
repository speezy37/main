package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.prioritylevel.PriorityLevelEnum;
import systemtests.SessionHelper;

public class SetScheduleCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();
    private Index index = Index.fromOneBased(1);

    @Before
    public void setUp() {
        SessionHelper.forceLoginWithPriorityLevelOf(PriorityLevelEnum.ADMINISTRATOR.getPriorityLevelCode());
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_nullValues_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new SetScheduleCommand(null, null);
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new SetScheduleCommand(index, null);
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        thrown.expect(NullPointerException.class);
        new SetScheduleCommand(null, editPersonDescriptor);
    }

    @Test
    public void execute_emptyModel_failure() {
        // Empty Address Book
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        assertCommandFailure(new SetScheduleCommand(index, editPersonDescriptor),
                emptyModel, commandHistory, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndex_failure() {
        // AddressBook with 7 people
        Model emptyModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        // Index 8 (Person does not exist) -> failure
        assertCommandFailure(new SetScheduleCommand(Index.fromOneBased(8), editPersonDescriptor),
                emptyModel, commandHistory, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final SetScheduleCommand standardCommand = new SetScheduleCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values
        final EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        SetScheduleCommand commandWithSameValues = new SetScheduleCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertNotEquals(standardCommand, commandWithSameValues);

        // same object
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new SetScheduleCommand(INDEX_SECOND_PERSON, DESC_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new SetScheduleCommand(INDEX_FIRST_PERSON, DESC_BOB));
    }

    @After
    public void tearDown() {
        SessionHelper.logoutOfSession();
    }
}
