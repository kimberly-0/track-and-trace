package kd.datahandlers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import kd.models.*;

/**
 * The ImportCSV class is responsible for importing Establishments from a CSV
 * file.
 * 
 * @author Kimberly
 */
public class ImportCSV {

    /**
     * This method imports establishments from a CSV file
     * 
     * @param establishmentCSVFileURI The URI of the CSV file containing
     *                                establishments to import
     * @param controller              The controller that stores the list of
     *                                establishments
     */
    public void importEstablishmentsFromCSV(String establishmentCSVFileURI, Controller controller) {
        if (establishmentCSVFileURI != null) {
            try {
                // Create a file object from the given URI
                File establishmentsCSV = new File(ImportCSV.class.getResource(establishmentCSVFileURI).getFile());

                // Read file, parse lines, create Establishment objects and add to records
                parser(readCSV(establishmentsCSV), controller);

            } catch (Exception e) {
                System.out.println("Unable to import from file");
            }
        } else {
            System.out.println("No file to import records from");
        }
    }

    /*
     * This method reads the CSV file and stores each line into an array list
     */
    private ArrayList<String> readCSV(File establishmentCSV) {
        try {
            ArrayList<String> records = new ArrayList<>();
            Scanner scanner = new Scanner(establishmentCSV);
            while (scanner.hasNextLine()) {
                records.add(scanner.nextLine());
            }
            scanner.close();
            return records;
        } catch (Exception e) {
            System.out.println("Unable to read file");
            return null;
        }
    }

    /*
     * This method takes each line from the array list, parses it, creates an
     * Establishment object with those values, and adds it to the records
     */
    private void parser(ArrayList<String> records, Controller controller) {
        records.remove(0); // Removes the first record: the column headers of the csv file

        for (String line : records) {
            String[] elements = line.split(",");

            String name = elements[0];
            String firstLineAddress = elements[1];
            String postCode = elements[2];
            int maxOccupancy = Integer.parseInt(elements[3]);

            Establishment establishment = new Establishment(name, firstLineAddress, postCode, maxOccupancy);

            if (controller.addEstablishment(establishment)) {
                System.out.printf("%s%s%s%n", "Import from CSV -> Succesfully added ", establishment.getName(),
                        " to establishments");
            } else {
                System.out.printf("%s%s%s%n", "Import from CSV -> Establishment ", establishment.getName(),
                        " already exists");
            }
        }
    }

}
