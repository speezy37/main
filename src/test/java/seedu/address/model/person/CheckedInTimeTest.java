package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CheckedInTimeTest {
    @Test
    public void equals() {
        CheckedInTime checkedInTime = new CheckedInTime("12:00:00");
        // same object -> returns true
        assertTrue(checkedInTime.equals(checkedInTime));
        // same values -> returns true
        CheckedInTime checkedInTimeCopy = new CheckedInTime(checkedInTime.value);
        assertTrue(checkedInTime.equals(checkedInTimeCopy));
        // different types -> returns false
        assertFalse(checkedInTime.equals(1));
        // null -> returns false
        assertFalse(checkedInTime.equals(null));
        // different checkedInTime -> returns false
        CheckedInTime differentCheckedInTime = new CheckedInTime("10:00:00");
        assertFalse(checkedInTime.equals(differentCheckedInTime));
    }
}
