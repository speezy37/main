package seedu.address.testutil;

import seedu.address.model.LeaveList;
import seedu.address.model.leave.Leave;

public class LeaveListBuilder {
    private LeaveList leaveList;

    public LeaveListBuilder() {
        leaveList = new LeaveList();
    }

    public LeaveListBuilder(LeaveList leaveList) {
        this.leaveList = leaveList;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public LeaveListBuilder withLeave(Leave leave) {
        leaveList.addRequest(leave);
        return this;
    }

    public LeaveList build() {
        return leaveList;
    }
}
