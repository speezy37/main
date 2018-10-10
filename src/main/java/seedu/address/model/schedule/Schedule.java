package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Schedule {
    public static final String MESSAGE_SCHEDULE_CONSTRAINTS =
            "Schedule should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String SCHEDULE_VALIDATION_REGEX = "[\\p{Alnum}]*";

    //public final String value;

    private TimeStart timeStart;
    private TimeEnd timeEnd;
    private Venue venue;

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
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nSchedule: { START: ")
                .append(getTimeStart())
                .append(", End: ")
                .append(getTimeEnd())
                .append(", Venue: ")
                .append(getVenue())
                .append(" }");
        return builder.toString();
    }
}
