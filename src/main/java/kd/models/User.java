package kd.models;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * The User class is responsible for creating a User object.
 *
 * @author Kimberly
 */
public class User {

    private String name;
    private LocalDate dob;
    private String emailaddress;
    private String phonenumber;
    private int age;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * This constructor creates a new User with a set name, date of birth, email
     * address, and phone number
     * 
     * @param name         The name of the user
     * @param dob          The date of birth of the user
     * @param emailaddress The email address of the user
     * @param phonenumber  The phone number of the user
     */
    public User(String name, LocalDate dob, String emailaddress, String phonenumber) {
        this.name = name;
        this.dob = dob;
        this.emailaddress = emailaddress;
        this.phonenumber = phonenumber;
        this.age = calcAge(dob);
    }

    /*
     * This method calculates the user's age based on the date of birth
     */
    private int calcAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    /**
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return user's date of birth
     */
    public LocalDate getDOB() {
        return dob;
    }

    /**
     * @return user's email address
     */
    public String getEmailAddress() {
        return emailaddress;
    }

    /**
     * @return user's phone number
     */
    public String getPhoneNumber() {
        return phonenumber;
    }

    /**
     * @return user's age
     */
    public int getAge() {
        return age;
    }

    /**
     * This method prints the user to the command line interface
     */
    public void printUser() {
        System.out.printf("%nName %s\n\t" +
                "Date of Birth %s\n\t" +
                "Email %s\n\t" +
                "Contact Number %s\n\t" +
                "Age %d%n",
                name,
                dob.format(formatter),
                emailaddress,
                phonenumber,
                age);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Date of birth: %s | Email address: %s | Phone number: %s | Age: %d%n",
                name,
                dob,
                emailaddress,
                phonenumber,
                age);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof User))
            return false;

        final User user = (User) obj;
        return name.equals(user.name)
                && dob.equals(user.dob)
                && emailaddress.equals(user.emailaddress)
                && phonenumber.equals(user.phonenumber)
                && age == user.age;
    }

}
