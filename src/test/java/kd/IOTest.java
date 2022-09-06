package kd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.junit.Before;
import org.junit.Test;

import kd.datahandlers.Controller;

/**
 * This class tests the input and output of program via command line interface
 */
public class IOTest extends TestBase {

    protected IO io;
    protected Controller controller;

    @Before
    public void setUp() {
        io = new IO();
        controller = new Controller();
    }

    /**
     * Tests the function to record an Event (which includes recording a User and
     * Establishment) via the command line interface
     * and the printing of these events to the command line interface
     */
    @Test
    public void testPrintEvents() {
        String recordEvent = "1";
        String event1 = String.format(
                "Kim Doe%s01/01/1990%skim@email.com%s07123000000%s23/09/2019%s18:30%s%d%sSome Restaurant Place%s1 Queen Street%sFA2 3KE%s%d",
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
                System.lineSeparator(), System.lineSeparator(), 2, System.lineSeparator(), System.lineSeparator(),
                System.lineSeparator(), System.lineSeparator(), 62);
        String event2 = String.format(
                "Sven%s02/03/1948%ssven@email.com%s07321000000%s05/10/2020%s20:10%s%d%sBistro Vita%s4 Strawberry Place%sNE1 4RE%s%d",
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
                System.lineSeparator(), System.lineSeparator(), 15, System.lineSeparator(), System.lineSeparator(),
                System.lineSeparator(), System.lineSeparator(), 250);
        String exitProgram = "6";

        setInput(recordEvent + System.lineSeparator() + event1 + System.lineSeparator() + recordEvent
                + System.lineSeparator() + event2 + System.lineSeparator() + exitProgram);
        io.runMainMenu(); // Run main menu to record the events

        io.printEvents();

        String[] output = getOutputLines();
        int numberOfOutputLines = output.length;

        assertThat(output[numberOfOutputLines - 25], containsString("Event ID "));
        assertThat(output[numberOfOutputLines - 25], containsString(" | Recorded User"));
        assertThat(output[numberOfOutputLines - 24], containsString("Name Kim Doe"));
        assertThat(output[numberOfOutputLines - 22], containsString("Email kim@email.com"));
        assertThat(output[numberOfOutputLines - 21], containsString("Contact Number 07123000000"));
        assertThat(output[numberOfOutputLines - 20], containsString("Age 32"));
        assertThat(output[numberOfOutputLines - 19], containsString("Date 23/09/2019"));
        assertThat(output[numberOfOutputLines - 18], containsString("Time 18:30"));
        assertThat(output[numberOfOutputLines - 17], containsString("Party Size 2"));
        assertThat(output[numberOfOutputLines - 16], containsString("Establishment"));
        assertThat(output[numberOfOutputLines - 15], containsString("Name Some Restaurant Place"));
        assertThat(output[numberOfOutputLines - 14], containsString("Address 1 Queen Street FA2 3KE"));
        assertThat(output[numberOfOutputLines - 13], containsString(""));
        assertThat(output[numberOfOutputLines - 12], containsString("Event ID "));
        assertThat(output[numberOfOutputLines - 12], containsString(" | Recorded User"));
        assertThat(output[numberOfOutputLines - 11], containsString("Name Sven"));
        assertThat(output[numberOfOutputLines - 10], containsString("Date of Birth 02/03/1948"));
        assertThat(output[numberOfOutputLines - 9], containsString("Email sven@email.com"));
        assertThat(output[numberOfOutputLines - 8], containsString("Contact Number 07321000000"));
        assertThat(output[numberOfOutputLines - 7], containsString("Age 74"));
        assertThat(output[numberOfOutputLines - 6], containsString("Date 05/10/2020"));
        assertThat(output[numberOfOutputLines - 5], containsString("Time 20:10"));
        assertThat(output[numberOfOutputLines - 4], containsString("Party Size 15"));
        assertThat(output[numberOfOutputLines - 3], containsString("Establishment"));
        assertThat(output[numberOfOutputLines - 2], containsString("Name Bistro Vita"));
        assertThat(output[numberOfOutputLines - 1], containsString("Address 4 Strawberry Place NE1 4RE"));
    }

    @Test
    public void testPrintEventsWithNoEvents() {
        io.printEvents();
        assertThat(outputStream.toString(), containsString("No events were found"));
    }

    /**
     * Tests the function to record an Establishment via the command line interface
     * and the printing of these establishments to the command line interface
     */
    @Test
    public void testPrintEstablishments() {
        String addEstablishment = "2";
        String establishment1 = String.format(
                "Some Restaurant Place%s1 Queen Street%sFA2 3KE%s%d",
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), 62);
        String establishment2 = String.format(
                "Bistro Vita%s4 Strawberry Place%sNE1 4RE%s%d",
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), 250);
        String exitProgram = "6";

        setInput(addEstablishment + System.lineSeparator() + establishment1 + System.lineSeparator() + addEstablishment
                + System.lineSeparator() + establishment2 + System.lineSeparator() + exitProgram);
        io.runMainMenu(); // Run main menu to record the establishments

        io.printEstablishments();

        String[] output = getOutputLines();
        int numberOfOutputLines = output.length;

        assertThat(output[numberOfOutputLines - 5], containsString("Name Some Restaurant Place"));
        assertThat(output[numberOfOutputLines - 4], containsString("Address 1 Queen Street FA2 3KE"));
        assertThat(output[numberOfOutputLines - 3], containsString(""));
        assertThat(output[numberOfOutputLines - 2], containsString("Name Bistro Vita"));
        assertThat(output[numberOfOutputLines - 1], containsString("Address 4 Strawberry Place NE1 4RE"));
    }

    @Test
    public void testPrintEstablishmentsWithNoEstablishments() {
        io.printEstablishments();
        assertThat(outputStream.toString(), containsString("No establishments were found"));
    }

}
