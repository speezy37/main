package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class Schedule {
    public static final String MESSAGE_SCHEDULE_CONSTRAINTS =
            "Schedule should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String SCHEDULE_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    private TimeStart timeStart;
    private TimeEnd timeEnd;
    private Venue venue;


    public Schedule(String schedule) {
        requireNonNull(schedule);
        checkArgument(isValidSchedule(schedule), MESSAGE_SCHEDULE_CONSTRAINTS);
        value = schedule;
    }

    /*
    public Schedule() {

    }

    public Schedule(TimeStart timeStart, TimeEnd timeEnd, Venue venue) {
        requireAllNonNull(timeStart, timeEnd, venue);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.venue = venue;
    }

    public TimeStart getTimeStart() {
        return timeStart;
    }

    public TimeEnd getTimeEnd() {
        return timeEnd;
    }

    public Venue getVenue() {
        return venue;
    }
    */

    /**
     * Returns true if a given string is a valid schedule.
     */

    /*
    public static boolean isValidSchedule(String test) {
        return test.matches(SCHEDULE_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Schedule: { START: ")
                .append(getTimeStart())
                .append(", End: ")
                .append(getTimeEnd())
                .append(", Venue: ")
                .append(getVenue());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        return otherSchedule.getTimeStart().equals(getTimeStart())
                && otherSchedule.getTimeEnd().equals(getTimeEnd())
                && otherSchedule.getVenue().equals(getVenue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStart, timeEnd, venue);
    }*/

    public static boolean isValidSchedule(String test) {
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
