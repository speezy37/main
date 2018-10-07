package seedu.address.model.prioritylevel;

/**
 * Priority level by descending order.
 * I.T. Unit highest priority, followed by administrator, followed by manager...
 */
public enum PriorityLevelEnum {
    IT_UNIT(0),
    ADMINISTRATOR(1),
    MANAGER(2),
    BASIC(3);

    private final int priorityLevelCode;

    PriorityLevelEnum(int priorityLevelCode) {
        this.priorityLevelCode = priorityLevelCode;
    }

    public int getPriorityLevelCode() {
        return this.priorityLevelCode;
    }

    /**
     * Returns true if the priorityLevel value is within the defined enum range as stated above.
     */
    public static boolean isValidPriorityLevel(int test) {
        if (test >= IT_UNIT.getPriorityLevelCode() && test <= BASIC.getPriorityLevelCode()) {
            return true;
        }
        return false;
    }
}
