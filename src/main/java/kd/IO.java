package kd;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import kd.datahandlers.*;
import kd.models.*;

/**
 * <h1>COVID-19 track and trace system</h1>
 * The system will record and manage data via a command line interface.
 * The data is recorded for the purpose of tracing and alerting people who
 * have been in close contact with a person who tested positive for COVID-19.
 * The program enables an engineer to record and manage data about Events,
 * Establishments and Users.
 *
 * The IO class manages the input and output of the program, i.e. the command
 * line interface menus, the user's response, and the printed results.
 * 
 * Updated on 02/09/2022
 * 
 * @author Kimberly Dijkmans
 */
public class IO {

    private final static ImportCSV importcsv = new ImportCSV();
    private final static Controller controller = new Controller();
    private final static Recorder recorder = new Recorder();

    /*
     * This method prints the main menu options to the command line interface
     */
    private void printMainMenu() {
        System.out.printf("%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n",
                "------ MAIN MENU ------",
                "1. Record an Event",
                "2. Add Establishment",
                "3. Filters",
                "4. Print Events",
                "5. Print Establishments",
                "6. Exit the program",
                "------------------------");
    }

    /*
     * This method prints the filters menu options to the command line interface
     */
    private void printFiltersMenu() {
        System.out.printf("%n%s%n%s%n%s%n%s%n%s%n%s%n",
                "------ FILTER MENU ------",
                "1. Records by Establishment",
                "2. Records by Date",
                "3. Records by Name",
                "4. Go Back",
                "------------------------");
    }

