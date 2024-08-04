import java.util.Scanner;
 class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }
}
class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}
class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int[] courseIDs;
    private int[] courseCredits;

    private int studentCount;
    private int gradeCount;
    private int courseCount;

    private static final int MAX_STUDENTS = 100;
    private static final int MAX_GRADES = 500;
    private static final int MAX_COURSES = 50;

    public GradingSystem() {
        students = new Student[MAX_STUDENTS];
        grades = new Grade[MAX_GRADES];
        courseIDs = new int[MAX_COURSES];
        courseCredits = new int[MAX_COURSES];

        studentCount = 0;
        gradeCount = 0;
        courseCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < MAX_STUDENTS) {
            students[studentCount++] = student;
        } else {
            System.out.println("Maximum number of students reached.");
        }
    }

    public void addGrade(Grade grade) {
        if (gradeCount < MAX_GRADES) {
            grades[gradeCount++] = grade;
        } else {
            System.out.println("Maximum number of grades reached.");
        }
    }

    public void addCourseCredits(int courseID, int credits) {
        if (courseCount < MAX_COURSES) {
            courseIDs[courseCount] = courseID;
            courseCredits[courseCount++] = credits;
        } else {
            System.out.println("Maximum number of courses reached.");
        }
    }

    private int getCourseCredits(int courseID) {
        for (int i = 0; i < courseCount; i++) {
            if (courseIDs[i] == courseID) {
                return courseCredits[i];
            }
        }
        return 0; // default if courseID is not found
    }

    public double calculateCGPA(int studentID) {
        int totalCredits = 0;
        int totalPoints = 0;
        for (int i = 0; i < gradeCount; i++) {
            Grade grade = grades[i];
            if (grade.getStudentID() == studentID) {
                int credits = getCourseCredits(grade.getCourseID());
                totalCredits += credits;
                totalPoints += gradeToPoints(grade.getGrade()) * credits;
            }
        }
        return totalCredits == 0 ? 0 : (double) totalPoints / totalCredits;
    }

    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A': return 4;
            case 'B': return 3;
            case 'C': return 2;
            case 'D': return 1;
            case 'F': return 0;
            default: return 0;
        }
    }

    public void printGradeReport(int studentID) {
        System.out.println("Grade Report for Student ID: " + studentID);
        for (int i = 0; i < gradeCount; i++) {
            Grade grade = grades[i];
            if (grade.getStudentID() == studentID) {
                System.out.println("Course ID: " + grade.getCourseID() + ", Grade: " + grade.getGrade());
            }
        }
        System.out.println("CGPA: " + calculateCGPA(studentID));
    }
} 


 class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradingSystem gradingSystem = new GradingSystem();

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Add Course Credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Print Grade Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    gradingSystem.addStudent(new Student(studentID, name));
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter Grade (A/B/C/D/F): ");
                    char grade = scanner.next().charAt(0);
                    gradingSystem.addGrade(new Grade(studentID, courseID, grade));
                    break;
                case 3:
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextInt();
                    System.out.print("Enter Credits: ");
                    int credits = scanner.nextInt();
                    gradingSystem.addCourseCredits(courseID, credits);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    System.out.println("CGPA: " + gradingSystem.calculateCGPA(studentID));
                    break;
                case 5:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    gradingSystem.printGradeReport(studentID);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
