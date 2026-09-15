import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PaymentGateway gateway = new PaymentGateway();

        while (true) {
            System.out.println("================================");
            System.out.println("          LICEO PAY");
            System.out.println("================================");
            System.out.println("1. Make Payment");
            System.out.println("2. Show All Receipts");
            System.out.println("3. Find Payment");
            System.out.println("4. Total Collected");
            System.out.println("5. Refund All Refundable");
            System.out.println("6. Show Service Fees");
            System.out.println("7. Exit");
            System.out.println("================================");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    makePayment(sc, gateway);
                    break;
                case 2:
                    System.out.println();
                    gateway.processAll();
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter ID to find: ");
                    int findId = sc.nextInt();
                    sc.nextLine();
                    Payment found = gateway.findById(findId);
                    if (found != null) {
                        System.out.println();
                        found.printReceipt();
                        found.printThankYou();
                        System.out.println();
                    } else {
                        System.out.println("Payment not found.");
                    }
                    break;
                case 4:
                    System.out.printf("Total collected: PHP %.2f%n", gateway.totalCollected());
                    break;
                case 5:
                    System.out.println();
                    gateway.refundAll();
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    gateway.showServiceFees();
                    System.out.println();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void makePayment(Scanner sc, PaymentGateway gateway) {
        System.out.println();
        System.out.println("--- Make Payment ---");
        System.out.println("1. GCash");
        System.out.println("2. Maya");
        System.out.println("3. Cash");
        System.out.print("Choose payment method: ");
        int method = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter payer name: ");
        String name = sc.nextLine();
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Payment payment = null;
        switch (method) {
            case 1: // GCash
                System.out.print("Enter mobile number: ");
                String mobile = sc.nextLine();
                payment = new GCashPayment(id, name, amount, mobile);
                break;
            case 2: // Maya
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                payment = new MayaPayment(id, name, amount, email);
                break;
            case 3: // Cash
                payment = new CashPayment(id, name, amount);
                break;
            default:
                System.out.println("Invalid method.");
                return;
        }

        gateway.add(payment);
        System.out.println("Payment added successfully.");
        System.out.println();
    }
}