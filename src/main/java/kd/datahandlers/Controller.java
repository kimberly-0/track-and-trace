package kd.datahandlers;

import java.time.LocalDate;
import java.util.ArrayList;

import kd.models.*;

/**
 * The Controller class is responsible for storing and retrieving information
 * regarding Users, Establishments, and Events.
 * 
 * Updated on 02/09/2022
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

    /**
     * This method adds an Establishment to the list of establishments if it doesn't
     * already exist
     * 
     * @param establishment
     * @return boolean indicating whether adding it to the list was successful
     */
    public boolean addEstablishment(Establishment establishment) {
        for (Establishment e : establishments) {
            if (e.equals(establishment)) {
                return false; // Establishment already exists
            }
        }
        return establishments.add(establishment);
    }

    /**
     * This method adds an Event to the list of events if it doesn't already exist
     * 
     * @param event
     * @return boolean indicating whether adding it to the list was successful
     */
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

    /**
     * @return establishment from the list of establishments
     */
    public ArrayList<Establishment> getEstablishments() {
        return establishments;
    }

    /**
     * @return event from the list of events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * This method filters events by a given establishment
     * 
     * @param nameOfEstablishment
     * @return
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
     * @param eventDate
     * @return list of events for a specific date
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
     * @param userName
     * @param userEmail
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
