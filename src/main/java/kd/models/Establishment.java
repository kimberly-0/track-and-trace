package kd.models;

/**
 * The Establishment class is responsible for creating a Establishment object.
 *
 * @author Kimberly
 */
public class Establishment {

    private String name;
    private String firstLineAddress;
    private String postCode;
    private int maxOccupancy;

    /**
     * This constructor creates a new Establishment with a set name, first line of
     * address, postcode, and maximum occupancy
     * 
     * @param name             The name of the establishment
     * @param firstLineAddress The first line of the address of the establishment
     * @param postCode         The post code of the establishment
     * @param maxOccupancy     The maximum occupancy of the establishment
     */
    public Establishment(String name, String firstLineAddress, String postCode, int maxOccupancy) {
        this.name = name;
        this.firstLineAddress = firstLineAddress;
        this.postCode = postCode;
        this.maxOccupancy = maxOccupancy;
    }

    /**
     * @return establishment's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return establishment's first line of address
     */
    public String getFirstLineAddress() {
        return firstLineAddress;
    }

    /**
     * @return establishment's postcode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @return establishment's maximum occupancy
     */
    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    /**
     * This method prints the establishment to the command line interface
     */
    public void printEstablishment() {
        System.out.printf("%nName %s%n" +
                "Address %s %s%n",
                name,
                firstLineAddress,
                postCode);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Address: %s %s | Max. occupancy: %d%n",
                name,
                firstLineAddress,
                postCode,
                maxOccupancy);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Establishment))
            return false;

        final Establishment establishment = (Establishment) obj;
        return name.equals(establishment.name)
                && firstLineAddress.equals(establishment.firstLineAddress)
                && postCode.equals(establishment.postCode)
                && maxOccupancy == establishment.maxOccupancy;
    }

}
