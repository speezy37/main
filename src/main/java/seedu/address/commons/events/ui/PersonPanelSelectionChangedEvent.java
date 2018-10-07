package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.leave.Leave;
import seedu.address.model.person.Person;

/**
 * Represents a selection change in the Person List Panel
 */
public class PersonPanelSelectionChangedEvent extends BaseEvent {


    private Person newSelection;
    private Leave newSelection1;

    public PersonPanelSelectionChangedEvent(Person newSelection) {
        this.newSelection = newSelection;
    }

    public PersonPanelSelectionChangedEvent(Leave newSelection) {
        this.newSelection1 = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Person getNewSelection() {
        return newSelection;
    }
}
