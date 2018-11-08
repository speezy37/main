package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.LeaveList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyLeaveList;
import seedu.address.model.leave.Approval;
import seedu.address.model.leave.Date;
import seedu.address.model.leave.EmployeeId;
import seedu.address.model.leave.Leave;
import seedu.address.model.person.Address;
import seedu.address.model.person.CheckedInTime;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Mode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WorkingRate;
import seedu.address.model.person.password.Password;
import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.model.prioritylevel.PriorityLevelEnum;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.TimeEnd;
import seedu.address.model.schedule.TimeStart;
import seedu.address.model.schedule.Venue;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Leonard Tan"), new Nric("S1230000E"), new Password("Password"),
                new Phone("96469770"), new Email("admin@abbank-sg.com"), new Department("IT Management"),
                new PriorityLevel(PriorityLevelEnum.IT_UNIT.getPriorityLevelCode()),
                new Address("AB Company Office, Singapore"), new Mode("out"),
                new WorkingRate("10"), new CheckedInTime(""),
                getTagSet("itUnitAccount", "S1230000E", "Password"),
                getSchedule("2200", "0100", "Server Room")),

            new Person(new Name("Alex Yeoh"), new Nric("S1234567E"), new Password("Password"), new Phone("87438807"),
                new Email("alexyeoh@abbank-sg.com"), new Department("Top Management"),
                new PriorityLevel(PriorityLevelEnum.ADMINISTRATOR.getPriorityLevelCode()),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Mode("out"),
                new WorkingRate("7.5"), new CheckedInTime("18:00:00"),
                getTagSet("ADMINISTRATOR", "S1234567E", "Password"),
                getSchedule("1300", "1400", "Level 5")),

            new Person(new Name("Bernice Yu"), new Nric("T1234567E"), new Password("Password"), new Phone("99272758"),
                new Email("berniceyu@abbank-sg.com"), new Department("Senior Management"),
                new PriorityLevel(PriorityLevelEnum.MANAGER.getPriorityLevelCode()),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Mode("out"),
                new WorkingRate("7.5"), new CheckedInTime("18:00:00"),
                getTagSet("MANAGER", "T1234567E", "Password"),
                getSchedule("1100", "1600", "Level 4")),

            new Person(new Name("Charlotte Oliveiro"), new Nric("F1234567E"), new Password("Password"),
                new Phone("93210283"), new Email("charlotte@abbank-sg.com"), new Department("Middle Management"),
                new PriorityLevel(PriorityLevelEnum.BASIC.getPriorityLevelCode()),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Mode("out"),
                new WorkingRate("10"), new CheckedInTime("18:00:00"),
                getTagSet(),
                getSchedule("1000", "1700", "Counter 1")),

            new Person(new Name("David Li"), new Nric("S5473621G"), new Password("NeuEr2018"), new Phone("91031282"),
                new Email("lidavid@abbank-sg.com"), new Department("Junior Management"),
                new PriorityLevel(PriorityLevelEnum.BASIC.getPriorityLevelCode()),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Mode("out"),
                new WorkingRate("7.5"), new CheckedInTime("18:00:00"),
                getTagSet(),
                getSchedule("2200", "0800", "Main Door")),

            new Person(new Name("Irfan Ibrahim"), new Nric("S8570520Q"), new Password("NeuEr2018"),
                new Phone("92492021"), new Email("irfan@abbank-sg.com"), new Department("Junior Management"),
                new PriorityLevel(PriorityLevelEnum.BASIC.getPriorityLevelCode()),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Mode("out"),
                new WorkingRate("10"), new CheckedInTime("18:00:00"),
                getTagSet(), null),

            new Person(new Name("Roy Balakrishnan"), new Nric("F5169584T"), new Password("NeuEr2018"),
                new Phone("92624417"), new Email("royb@abbank-sg.com"), new Department("Junior Management"),
                new PriorityLevel(PriorityLevelEnum.BASIC.getPriorityLevelCode()),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Mode("out"),
                new WorkingRate("7.5"), new CheckedInTime("18:00:00"),
                getTagSet(), null)
        };
    }

    public static Leave[] getSampleLeaves() {
        return new Leave[]{
            new Leave(new EmployeeId("F5169584T"), new Date("01/12/2018"),
                new Approval("PENDING"), new PriorityLevel(3)),
            new Leave(new EmployeeId("S8570520Q"), new Date("20/01/2019"),
                new Approval("PENDING"), new PriorityLevel(3))
        };
    }

    public static ReadOnlyLeaveList getSampleLeaveList() {
        LeaveList sample = new LeaveList();
        for (Leave sampleLeave : getSampleLeaves()) {
            sample.addRequest(sampleLeave);
        }
        return sample;
    }



    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

    /**
     * Returns a Schedule containing the given timeStart, timeEnd and venue.
     */
    public static Schedule getSchedule(String timeStart, String timeEnd, String venue) {
        Schedule schedule;
        try {
            schedule = new Schedule(new TimeStart(timeStart), new TimeEnd(timeEnd), new Venue(venue));
        } catch (Exception e) {
            return null;
        }
        return schedule;
    }
}
