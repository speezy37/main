package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Venue of a Schedule in address book.
 */
public class Venue {
    public static final String MESSAGE_VENUE_CONSTRAINTS =
            "Venue should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the venue must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VENUE_VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param venue A valid venue.
     */
    public Venue(String venue) {
        requireNonNull(venue);
        checkArgument(isValidVenue(venue), MESSAGE_VENUE_CONSTRAINTS);
        value = venue;
    }

    /**
     * Returns true if a given string is a venue.
     */
    public static boolean isValidVenue(String test) {
        return test.matches(VENUE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Venue // instanceof handles nulls
                && value.equals(((Venue) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
