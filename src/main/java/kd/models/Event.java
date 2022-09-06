package kd.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * The Event class is responsible for creating an Event object.
 * 
 * Updated on 02/09/2022
 * 
 * @author Kimberly Dijkmans
 */
public class Event {

    private int eventID;
    private User user;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private int partyNumber;
    private Establishment establishment;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Event(User user, LocalDate date, LocalTime time, int partyNumber, Establishment establishment) {
        int min = 100000000;
        int max = 999999999;
        this.eventID = new Random().nextInt((max - min) + min) + min; // Generate random event ID
        this.user = user;
        this.eventDate = date;
        this.eventTime = time;
        this.partyNumber = partyNumber;
        this.establishment = establishment;
    }

    public Event(User user, int partyNumber, Establishment establishment) {
        this.user = user;
        this.partyNumber = partyNumber;
        this.establishment = establishment;
    }

    /**
     * @return event's ID
     */
    public int getID() {
        return eventID;
    }

    /**
     * @return event's user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return event's date
     */
    public LocalDate getEventDate() {
        return eventDate;
    }

    /**
     * @return event's time
     */
    public LocalTime getEventTime() {
        return eventTime;
    }

    /**
     * @return event's party number
     */
    public int getPartyNumber() {
        return partyNumber;
    }

    /**
     * @return event's establishment
     */
    public Establishment getEstablishment() {
        return establishment;
    }

    /**
     * This method prints the event to the command line interface
     */
    public void printEvent() {
        System.out.printf("%nEvent ID %d | Recorded User \n\t" +
                "Name %s\n\t" +
                "Date of Birth %s\n\t" +
                "Email %s\n\t" +
                "Contact Number %s\n\t" +
                "Age %d\n" +
                "Date %s\n" +
                "Time %s\n" +
                "Party Size %d\n" +
                "Establishment \n\t" +
                "Name %s\n\t" +
                "Address %s %s%n",
                eventID,
                user.getName(),
                user.getDOB().format(formatter),
                user.getEmailAddress(),
                user.getPhoneNumber(),
                user.getAge(),
                eventDate.format(formatter),
                eventTime,
                partyNumber,
                establishment.getName(),
                establishment.getFirstLineAddress(),
                establishment.getPostCode());
    }

    /**
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString() {
        return String.format("Event ID: %d | User: %s | Date & time: %s %s | Party number: %d | Establishment: %s%n",
                eventID,
                user,
                eventDate,
                eventTime,
                partyNumber,
                establishment);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Event))
            return false;

        final Event event = (Event) obj;
        return eventID == event.eventID
                && user.equals(event.user)
                && eventDate.equals(event.eventDate)
                && eventTime.equals(event.eventTime)
                && partyNumber == event.partyNumber
                && establishment.equals(event.establishment);
    }

}
