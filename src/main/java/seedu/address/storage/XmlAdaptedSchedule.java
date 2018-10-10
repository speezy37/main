package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.TimeEnd;
import seedu.address.model.schedule.TimeStart;
import seedu.address.model.schedule.Venue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "schedule")
public class XmlAdaptedSchedule {

    @XmlElement
    private String venue;
    @XmlElement
    private String startTime;
    @XmlElement
    private String endTime;

    /**
     * Constructs an XmlAdaptedTag.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedSchedule() {}

    /**
     * Constructs a {@code XmlAdaptedSchedule} with the given {@code schedule}.
     */
    public XmlAdaptedSchedule(String schedule, String endTime, String startTime) {
        this.venue = schedule;
        this.endTime = endTime;
        this.startTime = startTime;
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
     * Converts this jaxb-friendly adapted tag object into the model's Tag object.
     *
     * @throws seedu.address.commons.exceptions.IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Schedule toModelType() throws IllegalValueException {
        TimeStart timeStart = new TimeStart(this.startTime);
        TimeEnd timeEnd = new TimeEnd(this.endTime);
        Venue venue = new Venue(this.venue);

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
