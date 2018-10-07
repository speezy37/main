package seedu.address.model.prioritylevel;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.prioritylevel.PriorityLevelEnum.BASIC;
import static seedu.address.model.prioritylevel.PriorityLevelEnum.IT_UNIT;

/**
 * Represents the priority level access given to the person.
 * The lower the number, the higher the priority.
 * Refer to {@code PriorityLevelEnum} for more details.
 */
public class PriorityLevel {
    public static final String MESSAGE_PRIORITY_CONSTRAINTS = "Priority level should be between "
            + IT_UNIT + " (" + IT_UNIT.getPriorityLevelCode() + " - highest) and "
            + BASIC + " (" + BASIC.getPriorityLevelCode() + " - lowest).";

    public final int priorityLevelCode;

    public PriorityLevel(int priorityLevelCode) {
        checkArgument(PriorityLevelEnum.isValidPriorityLevel(priorityLevelCode), MESSAGE_PRIORITY_CONSTRAINTS);
        this.priorityLevelCode = priorityLevelCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityLevel // instanceof handles nulls
                && priorityLevelCode == ((PriorityLevel) other).priorityLevelCode); // state check
    }

    @Override
    public String toString() {
        return PriorityLevelEnum.values()[priorityLevelCode].toString();
    }
}
