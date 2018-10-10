package seedu.address.model.person;

import org.junit.Test;

import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.testutil.Assert;

public class PrioritylevelTest {

    @Test
    public void constructor_invalidArgument_throwsIllegalValueException() {
        //Priority levels out of range
        Assert.assertThrows(IllegalArgumentException.class, () -> new PriorityLevel(999));
        Assert.assertThrows(IllegalArgumentException.class, () -> new PriorityLevel(-20));
    }

}
