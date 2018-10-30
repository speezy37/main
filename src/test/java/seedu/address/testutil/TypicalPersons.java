package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withNric("T2457888E").withPassword("ASd654").withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com").withDepartment("Top Management").withPhone("94351253")
            .withPriorityLevel(0).withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withNric("T2457846E").withPassword("ASd654").withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withDepartment("Top Management").withPhone("98765432")
            .withPriorityLevel(0).withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withNric("T2456699E").withPassword("ASd654").withPriorityLevel(0)
            .withEmail("heinz@example.com").withDepartment("Senior Management").withAddress("wall street")
            .withoutSchedule().build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withNric("S2457855E").withPassword("ASd654").withPriorityLevel(0)
            .withEmail("cornelia@example.com").withDepartment("Middle Management")
            .withAddress("10th street").withTags("friends").withoutSchedule().build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withNric("T0257855E").withPassword("ASd654").withPriorityLevel(0)
            .withEmail("werner@example.com").withDepartment("Junior Management")
            .withAddress("michegan ave").withoutSchedule().build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withNric("T2457005E").withPassword("ASd654").withPriorityLevel(0)
            .withEmail("lydia@example.com").withDepartment("Junior Management")
            .withAddress("little tokyo").withoutSchedule().build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withNric("T2457775E").withPassword("ASd654").withPriorityLevel(0)
            .withEmail("anna@example.com").withDepartment("Junior Management")
            .withAddress("4th street").withMode("out").withoutSchedule().build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withNric("T2457465H").withPassword("ASd654").withPriorityLevel(0)
            .withEmail("stefan@example.com").withDepartment("Junior Management")
            .withAddress("little india").withoutSchedule().build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withNric("T1234567E").withPassword("ASd654").withPriorityLevel(0)
            .withEmail("hans@example.com").withDepartment("Junior Management")
            .withAddress("chicago ave").withoutSchedule().build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withNric(VALID_NRIC_AMY)
            .withPassword(VALID_PASSWORD_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withDepartment(VALID_DEPARTMENT_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).withoutSchedule().build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withNric(VALID_NRIC_BOB).withPassword(VALID_PASSWORD_BOB)
            .withEmail(VALID_EMAIL_BOB).withDepartment(VALID_DEPARTMENT_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withoutSchedule().build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getEmptyAddressBook() {
        AddressBook ab = new AddressBook();
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
