package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.password.Password;
import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tag.Tag;


/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Nric nric;
    private final Phone phone;
    private final Email email;
    private final Department department;
    private final Password password;
    private final PriorityLevel priorityLevel;

    // Data fields
    private final Address address;
    private final Mode mode;
    private final Schedule schedule;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Nric nric, Password password, Phone phone, Email email, Department department,
                  PriorityLevel priorityLevel, Address address, Mode mode, Set<Tag> tags, Schedule schedule) {
        requireAllNonNull(name, nric, password, phone, email, department, priorityLevel, mode, address, tags);
        this.name = name;
        this.nric = nric;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.priorityLevel = priorityLevel;
        this.address = address;
        this.mode = mode;
        this.tags.addAll(tags);
        this.schedule = schedule;
    }

    public Name getName() {
        return name;
    }

    public Nric getNric() {
        return nric;
    }

    public Password getPassword() {
        return password;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Department getDepartment() {
        return department;
    }

    public Address getAddress() {
        return address;
    }

    public Mode getMode() {
        return mode;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Schedule getSchedule() {
        return schedule;
    }
    /**
     * Returns true if both persons have the same NRIC number, which is a unique identifier.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == null) {
            return false;
        } else {
            return (otherPerson.getNric()).equals((this.getNric()));
        }
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getNric().equals(getNric())
                && otherPerson.getPassword().equals(getPassword())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getPriorityLevel().equals(getPriorityLevel())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, nric, password, phone, email, address, priorityLevel, tags);
    }

    /**
     * Returns the appended string of the person's PUBLIC particulars (i.e.: Without password).
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\n NRIC: ")
                .append(getNric())
                .append("\n Phone: ")
                .append(getPhone())
                .append("\n Email: ")
                .append(getEmail())
                .append("\n Department: ")
                .append(getDepartment())
                .append("\n Priority Level: ")
                .append(getPriorityLevel())
                .append("\n Address: ")
                .append(getAddress())
                .append("\n Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
