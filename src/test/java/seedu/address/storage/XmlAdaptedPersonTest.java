package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.XmlAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.password.Password;
import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.testutil.Assert;

public class XmlAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_DEPARTMENT = "Juni@r D@p@rtm@nt";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PRIORITYLEVEL = "-5";
    private static final String INVALID_NRIC = "Z9420873T";
    private static final String INVALID_PASSWORD = "@ert";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_PASSWORD = BENSON.getPassword().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_DEPARTMENT = BENSON.getDepartment().toString();
    private static final String VALID_PRIORITYLEVEL = Integer.toString(BENSON.getPriorityLevel().priorityLevelCode);
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<XmlAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(XmlAdaptedTag::new)
            .collect(Collectors.toList());
    private static final XmlAdaptedSchedule VALID_SCHEDULE = new XmlAdaptedSchedule(BENSON.getSchedule());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        XmlAdaptedPerson person = new XmlAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        XmlAdaptedPerson person =
                new XmlAdaptedPerson(INVALID_NAME, VALID_NRIC, VALID_PASSWORD, VALID_PHONE, VALID_EMAIL,
                        VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = Name.MESSAGE_NAME_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(null, VALID_NRIC, VALID_PASSWORD, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(VALID_NAME, INVALID_NRIC, VALID_PASSWORD, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = Nric.MESSAGE_NRIC_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(VALID_NAME, null, VALID_PASSWORD, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPassword_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, INVALID_PASSWORD, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = Password.MESSAGE_PASSWORD_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPassword_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, null, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Password.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        XmlAdaptedPerson person =
                new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, INVALID_PHONE, VALID_EMAIL,
                        VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = Phone.MESSAGE_PHONE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, null,
                VALID_EMAIL, VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        XmlAdaptedPerson person =
                new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, VALID_PHONE, INVALID_EMAIL,
                        VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = Email.MESSAGE_EMAIL_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, VALID_PHONE,
                null, VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDepartment_throwsIllegalValueException() {
        XmlAdaptedPerson person =
                new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, VALID_PHONE, VALID_EMAIL,
                        INVALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = Department.MESSAGE_DEPARTMENT_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDepartment_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, VALID_PHONE,
                VALID_EMAIL, null, VALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Department.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        XmlAdaptedPerson person =
                new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, VALID_PHONE, VALID_EMAIL,
                        VALID_DEPARTMENT, VALID_PRIORITYLEVEL, INVALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = Address.MESSAGE_ADDRESS_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENT, VALID_PRIORITYLEVEL, null, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<XmlAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new XmlAdaptedTag(INVALID_TAG));
        XmlAdaptedPerson person =
                new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, VALID_PHONE,
                        VALID_EMAIL, VALID_DEPARTMENT, VALID_PRIORITYLEVEL, VALID_ADDRESS, invalidTags,
                        VALID_SCHEDULE);
        Assert.assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPriorityLevel_throwsIllegalValueException() {
        XmlAdaptedPerson person = new XmlAdaptedPerson(VALID_NAME, VALID_NRIC, VALID_PASSWORD, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENT, INVALID_PRIORITYLEVEL, VALID_ADDRESS, VALID_TAGS, VALID_SCHEDULE);
        String expectedMessage = String.format(PriorityLevel.MESSAGE_PRIORITY_CONSTRAINTS);
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
