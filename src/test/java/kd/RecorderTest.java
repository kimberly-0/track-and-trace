package kd;

import kd.datahandlers.Recorder;
import kd.models.*;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the methods that record a User, Establishment, and Event
 */
public class RecorderTest extends TestBase {

    private Recorder recorder;

    @Before
    public void setUp() {
        recorder = new Recorder();
    }

    @Test
    public void testRecordUser() {
        String input = String.format("Kim Doe%s01/01/1990%skim@email.com%s07123000000",
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator());

        assertThat(recorder.recordUser(new Scanner(input)), instanceOf(User.class));

        assertEquals(LocalDate.of(1990, 1, 1), recorder.recordUser(new Scanner(input)).getDOB());
        assertEquals("kim@email.com", recorder.recordUser(new Scanner(input)).getEmailAddress());
        assertEquals("07123000000", recorder.recordUser(new Scanner(input)).getPhoneNumber());
        assertEquals(Period.between(LocalDate.of(1990, 1, 1), LocalDate.now()).getYears(),
                recorder.recordUser(new Scanner(input)).getAge());
    }

    @Test
    public void testRecordEstablishment() {
        String input = String.format("Some Restaurant Place%s1 Queen Street%sFA2 3KE%s%d",
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), 62);

        assertThat(recorder.recordEstablishment(new Scanner(input)), instanceOf(Establishment.class));

        assertEquals("Some Restaurant Place", recorder.recordEstablishment(new Scanner(input)).getName());
        assertEquals("1 Queen Street", recorder.recordEstablishment(new Scanner(input)).getFirstLineAddress());
        assertEquals("FA2 3KE", recorder.recordEstablishment(new Scanner(input)).getPostCode());
        assertEquals(62, recorder.recordEstablishment(new Scanner(input)).getMaxOccupancy());
    }

    @Test
    public void testRecordEvent() {
        String input = String.format(
                "Kim Doe%s01/01/1990%skim@email.com%s07123000000%s23/09/2019%s18:30%s%d%sSome Restaurant Place%s1 Queen Street%sFA2 3KE%s%d",
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
                System.lineSeparator(), System.lineSeparator(), 2, System.lineSeparator(), System.lineSeparator(),
                System.lineSeparator(), System.lineSeparator(), 62);

        assertThat(recorder.recordEvent(new Scanner(input)), instanceOf(Event.class));

        assertThat(recorder.recordEvent(new Scanner(input)).getUser(), instanceOf(User.class));

        assertThat(recorder.recordEvent(new Scanner(input)).getID(), instanceOf(Integer.class));
        assertEquals(LocalDate.of(2019, 9, 23), recorder.recordEvent(new Scanner(input)).getEventDate());
        assertEquals(LocalTime.of(18, 30), recorder.recordEvent(new Scanner(input)).getEventTime());
        assertEquals(2, recorder.recordEvent(new Scanner(input)).getPartyNumber());

        assertThat(recorder.recordEvent(new Scanner(input)).getEstablishment(), instanceOf(Establishment.class));
    }

}