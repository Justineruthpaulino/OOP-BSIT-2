package semifinal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RideManager manager = new RideManager();
        Scanner sc = new Scanner(System.in);

        // Sample rides
        manager.addRide(new Jeepney("Ana Reyes", 5.5));
        manager.addRide(new Tricycle("Jerome Tan", 3.0));
        manager.addRide(new Taxi("Liza Cruz", 5.0));

        while (true) {
            System.out.println("\n===== LICEO RIDE =====");
            System.out.println("1. Book a ride");
            System.out.println("2. Show all tickets");
            System.out.println("3. Find passenger");
            System.out.println("4. Show student discounts");
            System.out.println("5. Show total sales");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.println("1. Jeepney");
                    System.out.println("2. Tricycle");
                    System.out.println("3. Taxi");
                    System.out.print("Choose vehicle: ");
                    int v = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Passenger name: ");
                    String name = sc.nextLine();
                    System.out.print("Distance (km): ");
                    double km = sc.nextDouble();
                    sc.nextLine();

                    Ride ride = null;
                    if (v == 1) ride = new Jeepney(name, km);
                    else if (v == 2) ride = new Tricycle(name, km);
                    else if (v == 3) ride = new Taxi(name, km);

                    if (ride != null) {
                        manager.addRide(ride);
                        ride.printTicket("Booked! Ingat sa biyahe.");
                    }
                    break;

                case 2:
                    manager.showAllTickets();
                    break;

                case 3:
                    System.out.print("Enter passenger name: ");
                    String search = sc.nextLine();
                    manager.findPassenger(search);
                    break;

                case 4:
                    manager.showStudentDiscounts();
                    break;

                case 5:
                    System.out.printf("TOTAL SALES: PHP %.2f from %d rides%n",
                            manager.totalSales(), 3); // temporary
                    break;
            }
        }
        sc.close();
    }
}