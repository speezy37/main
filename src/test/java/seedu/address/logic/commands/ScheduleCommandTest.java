package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.model.prioritylevel.PriorityLevelEnum;
import systemtests.SessionHelper;

public class ScheduleCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        SessionHelper.forceLoginWithPriorityLevelOf(PriorityLevelEnum.ADMINISTRATOR.getPriorityLevelCode());
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ScheduleCommand(null);
    }

    @Test
    public void equals() {
        ScheduleCommand cleanerScheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON);
        ScheduleCommand clerkScheduleCommand = new ScheduleCommand(INDEX_SECOND_PERSON);

        // same object
        assertEquals(cleanerScheduleCommand, cleanerScheduleCommand);

        // same values
        ScheduleCommand cleanerScheduleCommandCopy = new ScheduleCommand(INDEX_FIRST_PERSON);
        assertNotEquals(cleanerScheduleCommand, cleanerScheduleCommandCopy);

        // different types
        assertNotEquals(cleanerScheduleCommand, 1);

        // null
        assertNotEquals(cleanerScheduleCommand, null);

        // different index
        assertNotEquals(cleanerScheduleCommand, clerkScheduleCommand);
    }

    @After
    public void tearDown() {
        SessionHelper.logoutOfSession();
    }

}
