package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;

public class SetScheduleCommandTest {
    @Test
    public void equals() {
        final SetScheduleCommand standardCommand = new SetScheduleCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
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
}
