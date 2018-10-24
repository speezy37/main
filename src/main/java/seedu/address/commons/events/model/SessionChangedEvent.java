package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.person.Person;

/**
 * Indicates that a login/logout event has occurred.
 */
public class SessionChangedEvent extends BaseEvent {

    String toFeedback;

    public SessionChangedEvent() {
        toFeedback = "Not logged in";
    }

    public SessionChangedEvent(Person loggedInPerson) {
        toFeedback = "Logged in as: " + loggedInPerson.getNric();
    }

    @Override
    public String toString() {
        return toFeedback;
    }
}
