package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPROVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITYLEVEL;

import seedu.address.logic.commands.AddLeaveCommand;
import seedu.address.logic.commands.EditLeaveCommand.EditLeaveDescriptor;
import seedu.address.model.leave.Leave;

//@@author Hafizuddin-NUS
/**
 * A utility class for Leave.
 */
public class LeaveUtil {
    /**
     * Returns an add leave command string for adding the {@code leave}.
     */
    public static String getAddLeaveCommand(Leave leave) {
        return AddLeaveCommand.COMMAND_WORD + " " + getLeaveDetails(leave);
    }

    /**
     * Returns the part of command string for the given {@code leave}'s details.
     */
    public static String getLeaveDetails(Leave leave) {
        StringBuilder sb = new StringBuilder();
        //sb.append(PREFIX_NRIC + leave.getEmployeeId().nric + " ");
        sb.append(PREFIX_DATE + leave.getDate().date);
        //sb.append(PREFIX_APPROVAL + leave.getApproval().status + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditLeaveDescriptor}'s details.
     */
    public static String getEditLeaveDescriptorDetails(EditLeaveDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        //descriptor.getNric().ifPresent(nric-> sb.append(PREFIX_NRIC).append(nric.nric).append(" "));
        //descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.date).append(" "));
        //descriptor.getApproval().ifPresent(approval -> sb.append(PREFIX_APPROVAL).append(approval.status).append(" "));
        //descriptor.getPriorityLevel().ifPresent(priorityLevel -> sb.append(PREFIX_PRIORITYLEVEL)
                //.append(priorityLevel.priorityLevelCode).append(" "));

        return sb.toString();
    }

}


