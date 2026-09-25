package semifinal;

import java.util.ArrayList;

public class RideManager {
    private ArrayList<Ride> rides = new ArrayList<>();

    public void addRide(Ride r) {
        rides.add(r);
    }

    public void showAllTickets() {
        System.out.println("ALL TICKETS (" + rides.size() + ")");
        // TODO 8: write your code here
        for (Ride r : rides) {
            r.printTicket();
        }
    }

    public void showStudentDiscounts() {
        System.out.println("STUDENT DISCOUNTS (20% off)");
        // TODO 9: write your code here
        for (Ride r : rides) {
            if (r instanceof StudentDiscount) {
                StudentDiscount s = (StudentDiscount) r;
                System.out.println(r.getPassenger() + " (" + r.vehicle() + ")");
                System.out.println("Student fare  : PHP " + String.format("%.2f", s.discountedFare()));
            }
        }
    }

    public double totalSales() {
        // TODO 10: write your code here
        double total = 0;
        for (Ride r : rides) {
            total += r.fare();
        }
        return total;
    }

    public void findPassenger(String name) {
        boolean found = false;
        for (Ride r : rides) {
            if (r.getPassenger().equalsIgnoreCase(name)) {
                r.printTicket();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Passenger not found.");
        }
    }
}