package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ModeTest {
    @Test
    public void equals() {
        Mode mode = new Mode("in");

        // same object -> returns true
        assertTrue(mode.equals(mode));

        // same values -> returns true
        Mode modeCopy = new Mode(mode.value);
        assertTrue(mode.equals(modeCopy));

        // different types -> returns false
        assertFalse(mode.equals(1));

        // null -> returns false
        assertFalse(mode.equals(null));

        // different remark -> returns false
        Mode differentMode = new Mode("out");
        assertFalse(mode.equals(differentMode));
    }
}
