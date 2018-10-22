package seedu.address.model;

import java.util.HashMap;
import java.util.List;

import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.password.Password;
import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.model.prioritylevel.PriorityLevelEnum;

//@@author jylee-git
/**
 * Stores the {@Code Nric} and {@code PriorityLevel} of the user who's logged in to the application.
 * Also manages the logging in and out of the current session.
 * This class is singleton class.
 */
public class SessionManager {
    public static final String NOT_LOGGED_IN = "This operation requires the user to be logged in!";

    private static SessionManager singleInstance = null;

    private static Nric loggedInNric = null;
    private static PriorityLevel loggedInPriorityLevel = null;

    private HashMap<Nric, Person> allPersonsHashMap;

    private SessionManager(Model model) {
        allPersonsHashMap = new HashMap<>();
        List<Person> allPersonsList = model.getAddressBook().getPersonList();
        for (Person currPerson : allPersonsList) {
            allPersonsHashMap.put(currPerson.getNric(), currPerson);
        }
    }

    /**
     * Returns the one and only initialized instance of the Object
     */
    public static SessionManager getInstance(Model model) {
        if (singleInstance == null) {
            singleInstance = new SessionManager(model);
        }
        return singleInstance;
    }

    //================================ LOGIN/LOGOUT =================================================================

    /**
     * Returns true if user is logged in to the application.
     */
    public boolean isLoggedIn() {
        return loggedInNric != null;
    }

    /**
     * Attempts to log into the session using a user input NRIC and Password.
     * @throws CommandException if the login parameters are incorrect.
     */
    public void loginToSession(Nric loginWithThisNric, Password loginWithThisPassword) throws CommandException {
        if (isLoggedIn()) {
            throw new CommandException(LoginCommand.ALREADY_LOGGED_IN);
        }
        if (!isLoginCredentialsValid(loginWithThisNric, loginWithThisPassword)) {
            throw new CommandException(LoginCommand.INVALID_LOGIN_CREDENTIALS);
        } else {
            loggedInNric = loginWithThisNric;
            loggedInPriorityLevel = allPersonsHashMap.get(loginWithThisNric).getPriorityLevel();
        }
    }

    private boolean isLoginCredentialsValid(Nric loginNric, Password loginPassword) {
        //Returns false if wrong NRIC and/or Password. Else return true.
        return (allPersonsHashMap.containsKey(loginNric)
                && allPersonsHashMap.get(loginNric).getPassword().equals(loginPassword));
    }

    /**
     * Logs out of the current session.
     */
    public void logOutSession() {
        loggedInNric = null;
        loggedInPriorityLevel = null;
    }

    //================================================ GETTING LOGGED IN DETAILS ====================================

    /**
     * Returns the {@code Nric} of the logged in person.
     * @throws CommandException if the app's not logged in.
     */
    public Nric getLoggedInSessionNric() throws CommandException {
        if (!isLoggedIn()) {
            throw new CommandException(NOT_LOGGED_IN);
        }
        return loggedInNric;
    }

    /**
     * Returns the {@code Person} object whose NRIC matches the one that's currently logged in.
     */
    public Person getLoggedInPersonDetails() throws CommandException {
        if (!isLoggedIn()) {
            throw new CommandException(NOT_LOGGED_IN);
        }
        return allPersonsHashMap.get(loggedInNric);
    }

    //================================== PRIORITY LEVEL CONCERNS ==================================================
    /**
     * Returns true if current session has at least the required priority level for the operation.
     * @throws CommandException if user's not logged in.
     */
    public boolean hasSufficientPriorityLevelForThisSession(
            PriorityLevelEnum minimumPriorityLevel) throws CommandException {
        if (!isLoggedIn()) {
            throw new CommandException(NOT_LOGGED_IN);
        }
        return PriorityLevel.isPriorityLevelAtLeastOf(loggedInPriorityLevel, minimumPriorityLevel);
    }


    //================================= FOR TEST USE ONLY =========================================================
    /**
     * For test use only. Logs in with a defined priority level, which may be necessary for operations
     * requiring admin rights.
     */
    protected static void forceLoginWith(String nric, int priorityLevel) {
        loggedInNric = new Nric(nric);
        loggedInPriorityLevel = new PriorityLevel(priorityLevel);
    }
}
