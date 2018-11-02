package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.CheckedInTime;
import seedu.address.model.person.Mode;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.password.Password;
import seedu.address.model.prioritylevel.PriorityLevelEnum;
import seedu.address.session.SessionManager;

//@@author pinjuen

/**
 * Check in or out to work.
 */
public class CheckCommand extends Command {
    public static final String COMMAND_WORD = "check";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks in/out to work. "
        + "\nParameters: "
        + PREFIX_NRIC + "NRIC "
        + PREFIX_PASSWORD + "PASSWORD "
        + PREFIX_MODE + "MODE"
        + "\nExample: " + COMMAND_WORD + " "
        + PREFIX_NRIC + "G1234567T "
        + PREFIX_PASSWORD + "HEllo12 "
        + PREFIX_MODE + "in ";
    public static final String MESSAGE_DUPLICATE = "User has already checked %1$s to work!";
    public static final String MESSAGE_NOT_FOUND = "User is not found!";
    public static final String MESSAGE_NOT_LOGIN = "User has not logged in yet!";
    public static final String MESSAGE_NOT_AUTHORISED = "User is not authorised to do so!";
    public static final String MESSAGE_CHECKED_IN = "Successfully checked in to work!\n"
        + "Date: %1$s Time: %2$s";
    public static final String MESSAGE_CHECKED_OUT = "Successfully checked out from work!\n"
        + "Date: %1$s Time: %2$s\n"
        + "Worked for: %3$.2f hours Salary per day: $%4$.2f";

    private double checkedInHour;
    private double currHour;
    private String currentTime = currentTime();
    private String messageSucess;
    private Password password;
    private Mode mode;
    private Nric nric;
    private CheckedInTime checkedInTime;
    private Person personLoggedIn;
    private Person personToEdit;
    private double hoursWorked;
    private double salaryPerDay;

    public CheckCommand(Nric nric, Password password, Mode mode) {
        requireAllNonNull(nric, password, mode);
        double currSecond;
        double currMinute;
        String[] currTimeArray = splitTime(currentTime);
        currSecond = Double.parseDouble(currTimeArray[2]);
        currMinute = Double.parseDouble(currTimeArray[1]);

        this.nric = nric;
        this.password = password;
        this.mode = mode;
        this.currHour = Double.parseDouble(currTimeArray[0]) + currMinute / 60 + currSecond / 3600;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        SessionManager sessionManager = SessionManager.getInstance(model);

        if (!sessionManager.isLoggedIn()) {
            throw new CommandException(MESSAGE_NOT_LOGIN);
        } else {
            personLoggedIn = sessionManager.getLoggedInPersonDetails();

            if (!isUserValid(model)) {
                throw new CommandException(MESSAGE_NOT_FOUND);
            }

            if (personLoggedIn.getPriorityLevel().priorityLevelCode == PriorityLevelEnum.BASIC.getPriorityLevelCode()
                && !personLoggedIn.getNric().toString().equals(nric.toString())) {
                throw new CommandException(MESSAGE_NOT_AUTHORISED);
            }

            if (personToEdit.getMode().equals(mode)) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE, mode));
            }

            if (mode.equals(new Mode("in"))) {
                checkedInTime = new CheckedInTime(currentTime);
                messageSucess = MESSAGE_CHECKED_IN;
            } else {
                hoursWorked = calculateHoursWorked(personToEdit.getCheckedInTime().toString());
                salaryPerDay = hoursWorked * (Double.parseDouble(personToEdit.getWorkingRate().toString()));
                checkedInTime = new CheckedInTime("");
                messageSucess = MESSAGE_CHECKED_OUT;
            }

            Person editedPerson = new Person(personToEdit.getName(), personToEdit.getNric(),
                personToEdit.getPassword(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getDepartment(), personToEdit.getPriorityLevel(), personToEdit.getAddress(),
                mode, personToEdit.getWorkingRate(), checkedInTime, personToEdit.getTags(),
                personToEdit.getSchedule());

            model.updatePerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
            model.commitAddressBook();

            return new CommandResult(String.format(messageSucess, currentDate(), currentTime,
                hoursWorked, salaryPerDay));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckCommand)) {
            return false;
        }

        // state check
        CheckCommand e = (CheckCommand) other;
        return nric.equals(e.nric)
            && password.equals(e.password)
            && mode.equals(e.mode);
    }

    /**
     * Returns true if Nric and Password are equal to the one in AddressBook.
     */
    private boolean isUserValid(Model model) {
        // Grabs the list of ALL people in the address book.
        List<Person> allPersonsList = model.getAddressBook().getPersonList();

        for (Person currPerson : allPersonsList) {
            if ((currPerson.getNric()).toString().equals(nric.toString())) {
                if ((currPerson.getPassword()).toString().equals(password.toString())) {
                    personToEdit = currPerson;
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * Returns current date.
     */
    public static String currentDate() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate dateNow = LocalDate.now();

        return date.format(dateNow);
    }

    /**
     * Returns current time.
     */
    public static String currentTime() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime timeNow = LocalTime.now();

        return time.format(timeNow);
    }

    /**
     * Returns hours worked.
     */
    private double calculateHoursWorked(String checkedInTime) {
        String[] checkedInTimeArray = splitTime(checkedInTime);

        double checkedInSecond = Double.parseDouble(checkedInTimeArray[2]);
        double checkedInMinute = Double.parseDouble(checkedInTimeArray[1]);
        checkedInHour = Double.parseDouble(checkedInTimeArray[0]) + checkedInMinute / 60 + checkedInSecond / 3600;
        return currHour - checkedInHour;
    }

    /**
     * Returns array contains hours, minutes and seconds separately.
     */
    public static String[] splitTime(String time) {
        String[] timeTokens = time.split("\\:");
        String[] timeArray = new String[3];

        for (int i = 0; i < timeTokens.length; i++) {
            timeArray[i] = timeTokens[i];
        }

        return timeArray;
    }

}
