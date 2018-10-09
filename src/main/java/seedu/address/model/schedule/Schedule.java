package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import seedu.address.model.person.Name;

public class Schedule {
    public static final String MESSAGE_SCHEDULE_CONSTRAINTS =
            "Schedule should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String SCHEDULE_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Schedule}.
     *
     * @param schedule A valid schedule.
     */
    public Schedule(String schedule) {
        requireNonNull(schedule);
        checkArgument(isValidSchdule(schedule), MESSAGE_SCHEDULE_CONSTRAINTS);
        value = schedule;
    }

    /**
     * Returns true if a given string is a valid schedule.
     */
    public static boolean isValidSchdule(String test) {
        return test.matches(SCHEDULE_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Schedule // instanceof handles nulls
                && value.equals(((Schedule) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
