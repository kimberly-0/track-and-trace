package kd.datahandlers;

import java.time.LocalDate;
import java.util.ArrayList;

import kd.models.*;

/**
 * The Controller class is responsible for storing and retrieving information
 * regarding Users, Establishments, and Events.
 * 
 * Updated on 02/03/2023
 * 
 * @author Kimberly Dijkmans
 */
public class Controller {

    private ArrayList<Establishment> establishments;
    private ArrayList<Event> events;

    public Controller() {
        this.establishments = new ArrayList<>();
        this.events = new ArrayList<>();
    };

    public boolean addEstablishment(Establishment establishment) {
        for (Establishment e : establishments) {
            if (e.equals(establishment)) {
                return false; // Establishment already exists
            }
        }
        return establishments.add(establishment);
    }

    public boolean addEvent(Event event) {
        for (Event e : events) {
            if (e.equals(event)) {
                return false; // Event already exists
            }
        }
        // Add establishment to list of establishments if it doesn't already exist
        addEstablishment(event.getEstablishment());

        return events.add(event);
    }

    public ArrayList<Establishment> getEstablishments() {
        return establishments;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * This method filters events by a given establishment
     * 
     * @param nameOfEstablishment The name of establishment to filter on
     * @return list of events for the specific establishment
     */
    public ArrayList<Event> filterEventByEstablishment(String nameOfEstablishment) {
        ArrayList<Event> filteredRecords = new ArrayList<>();

        for (Event e : events) {
            if (e.getEstablishment().getName().equals(nameOfEstablishment)) {
                filteredRecords.add(e);
            }
        }
        return filteredRecords;
    }

    /**
     * This method filters the events by a specific date
     * 
     * @param eventDate The date of event to filter on
     * @return list of events for the specific date
     */
    public ArrayList<Event> filterEventByDate(LocalDate eventDate) {
        ArrayList<Event> filteredRecords = new ArrayList<>();

        for (Event e : events) {
            if (e.getEventDate().equals(eventDate)) {
                filteredRecords.add(e);
            }
        }
        return filteredRecords;
    }

    /**
     * This method filters the events by a specific user
     * 
     * @param userName  The name of user to filter on
     * @param userEmail The email of user to filter on
     * @return list of events for a specific user
     */
    public ArrayList<Event> filterEventByUser(String userName, String userEmail) {
        ArrayList<Event> filteredRecords = new ArrayList<>();

        for (Event e : events) {
            if (e.getUser().getName().equals(userName) && e.getUser().getEmailAddress().equals(userEmail)) {
                filteredRecords.add(e);
            }
        }
        return filteredRecords;
    }

}
