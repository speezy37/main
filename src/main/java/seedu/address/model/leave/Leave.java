package seedu.address.model.leave;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.prioritylevel.PriorityLevel;

//@@author Hafizuddin-NUS
/**
 * Represents a Leave in the leave list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Leave {

    private final Date date;
    private final EmployeeId employeeId;
    private final Approval approval;
    private final PriorityLevel priorityLevel;

    /**
     * Every field must be present and not null.
     */
    public Leave(EmployeeId employeeId, Date date, Approval approval, PriorityLevel priorityLevel) {
        requireAllNonNull(employeeId, date, approval);
        this.employeeId = employeeId;
        this.date = date;
        this.approval = approval;
        this.priorityLevel = priorityLevel;
    }

    public Date getDate() {
        return date;
    }

    public EmployeeId getEmployeeId() {
        return employeeId;
    }

    public Approval getApproval() {
        return approval;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, employeeId, approval);
    }

    /**
     * Returns true if both leave of the same employeeId have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two leaves.
     */

    public boolean isSameRequest(Leave otherRequest) {
        if (otherRequest == this) {
            return true;
        }

        return otherRequest != null
                && otherRequest.getEmployeeId().equals(getEmployeeId())
                && (otherRequest.getDate().equals(getDate()));
    }

    /**
     * Returns true if both leaves have the same identity and data fields.
     * This defines a stronger notion of equality between two leaves.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Leave)) {
            return false;
        }

        Leave otherPerson = (Leave) other;
        return otherPerson.getEmployeeId().equals(getEmployeeId())
                && otherPerson.getDate().equals(getDate())
                && otherPerson.getApproval().equals(getApproval());
    }

    /**
     * Returns the appended string of the person's PUBLIC particulars (i.e.: Without password).
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEmployeeId())
                .append(" Date of Application: ")
                .append(getDate())
                .append(" Approval: ")
                .append(getApproval());
        return builder.toString();
    }


}
