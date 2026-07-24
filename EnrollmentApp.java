import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EnrollmentApp {

    static class Student {
        private String studentId;
        private String fullName;
        private String program;
        private int yearLevel;

        public Student(String studentId, String fullName, String program, int yearLevel) {
            this.studentId = studentId;
            this.fullName = fullName;
            this.program = program;
            this.yearLevel = yearLevel;
        }

        public String getStudentId() { return studentId; }
        public String getFullName() { return fullName; }
        public String getProgram() { return program; }
        public int getYearLevel() { return yearLevel; }

        public String describe() {
            return studentId + " | " + fullName + " | " + program + " | Year " + yearLevel;
        }
    }

    static class Course {
        private String courseCode;
        private String title;
        private int units;
        private int capacity;
        private int enrolledCount;

        public Course(String courseCode, String title, int units, int capacity) {
            this.courseCode = courseCode;
            this.title = title;
            this.units = units;
            this.capacity = capacity;
            this.enrolledCount = 0;
        }

        public String getCourseCode() { return courseCode; }
        public String getTitle() { return title; }
        public int getUnits() { return units; }
        public int getCapacity() { return capacity; }
        public int getEnrolledCount() { return enrolledCount; }

        public boolean isFull() { return enrolledCount >= capacity; }

        public void addOneEnrollee() {
            if (!isFull()) enrolledCount++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        HashMap<String, ArrayList<String>> enrollments = new HashMap<>();
        String[] validPrograms = {"BSIT", "BSCS"};

        int choice = -1;
        while (choice != 0) {
            printMenu();
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1: // Register Student
                    System.out.println("\n--- REGISTER STUDENT ---");
                    System.out.print("Student ID : ");
                    String id = sc.nextLine().trim();
                    System.out.print("Full Name : ");
                    String name = sc.nextLine().trim();
                    System.out.print("Program : ");
                    String program = sc.nextLine().trim();
                    System.out.print("Year Level : ");
                    int year = Integer.parseInt(sc.nextLine().trim());

                    boolean validProg = false;
                    for (String p : validPrograms) {
                        if (p.equalsIgnoreCase(program)) {
                            validProg = true;
                            program = p;
                            break;
                        }
                    }
                    if (!validProg) {
                        System.out.println("Invalid program! Must be BSIT or BSCS.");
                        break;
                    }
                    if (year < 1 || year > 4) {
                        System.out.println("Invalid year level! Must be 1-4.");
                        break;
                    }

                    students.add(new Student(id, name, program, year));
                    System.out.println("[OK] Student registered successfully!");
                    break;

                case 2: // Add Course Offering
                    System.out.println("\n--- ADD COURSE OFFERING ---");
                    System.out.print("Course Code : ");
                    String code = sc.nextLine().trim();
                    System.out.print("Title : ");
                    String title = sc.nextLine().trim();
                    System.out.print("Units : ");
                    int units = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Capacity : ");
                    int cap = Integer.parseInt(sc.nextLine().trim());

                    courses.add(new Course(code, title, units, cap));
                    System.out.println("[OK] Course added successfully!");
                    break;

                case 3: // Enroll Student to Course
                    System.out.println("\n--- ENROLL STUDENT ---");
                    System.out.print("Student ID : ");
                    String sid = sc.nextLine().trim();
                    System.out.print("Course Code : ");
                    String ccode = sc.nextLine().trim();

                    Student student = null;
                    for (Student s : students) {
                        if (s.getStudentId().equals(sid)) {
                            student = s;
                            break;
                        }
                    }
                    if (student == null) {
                        System.out.println("Error: Student not found!");
                        break;
                    }

                    Course course = null;
                    for (Course c : courses) {
                        if (c.getCourseCode().equals(ccode)) {
                            course = c;
                            break;
                        }
                    }
                    if (course == null) {
                        System.out.println("Error: Course not found!");
                        break;
                    }
                    if (course.isFull()) {
                        System.out.println("Error: Course is full!");
                        break;
                    }

                    ArrayList<String> studentCourses = enrollments.getOrDefault(sid, new ArrayList<>());
                    if (studentCourses.contains(ccode)) {
                        System.out.println("Error: Student already enrolled in this course!");
                        break;
                    }

                    course.addOneEnrollee();
                    studentCourses.add(ccode);
                    enrollments.put(sid, studentCourses);
                    System.out.println("[OK] Student enrolled successfully!");
                    break;

                case 4: // View All Students
                    System.out.println("\n--- ALL STUDENTS ---");
                    if (students.isEmpty()) {
                        System.out.println("No students yet.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s.describe());
                        }
                    }
                    break;

                case 5: // View All Courses
                    System.out.println("\n--- ALL COURSES ---");
                    if (courses.isEmpty()) {
                        System.out.println("No courses yet.");
                    } else {
                        for (Course c : courses) {
                            System.out.println(c.getCourseCode() + " | " + c.getTitle() + " | " + 
                                c.getUnits() + " units | " + c.getEnrolledCount() + "/" + c.getCapacity());
                        }
                    }
                    break;

                case 6: // View Student Load
                    System.out.println("\n--- STUDENT LOAD ---");
                    System.out.print("Student ID : ");
                    String loadId = sc.nextLine().trim();

                    ArrayList<String> enrolled = enrollments.get(loadId);
                    if (enrolled == null || enrolled.isEmpty()) {
                        System.out.println("No courses enrolled or student not found.");
                        break;
                    }

                    int totalUnits = 0;
                    System.out.println("Enrolled Courses:");
                    for (String cc : enrolled) {
                        for (Course c : courses) {
                            if (c.getCourseCode().equals(cc)) {
                                System.out.println(" - " + cc + " (" + c.getTitle() + ") " + c.getUnits() + " units");
                                totalUnits += c.getUnits();
                                break;
                            }
                        }
                    }
                    System.out.println("Total Units: " + totalUnits);
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("\n=====================================");
        System.out.println("LICEO ENROLLMENT SYSTEM (CLI)");
        System.out.println("=====================================");
        System.out.println("[1] Register Student");
        System.out.println("[2] Add Course Offering");
        System.out.println("[3] Enroll Student to Course");
        System.out.println("[4] View All Students");
        System.out.println("[5] View All Courses");
        System.out.println("[6] View Student Load (Courses + Total Units)");
        System.out.println("[0] Exit");
        System.out.println("-------------------------------------");
        System.out.print("Enter choice: ");
    }
}