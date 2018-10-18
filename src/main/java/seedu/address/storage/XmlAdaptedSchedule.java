package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.TimeEnd;
import seedu.address.model.schedule.TimeStart;
import seedu.address.model.schedule.Venue;

/**
 * JAXB-friendly version of the Schedule.
 */
public class XmlAdaptedSchedule {

    public static final String MISSING_SCHEDULE_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";

    @XmlElement(required = true)
    private String venue;
    @XmlElement(required = true)
    private String startTime;
    @XmlElement(required = true)
    private String endTime;

    /**
     * Constructs an XmlAdaptedTag.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedSchedule() {}

    /**
     * Constructs a {@code XmlAdaptedSchedule} with the given {@code schedule}.
     */
    public XmlAdaptedSchedule(String startTime, String endTime, String venue) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
    }

    /**
     * Converts a given Schedule into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created
     */
    public XmlAdaptedSchedule(Schedule source) {
        venue = source.getVenue().value;
        endTime = source.getTimeEnd().value;
        startTime = source.getTimeStart().value;
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the model's Schedule object.
     *
     */
    public Schedule toModelType() throws IllegalValueException {
        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_SCHEDULE_FIELD_MESSAGE_FORMAT,
                    TimeStart.class.getSimpleName()));
        }
        if (!TimeStart.isValidTimeStart(startTime)) {
            throw new IllegalValueException(TimeStart.MESSAGE_TIME_START_CONSTRAINTS);
        }
        final TimeStart timeStart = new TimeStart(this.startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_SCHEDULE_FIELD_MESSAGE_FORMAT,
                    TimeEnd.class.getSimpleName()));
        }
        if (!TimeEnd.isValidTimeEnd(endTime)) {
            throw new IllegalValueException(TimeEnd.MESSAGE_TIME_END_CONSTRAINTS);
        }
        final TimeEnd timeEnd = new TimeEnd(this.endTime);

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_SCHEDULE_FIELD_MESSAGE_FORMAT,
                    Venue.class.getSimpleName()));
        }
        if (!Venue.isValidVenue(venue)) {
            throw new IllegalValueException(Venue.MESSAGE_VENUE_CONSTRAINTS);
        }
        final Venue venue = new Venue(this.venue);

        return new Schedule(timeStart, timeEnd, venue);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedSchedule)) {
            return false;
        }

        return venue.equals(((XmlAdaptedSchedule) other).venue);
    }
}
