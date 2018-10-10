package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TimeStart {
    public static final String MESSAGE_TIME_START_CONSTRAINTS =
                "End Time should only contain alphanumeric characters and spaces, and it should not be blank";

        /*
         * The first character of the address must not be a whitespace,
         * otherwise " " (a blank string) becomes a valid input.
         */
        public static final String TIME_START_VALIDATION_REGEX = "\\d{4,}";

        public final String value;

        /**
         * Constructs a {@code Name}.
         *
         * @param timeStart A valid department name.
         */
        public TimeStart(String timeStart) {
            requireNonNull(timeStart);
            checkArgument(isValidTimeEnd(timeStart), MESSAGE_TIME_START_CONSTRAINTS);
            value = timeStart;
        }

        /**
         * Returns true if a given string is a valid department name.
         */
        public static boolean isValidTimeEnd(String test) {
            return test.matches(TIME_START_VALIDATION_REGEX);
        }


        @Override
        public String toString() {
            return value;
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof TimeStart // instanceof handles nulls
                    && value.equals(((TimeStart) other).value)); // state check
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

}
