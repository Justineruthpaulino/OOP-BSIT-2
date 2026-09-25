package semifinal;

/**
 * Abstract class - the parent of every vehicle
 * TODO 2 - 3
 */
public abstract class Ride {
    private static int nextTicket = 1;

    private final int ticketNumber;
    private final String passenger;
    private final double km;

    public Ride(String passenger, double km) {
        this.ticketNumber = nextTicket++;
        this.passenger = passenger;
        this.km = km;
    }

    public String getPassenger() {
        return passenger;
    }

    public double getKm() {
        return km;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    // TODO 2: two public abstract methods
    public abstract double fare();
    public abstract String vehicle();

    /**
     * Prints a complete ticket. Relies on the abstract fare() and vehicle()
     * supplied by each concrete subclass (polymorphism / dynamic dispatch).
     */
    public void printTicket() {
        System.out.println("--------------------------------");
        System.out.println("Ticket #" + ticketNumber + " | " + vehicle());
        System.out.println("Passenger : " + passenger);
        System.out.printf("Distance  : %.1f km%n", km);
        System.out.printf("Fare      : PHP %.2f%n", fare());
        System.out.println("--------------------------------");
    }

    // TODO 3: overload printTicket with a note parameter
    public void printTicket(String note) {
        printTicket();
        System.out.println("Note      : " + note);
    }
}