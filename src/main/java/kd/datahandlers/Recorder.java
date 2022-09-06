package kd.datahandlers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import kd.models.*;

/**
 * The Recorder class is responsible for recording new Users, Establishments,
 * and Events via the command line interface.
 * 
 * Updated on 02/09/2022
 * 
 * @author Kimberly Dijkmans
 */
public class Recorder {

    /**
     * This method creates a new User
     * 
     * @return user
     */
    public User recordUser(Scanner scanner) {
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?"); // Skip the new line character

        /*
         * Record the user's name
         */
        System.out.println("Enter the User's name:");
        String name = scanner.nextLine();

        /*
         * Record the user's date of birth
         */
        System.out.println("Enter the User's date of birth in format dd/mm/yyyy:");
        String date = scanner.nextLine();
        LocalDate dob = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));

        // Check if the dob is valid (not later than today), otherwise enter again
        boolean validDOB = false;
        while (!validDOB) {
            if (!dob.isAfter(LocalDate.now())) {
                validDOB = true;
            } else {
                System.out.println("Invalid date of birth, please enter again:");
                date = scanner.nextLine();
                dob = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
            }
        }

        /*
         * Record the user's email address
         */
        System.out.println("Enter the User's email address:");
        String emailaddress = scanner.nextLine();

        // Check if email address is valid (string contains @), otherwise enter again
        boolean validEmail = false;
        while (!validEmail) {
            if (emailaddress.contains("@")) {
                validEmail = true;
            } else {
                System.out.println("Invalid email address, please enter again:");
                emailaddress = scanner.nextLine();
            }
        }

        /*
         * Record the user's phone number
         */
        System.out.println("Enter the User's phone number:");
        String phonenumber = scanner.nextLine();

        // Check if the phone number is valid (string has contains 11 characters and
        // does not contain '+'), otherwise enter again
        boolean validPhoneNumber = false;
        while (!validPhoneNumber) {
            if (phonenumber.length() == 11 && !phonenumber.contains("+")) {
                validPhoneNumber = true;
            } else {
                System.out.println("Invalid phone number, please enter again:");
                phonenumber = scanner.nextLine();
            }
        }

        return new User(name, dob, emailaddress, phonenumber);
    }

    /**
     * This method creates a new Establishment
     * 
     * @return establishment
     */
    public Establishment recordEstablishment(Scanner scanner) {
        String name = null;
        String firstLineAddress = null;
        String postCode = null;
        int maxOccupancy = 0;

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?"); // Skip the new line character

        System.out.println("Enter the Establishment's name:");
        name = scanner.nextLine();

        System.out.println("Enter the Establishment's first line of address:");
        firstLineAddress = scanner.nextLine();

        System.out.println("Enter the Establishment's postcode:");
        postCode = scanner.nextLine();

        System.out.println("Enter the Establishment's maximum occupancy:");
        maxOccupancy = scanner.nextInt();

        if (name != null && firstLineAddress != null && postCode != null && maxOccupancy > 0) {
            return new Establishment(name, firstLineAddress, postCode, maxOccupancy);
        } else {
            return null;
        }

    }

    /**
     * This method creates a new Event
     * 
     * @return event
     */
    public Event recordEvent(Scanner scanner) {
        // Record a user
        User user = recordUser(scanner);

        // Record the event's date
        System.out.println("Enter the Event's date in format dd/mm/yyyy:");
        LocalDate eventDate = null;
        boolean validDate = false;
        while (!validDate) {
            String date = scanner.nextLine();
            try {
                eventDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
                validDate = true;
            } catch (Exception e) {
                System.out.printf("%s%n", "Not a valid date, try again, enter the Event's date in format dd/mm/yyyy:");
            }
        }

        // Record the event's time
        System.out.println("Enter the Event's time:");
        LocalTime eventTime = null;
        boolean validTime = false;
        while (!validTime) {
            String time = scanner.nextLine();
            try {
                eventTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm"));
                validTime = true;
            } catch (Exception e) {
                System.out.printf("%s%n", "Not a valid time, try again, enter the Event's time:");
            }
        }

        // Record the event's party number
        System.out.println("Enter the Event's party number:");
        int partyNumber = scanner.nextInt();

        // Record an establishment
        Establishment establishment = recordEstablishment(scanner);

        return new Event(user, eventDate, eventTime, partyNumber, establishment);
    }

}
