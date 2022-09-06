package kd;

import kd.datahandlers.Controller;
import kd.models.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the methods that add a User, Establishment, or Event to the
 * records and the filter methods
 */
public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller();
    }

    @Test
    public void testAddEstablishmentThatDoesNotYetExist() {
        Establishment establishment = new Establishment("Bistro Vita", "4 Strawberry Place", "NE1 4RE", 250);

        assertTrue(controller.addEstablishment(establishment));
        assertTrue(controller.getEstablishments().contains(establishment));
    }

    @Test
    public void testAddEstablishmentThatDoesAlreadyExist() {
        Establishment establishment = new Establishment("Bistro Vita", "4 Strawberry Place", "NE1 4RE", 250);
        controller.addEstablishment(establishment); // Add once before so it already exists

        assertFalse(controller.addEstablishment(establishment));
    }

    @Test
    public void testAddEventThatDoesNotYetExist() {
        User user = new User("Kim Doe", LocalDate.of(1990, 1, 1), "kim@email.com", "07123000000");
        Establishment establishment = new Establishment("Some Restaurant Place", "1 Queen Street", "FA2 3KE", 62);
        Event event = new Event(user, LocalDate.of(2019, 9, 23), LocalTime.of(18, 30, 00), 2, establishment);

        assertTrue(controller.addEvent(event));
        assertTrue(controller.getEvents().contains(event));
    }

    @Test
    public void testAddEventThatDoesAlreadyExist() {
        User user = new User("Kim Doe", LocalDate.of(1990, 1, 1), "kim@email.com", "07123000000");
        Establishment establishment = new Establishment("Some Restaurant Place", "1 Queen Street", "FA2 3KE", 62);
        Event event = new Event(user, LocalDate.of(2019, 9, 23), LocalTime.of(18, 30, 00), 2, establishment);
        controller.addEvent(event); // Add once before so it already exists

        assertFalse(controller.addEvent(event));
    }

    @Test
    public void testAddingEventAlsoAddsEstablishment() {
        User user = new User("Kim Doe", LocalDate.of(1990, 1, 1), "kim@email.com", "07123000000");
        Establishment establishment = new Establishment("Some Restaurant Place", "1 Queen Street", "FA2 3KE", 62);
        Event event = new Event(user, LocalDate.of(2019, 9, 23), LocalTime.of(18, 30, 00), 2, establishment);
        controller.addEvent(event);

        assertTrue(controller.getEstablishments().contains(establishment));
    }

    @Test
    public void testFilterEventByEstablishment() {
        User user1 = new User("Kim Doe", LocalDate.of(1990, 1, 1), "kim@email.com", "07123000000");
        Establishment establishment1 = new Establishment("Some Restaurant Place", "1 Queen Street", "FA2 3KE", 62);
        Event event1 = new Event(user1, LocalDate.of(2019, 9, 23), LocalTime.of(18, 30, 00), 2, establishment1);
        controller.addEvent(event1);

        User user2 = new User("Sven", LocalDate.of(1948, 3, 2), "sven@email.com", "07321000000");
        Establishment establishment2 = new Establishment("Bistro Vita", "4 Strawberry Place", "NE1 4RE", 250);
        Event event2 = new Event(user2, LocalDate.of(2020, 10, 5), LocalTime.of(20, 10, 00), 15, establishment2);
        controller.addEvent(event2);

        assertTrue(controller.filterEventByEstablishment("Some Restaurant Place").contains(event1));
        assertFalse(controller.filterEventByEstablishment("Some Restaurant Place").contains(event2));
    }

    @Test
    public void testFilterEventByDate() {
        User user1 = new User("Kim Doe", LocalDate.of(1990, 1, 1), "kim@email.com", "07123000000");
        Establishment establishment1 = new Establishment("Some Restaurant Place", "1 Queen Street", "FA2 3KE", 62);
        Event event1 = new Event(user1, LocalDate.of(2019, 9, 23), LocalTime.of(18, 30, 00), 2, establishment1);
        controller.addEvent(event1);

        User user2 = new User("Sven", LocalDate.of(1948, 3, 2), "sven@email.com", "07321000000");
        Establishment establishment2 = new Establishment("Bistro Vita", "4 Strawberry Place", "NE1 4RE", 250);
        Event event2 = new Event(user2, LocalDate.of(2020, 10, 5), LocalTime.of(20, 10, 00), 15, establishment2);
        controller.addEvent(event2);

        assertTrue(controller.filterEventByDate(LocalDate.of(2019, 9, 23)).contains(event1));
        assertFalse(controller.filterEventByDate(LocalDate.of(2019, 9, 23)).contains(event2));
    }

    @Test
    public void testFilterEventByUser() {
        User user1 = new User("Kim Doe", LocalDate.of(1990, 1, 1), "kim@email.com", "07123000000");
        Establishment establishment1 = new Establishment("Some Restaurant Place", "1 Queen Street", "FA2 3KE", 62);
        Event event1 = new Event(user1, LocalDate.of(2019, 9, 23), LocalTime.of(18, 30, 00), 2, establishment1);
        controller.addEvent(event1);

        User user2 = new User("Sven", LocalDate.of(1948, 3, 2), "sven@email.com", "07321000000");
        Establishment establishment2 = new Establishment("Bistro Vita", "4 Strawberry Place", "NE1 4RE", 250);
        Event event2 = new Event(user2, LocalDate.of(2020, 10, 5), LocalTime.of(20, 10, 00), 15, establishment2);
        controller.addEvent(event2);

        assertTrue(controller.filterEventByUser("Kim Doe", "kim@email.com").contains(event1));
        assertFalse(controller.filterEventByUser("Kim Doe", "kim@email.com").contains(event2));
    }

}