package semifinal;
import java.util.ArrayList;
/**
 * RideManager - keeps every booked ride in ONE list of type Ride.
 * RULE: this file must never name a child class of Ride.
 * It only talks to the abstraction (Ride and StudentDiscount).
 */
public class RideManager {
    
// GIVEN: one list that can hold ANY kind of ride (upcasting)
    private final ArrayList<Ride> rides = new ArrayList<>();
    
// GIVEN
    public void addRide(Ride ride) {
        rides.add(ride);
    }
    
// GIVEN: search by passenger name (not case-sensitive)
    public Ride findRide(String passenger) {
        for (Ride r : rides) {
            if (r.getPassenger().equalsIgnoreCase(passenger)) {
                return r;
            }
        }
        return null;
    }
    // 
TODO 8
    public void showAllTickets() {
        for (Ride r : rides) {
            r.printTicket();
        }
    }
    // 
TODO 9
    public void showStudentDiscounts() {
        for (Ride r : rides) {
            if (r instanceof StudentDiscount) {
                System.out.println(" " + r.getPassenger() + " (" + r.vehicle() + ")");
                StudentDiscount s = (StudentDiscount) r;
                s.printDiscount();
            }
        }
    }
    // 
TODO 10
    public double totalSales() {
        double total = 0;
        for (Ride r : rides) {
            total += r.fare();
        }
        return total;
    }
    
// GIVEN
    public int count() {
        return rides.size();
    }
}