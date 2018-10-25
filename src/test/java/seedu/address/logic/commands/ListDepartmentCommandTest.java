package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ListDepartmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {

        List<Person> lastShownList = model.getFilteredPersonList();
        List<String> departments = new ArrayList<>();

        for (int i = 0; i < lastShownList.size(); i++) {
            Person department = lastShownList.get(i);
            departments.add(department.getDepartment().toString());
        }

        Set<String> hs = new HashSet<>();
        hs.addAll(departments);
        departments.clear();
        departments.addAll(hs);

        Collections.sort(departments);

        String expectedMessage = String.format(ListDepartmentCommand.MESSAGE_SUCCESS,
                String.join(", ", departments));

        assertCommandSuccess(new ListDepartmentCommand(), model, commandHistory,
                expectedMessage, expectedModel);
    }
}
