package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.password.Password;
import seedu.address.model.prioritylevel.PriorityLevelEnum;
import seedu.address.testutil.PersonBuilder;

import systemtests.SessionHelper;

//@@author jylee-git
public class LoginCommandTest {

    private static final Nric CORRECT_NRIC = new Nric("S0000001A");
    private static final Password CORRECT_PASSWORD = new Password("NeUeR2018");
    private static final Nric WRONG_NRIC = new Nric("T2525254E");
    private static final Password WRONG_PASSWORD = new Password("PASSword");

    private Model model = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();
    private ModelManager expectedModel;
    private Person personA;

    @BeforeEach
    public void setUp() throws CommandException {
        SessionHelper.forceLoginWithPriorityLevelOf(PriorityLevelEnum.ADMINISTRATOR.getPriorityLevelCode());
        personA = new PersonBuilder().withName("Michael").withNric(CORRECT_NRIC.toString())
                .withPassword(CORRECT_PASSWORD.toString()).build();
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(personA);
        expectedModel.commitAddressBook();
        new AddCommand(personA).execute(model, commandHistory);
        SessionHelper.logoutOfSession();
    }

    //Lambda function solution adopted from https://howtodoinjava.com/junit5/expected-exception-example/
    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new LoginCommand(null, null);
        });
    }

    @Test
    public void execute_wrongNric_throwsCommandException() {
        Assertions.assertThrows(CommandException.class, () -> {
            new LoginCommand(WRONG_NRIC, CORRECT_PASSWORD).execute(model, commandHistory);
        }, LoginCommand.INVALID_LOGIN_CREDENTIALS);
    }

    @Test
    public void execute_wrongPassword_throwsCommandException() {
        Assertions.assertThrows(CommandException.class, () -> {
            new LoginCommand(CORRECT_NRIC, WRONG_PASSWORD).execute(model, commandHistory);
        }, LoginCommand.INVALID_LOGIN_CREDENTIALS);
    }

    @Test
    public void execute_validAccount_success() {
        LoginCommand loginCommand = new LoginCommand(CORRECT_NRIC, CORRECT_PASSWORD);

        String expectedResult = String.format(LoginCommand.LOGIN_SUCCESS, CORRECT_NRIC.toString())
                + "\nWelcome Michael" + "\nNo Schedule Available";

        assertCommandSuccess(loginCommand, model, commandHistory, expectedResult, expectedModel);
    }

    @Test
    public void ececute_loginWithoutLoggingOut_throwsCommandException() {
        SessionHelper.forceLoginWithPriorityLevelOf(PriorityLevelEnum.BASIC.getPriorityLevelCode());
        try {
            Assertions.assertThrows(CommandException.class, () -> {
                new LoginCommand(CORRECT_NRIC, CORRECT_PASSWORD).execute(model, commandHistory);
            }, LoginCommand.ALREADY_LOGGED_IN);
        } finally {
            SessionHelper.logoutOfSession();
        }
    }

    @AfterEach
    void tearDown() {
        SessionHelper.logoutOfSession();
    }
}
