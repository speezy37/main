package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.Schedule;

import javax.xml.bind.annotation.XmlValue;

public class XmlAdaptedSchedule {

    @XmlValue
    private String schedule;

    /**
     * Constructs an XmlAdaptedTag.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedSchedule() {}

    /**
     * Constructs a {@code XmlAdaptedTag} with the given {@code tagName}.
     */
    public XmlAdaptedSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * Converts a given Tag into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created
     */
    public XmlAdaptedSchedule(Schedule source) {
        schedule = source.value;
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the model's Tag object.
     *
     * @throws seedu.address.commons.exceptions.IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Schedule toModelType() throws IllegalValueException {
        if (!Schedule.isValidSchedule(schedule)) {
            throw new IllegalValueException(Schedule.MESSAGE_SCHEDULE_CONSTRAINTS);
        }
        return new Schedule(schedule);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedSchedule)) {
            return false;
        }

        return schedule.equals(((XmlAdaptedSchedule) other).schedule);
    }
}
