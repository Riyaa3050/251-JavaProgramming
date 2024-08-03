import java.util.*;
class Student {
    private int id;
    private String name;
    private int age;
    private String deptName;

    // Constructor
    public Student(int id, String name, int age, String deptName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.deptName = deptName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Department: " + deptName;
    }
}
class StudentSystem 
{
    private Student studentArray[];
    private int size; // the current number of students

    // Constructor
    public StudentSystem(int capacity) {
        studentArray = new Student[capacity];
        size = 0;
    }

    // Method to add a new student record
    public void addStudent(Student student) {
        if (size >= studentArray.length) {
            System.out.println("Error: Cannot add more students, array is full.");
        } else {
            studentArray[size++] = student;
            System.out.println("Student added successfully.");
        }
    }

    // Method to view all student records
    public void viewStudents() {
        if (size == 0) {
            System.out.println("No student records available.");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(studentArray[i]);
            }
        }
    }

    // Method to search for a student by ID
    public void searchStudentById(int id) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (studentArray[i].getId() == id) {
                System.out.println(studentArray[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public static void main(String[] args) {
        StudentSystem system = new StudentSystem(100); // Initialize with a capacity of 100 students
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Record System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add new student
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Department Name: ");
                    String deptName = scanner.nextLine();
                    
                    Student student = new Student(id, name, age, deptName);
                    system.addStudent(student);
                    break;

                case 2:
                    // View all students
                    system.viewStudents();
                    break;

                case 3:
                    // Search student by ID
                    System.out.print("Enter ID to search: ");
                    int searchId = scanner.nextInt();
                    system.searchStudentById(searchId);
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}