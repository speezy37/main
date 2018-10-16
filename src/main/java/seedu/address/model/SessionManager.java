package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.model.prioritylevel.PriorityLevelEnum;

//@@author jylee-git
/**
 * Stores the {@Code Nric} and {@code PriorityLevel} of the user who's logged in to the application.
 * Also manages the logging in and out of the current session.
 */
public class SessionManager {
    public static final String NOT_LOGGED_IN = "This operation requires the user to be logged in!";

    private static Nric loggedInNric = null;
    private static String employeeNric = null;
    private static PriorityLevel loggedInPriorityLevel = null;

    /**
     * Stores the {@code Nric} of the successfully logged in person into the session.
     * PRE-CONDITION: Nric must be valid.
     * @param logInWithThisNric
     */
    public static void loginToSession(Model model, Nric logInWithThisNric) {
        requireNonNull(logInWithThisNric);
        loggedInNric = logInWithThisNric;
        loggedInPriorityLevel = getLoggedInPersonDetails(model).getPriorityLevel();
    }


    /**
     * Logs out of the current session.
     */
    public static void logOutSession() {
        loggedInNric = null;
        loggedInPriorityLevel = null;
    }

    /**
     * Returns the {@code Nric} of the logged in person.
     * @throws CommandException if the app's not logged in.
     */
    public static Nric getLoggedInSessionNric() throws CommandException {
        if (!isLoggedIn()) {
            throw new CommandException(NOT_LOGGED_IN);
        }
        return loggedInNric;
    }
    public static String getLoggedInEmployeeNric() {
        employeeNric = loggedInNric.nric;
        return employeeNric;
    }

    /**
     * Reutrns true if user is logged in to the application.
     */
    public static boolean isLoggedIn() {
        return loggedInNric != null;
    }

    /**
     * Returns the {@code Person} of the person whose NRIC matches the one that's currently logged in.
     */
    public static Person getLoggedInPersonDetails(Model model) {
        List<Person> allPersonsList = model.getAddressBook().getPersonList();
        for (Person currPerson : allPersonsList) {
            if (currPerson.getNric() == loggedInNric) {
                return currPerson;
            }
        }
        return null;
    }

    /**
     * Returns true if current session has at least the required priority level for the operation.
     * @throws CommandException if user's not logged in.
     */
    public static boolean hasSufficientPriorityLevelForThisSession(
            PriorityLevelEnum minimumPriorityLevel) throws CommandException {
        if (!isLoggedIn()) {
            throw new CommandException(NOT_LOGGED_IN);
        }
        return PriorityLevel.isPriorityLevelAtLeastOf(loggedInPriorityLevel, minimumPriorityLevel);
    }

    /**
     * For test use only. Logs in with a defined priority level, which may be necessary for operations
     * requiring admin rights.
     */
    protected static void forceLoginWith(String nric, int priorityLevel) {
        loggedInNric = new Nric(nric);
        loggedInPriorityLevel = new PriorityLevel(priorityLevel);
    }
}