    /**
     * This method displays and handles the main command line menu
     */
    public void runMainMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            printMainMenu(); // Print the main menu options
            System.out.println("Please select an option by typing the number");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine(); // Clear invalid input from scanner buffer
            }

            switch (choice) {
                case 1: // Record an Event
                    Event event = recorder.recordEvent(scanner);
                    if (controller.addEvent(event)) {
                        System.out.printf("%n%s%s%s%n", "Succesfully added ", event.getID(), " to events");
                    } else {
                        System.out.printf("%n%s%s%s%n", "Event ", event.getID(), " already exists");
                    }
                    break;
                case 2: // Add Establishment
                    Establishment establishment = recorder.recordEstablishment(scanner);
                    if (controller.addEstablishment(establishment)) {
                        System.out.printf("%n%s%s%s%n", "Succesfully added ", establishment.getName(),
                                " to establishments");
                    } else {
                        System.out.printf("%n%s%s%s%n", "Establishment ", establishment.getName(),
                                " already exists");
                    }
                    break;
                case 3: // Filters
                    quit = true; // Stop the while loop to stop the main menu
                    runFiltersMenu(scanner); // Run the sub menu (filters)
                    break;
                case 4: // Print Events
                    printEvents();
                    break;
                case 5: // Print Establishments
                    printEstablishments();
                    break;
                case 6: // Exit the program
                    System.out.printf("%n%s%n", "Exiting the program ...");
                    quit = true; // Stop the while loop to stop the main menu
                    scanner.close();
                    break;
                default:
                    System.out.printf("%n%s%n", "Not a valid option, try again");
            }
        }
    }

    /**
     * This method displays and handles the submenu for filtering
     */
    private void runFiltersMenu(Scanner scanner) {

        boolean quit = false;
        while (!quit) {
            printFiltersMenu(); // Print the filter menu options
            System.out.println("Please select an option from the filters menu");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine(); // Clear invalid input from scanner buffer
            }

            switch (choice) {
                case 1: // Print Users for a specific Establishment
                    System.out.println("Enter the Establishment's name you want to see the visitors of:");
                    printFilteredEventsByEstablishment(recordInput(scanner));
                    break;
                case 2: // Print Events for a specific date
                    System.out.println("Enter the Event's date you want to filter by in format dd/mm/yyyy:");
                    printFilteredEventsByDate(recordInput(scanner));
                    break;
                case 3: // Print Events for a specific User
                    System.out.println("Enter the User's name you want to filter by:");
                    String name = recordInput(scanner);
                    System.out.println("Enter the User's email address you want to filter by:");
                    String email = recordInput(scanner);
                    printFilteredEventsByUser(name, email);
                    break;
                case 4: // Go back
                    quit = true; // Stop the while loop to stop the sub menu (filters)
                    runMainMenu(); // Run main menu
                    break;
                default:
                    System.out.printf("%n%s%n", "Not a valid option, try again");
            }
        }
    }

    /*
     * This method enables the user to indicate for what they would like to filter
     */
    private String recordInput(Scanner scanner) {
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?"); // Skip the new line character
        String input = scanner.nextLine();
        return input;
    }

    /**
     * This method prints all recorded events to the command line interface
     */
    public void printEvents() {
        ArrayList<Event> events = controller.getEvents();
        if (events.size() > 0) {
            events.forEach((i) -> i.printEvent());
        } else {
            System.out.printf("%n%s%n", "No events were found");
        }
    }

    /**
     * This method prints all recorded establishments to the command line interface
     */
    public void printEstablishments() {
        ArrayList<Establishment> establishments = controller.getEstablishments();
        if (establishments.size() > 0) {
            establishments.forEach((i) -> i.printEstablishment());
        } else {
            System.out.printf("%n%s%n", "No establishments were found");
        }
    }

    /**
     * This method prints the users of the events for a specific establishment to
     * the command line interface
     */
    public void printFilteredEventsByEstablishment(String establishment) {
        ArrayList<Event> filteredRecords = controller.filterEventByEstablishment(establishment);
        if (filteredRecords.size() > 0) {
            filteredRecords.forEach((i) -> i.getUser().printUser());
        } else {
            System.out.printf("%n%s%s%n", "No events were found for ", establishment);
        }
    }

    /**
     * This method prints the events for a specific date to the command line
     * interface
     */
    public void printFilteredEventsByDate(String date) {
        try {
            LocalDate eventDate = LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("d/MM/yyyy"));
            ArrayList<Event> filteredRecords = controller.filterEventByDate(eventDate);
            if (filteredRecords.size() > 0) {
                filteredRecords.forEach((i) -> i.printEvent());
            } else {
                System.out.printf("%n%s%s%n", "No Events were found on ", date);
            }
        } catch (Exception e) {
            System.out.printf("%n%s%n", "Not a valid date format");
        }
    }

    /**
     * This method prints the events for a specific user to the command line
     * interface
     */
    public void printFilteredEventsByUser(String name, String email) {
        ArrayList<Event> filteredRecords = controller.filterEventByUser(name, email);
        if (filteredRecords.size() > 0) {
            filteredRecords.forEach((i) -> i.printEvent());
        } else {
            System.out.printf("%n%s%s%s%s%s%n",
                    "No events were found for ", name, " (email: ", email, ")");
        }
    }

    /*
     * Method for testing and debugging with hard-coded User, Establishment and
     * Event objects
     */
    private void debug() {
        importcsv.importEstablishmentsFromCSV("../resources/establishments.csv", controller);

        User user = new User("Kim Doe", LocalDate.of(1990, 1, 1), "kim@email.com", "07123000000");
        Establishment establishment = new Establishment("Some Restaurant Place", "2 Queen Street", "KE3 2FA", 15);
        Event event = new Event(user, LocalDate.of(2019, 9, 23), LocalTime.of(18, 30, 00), 2, establishment);
        controller.addEstablishment(establishment);
        controller.addEvent(event);

        User user1 = new User("Sven", LocalDate.of(1948, 3, 2), "sven@email.com", "07321000000");
        Establishment establishment1 = new Establishment("Bistro Vita", "4 Strawberry Place", "NE1 4RE", 250);
        Event event1 = new Event(user1, LocalDate.of(2021, 3, 8), LocalTime.of(20, 10, 00), 15, establishment1);
        controller.addEstablishment(establishment1);
        controller.addEvent(event1);

        User user2 = new User("Raj", LocalDate.of(1992, 5, 6), "raj@email.com",
                "07765000000");
        Event event2 = new Event(user2, LocalDate.of(2020, 10, 5), LocalTime.of(20,
                10, 00), 15, establishment1);
        controller.addEvent(event2);

        runMainMenu();
    }

    public static void main(String[] args) {
        // Run the program as normal without any Events, Establishments, and Users
        // recorded
        // new IO().runMainMenu();

        // Debug method to run the program with sample Events, Establishments, and Users
        new IO().debug();
    }

}