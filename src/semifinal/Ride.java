package semifinal;

public abstract class Ride {
    private String passenger;
    private double km;

    public Ride(String passenger, double km) {
        this.passenger = passenger;
        this.km = km;
    }

    public String getPassenger() {
        return passenger;
    }

    public double getKm() {
        return km;
    }

    // TODO 2: write your code here
    public abstract double fare();
    public abstract String vehicle();

    public void printTicket() {
        System.out.println("================================");
        System.out.println("Ticket #      | " + vehicle());
        System.out.println("Passenger     : " + passenger);
        System.out.println("Distance      : " + km + " km");
        System.out.println("Fare          : PHP " + String.format("%.2f", fare()));
        System.out.println("================================");
    }

    // TODO 3: write your code here
    public void printTicket(String note) {
        printTicket();
        System.out.println("Note                : " + note);
    }
}