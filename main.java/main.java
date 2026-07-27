import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Arrays (max 10 students)
        int[] studentId = new int[10];
        String[] fullName = new String[10];
        int[] age = new int[10];
        String[] course = new String[10];
        double[] grade = new double[10];
        boolean[] enrolled = new boolean[10];
        
        int count = 0;
        int choice;
        
        do {
            printMenu();
            choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch (choice) {
                case 1: // Add Student
                    if (count >= 10) {
                        System.out.println("Student list is full!");
                        break;
                    }
                    System.out.print("Enter Student ID: ");
                    studentId[count] = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Full Name: ");
                    fullName[count] = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age[count] = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Course: ");
                    course[count] = sc.nextLine();
                    System.out.print("Enter Grade: ");
                    grade[count] = sc.nextDouble();
                    System.out.print("Is Enrolled (true/false): ");
                    enrolled[count] = sc.nextBoolean();
                    sc.nextLine();
                    
                    System.out.println(">> Student added successfully!");
                    count++;
                    break;
                    
                case 2: // View All Students
                    System.out.println("\n--- STUDENT RECORDS ---");
                    if (count == 0) {
                        System.out.println("No students recorded yet.");
                    } else {
                        System.out.printf("%-5s %-15s %-4s %-8s %-6s %-10s\n", 
                                          "ID", "NAME", "AGE", "COURSE", "GRADE", "STANDING");
                        for (int i = 0; i < count; i++) {
                            String standing = getStanding(grade[i]);
                            System.out.printf("%-5d %-15s %-4d %-8s %-6.1f %-10s\n", 
                                studentId[i], fullName[i], age[i], course[i], grade[i], standing);
                        }
                    }
                    break;
                    
                case 3: // Search by ID
                    System.out.print("Enter Student ID: ");
                    int search = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (studentId[i] == search) {
                            System.out.println("Student found!");
                            System.out.println("Name : " + fullName[i]);
                            System.out.println("Age : " + age[i]);
                            System.out.println("Course : " + course[i]);
                            System.out.println("Grade : " + grade[i]);
                            System.out.println("Enrolled : " + enrolled[i]);
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Student not found!");
                    break;
                    
                case 4: // Statistics
                    if (count == 0) {
                        System.out.println("No students yet.");
                        break;
                    }
                    double sum = 0;
                    double maxGrade = 0;
                    String topStudent = "";
                    for (int i = 0; i < count; i++) {
                        sum += grade[i];
                        if (grade[i] > maxGrade) {
                            maxGrade = grade[i];
                            topStudent = fullName[i];
                        }
                    }
                    System.out.println("\n--- STATISTICS ---");
                    System.out.println("Total Students : " + count);
                    System.out.printf("Average Grade : %.2f\n", sum / count);
                    System.out.println("Top Student   : " + topStudent + " (" + maxGrade + ")");
                    break;
                    
                case 5:
                    System.out.println("Thank you for using the Student Information System. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        
        sc.close();
    }
    
    public static String getStanding(double g) {
        if (g >= 90) return "Dean's Lister";
        else if (g >= 75) return "Passed";
        else return "Failed";
    }
    
    public static void printMenu() {
        System.out.println("\n===== STUDENT INFORMATION SYSTEM =====");
        System.out.println("[1] Add Student");
        System.out.println("[2] View All Students");
        System.out.println("[3] Search Student by ID");
        System.out.println("[4] View Statistics");
        System.out.println("[5] Exit");
        System.out.print("Enter choice: ");
    }
}