package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.Nric;

/**
 * Stores the NRIC of the user who's logged in to the applicaion.
 * Also manages the logging in and out of the current session.
 */
public class SessionManager {
    private static Nric loggedInNric = null;

    /**
     * Stores the NRIC of the successfully verified login into the session
     * PRE-CONDITION: NRIC must be valid.
     * @param logInWithThisNric
     */
    public static void loginToSession(Nric logInWithThisNric) {
        requireNonNull(logInWithThisNric);
        loggedInNric = logInWithThisNric;
    }

    public static void logOutSession() {
        loggedInNric = null;
    }

    public static Nric getLoggedInSessionNric() {
        requireNonNull(loggedInNric);
        return loggedInNric;
    }

    /**
     * Reutrns true if user is logged in to the application.
     */
    public static boolean isLoggedIn() {
        if (loggedInNric == null) {
            return false;
        }
        return true;
    }
}
