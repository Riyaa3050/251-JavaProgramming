import java.util.*;
import java.util.concurrent.CountDownLatch;
class Course
{
    private int id;
    private String name;
    private float credits;
    public Course(int id,String name,float credits)
    {
        this.id=id;
        this.name=name;
        this.credits=credits;
    }
    public int getid()
    {
        return id;
    }
    public String getname()
    {
        return name;
    }
    public float getcredits()
    {
        return credits;
    }
   /*  public String tosString()
    {
        return " id : " + id + "name  : " + name + "credits : " + credits  ;
    }*/

}
class Student
{
    private int sid;
    private String sname;
   // private float credits;
    public Student(int sid,String sname)
    {
        this.sid=sid;
        this.sname=sname;
       // this.credits=credits;
    }
    public int studentgetid()
    {
        return sid;
    }
    public String studentgetname()
    {
        return sname;
    }
}
class Enrollment{
    private Student student[];
    private Course course[];
    private int count;
    private boolean[][] enrollmentstatus; //if it is enrolled then it will return true;
    public Enrollment(int maxs,int maxc)
    {
       student=new Student[maxs];
       course=new Course[maxc];
       enrollmentstatus=new boolean[maxs][maxc];
    }
    public void addStudent(Student mainstudent)
    {
        for(int i=0;i<student.length;i++)
        {
             if(student[i]==null)
             {
               student[i]=mainstudent;
               break;
             }
        }
    }
    public void addcourse(Course maincourse)
    {
        for(int i=0;i<course.length;i++)
        {
             if(course[i]==null)
             {
               course[i]=maincourse;
               break;
             }
        }
    }
    public void enroll(int sid,int id)
    {
         int si=findstudent(sid);
         int ci=findcourse(id);
         if(si != -1 && ci != -1)
         {
           enrollmentstatus[si][ci]=true;
         }
    }
    public void drop(int sid,int id)
    {
        int si=findstudent(sid);
        int ci=findcourse(id);
        if(si != -1 && ci != -1)
        {
          enrollmentstatus[si][ci]=false;
        }
    }
    public void viewcourses(int sid)
    {
        int si=findstudent(sid);
        if(si!= -1)
        {
            System.out.println("Courses for student id : " +sid);
            for(int i=0;i<course.length;i++)
            {
               if(enrollmentstatus[si][i])
               {
                System.out.println("course id : "+course[i].getid()+ "couse name : " +course[i].getname());
               }
            }
        }
    }
    private int findstudent(int sid)
    {
        for(int i=0;i<student.length;i++)
        {
           if(student[i].studentgetid()==sid)
           {
            return i;
           }
        }
        return -1;
    }
    private int findcourse(int cid)
    {
        for(int i=0;i<course.length;i++)
        {
           if(course[i].getid()==cid)
           {
            return i;
           }
        }
        return -1;
    }

}
class Courseenrollment
{
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
      Enrollment enrollment=new Enrollment(100, 10);  
      int ch=0;
      do {
        System.out.println("\nStudent Record System");
        System.out.println("1. Add Student");
        System.out.println("2. Add course");
        System.out.println("3. Search enroll students data :");
        System.out.println("4. view enrolled courses for a student");
        System.out.println("5. drop course");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
            //student 
            System.out.println("enter id : ");
            int sid=scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Name: ");
            String sname = scanner.nextLine();
            Student student=new Student(sid, sname);
            enrollment.addStudent(student);

            break;
            case 2:
            //course
            System.out.println("enter id : ");
            int id=scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter credits: ");
            float credits = scanner.nextFloat();
            Course course=new Course(id, name , credits);
            enrollment.addcourse(course);
           break;
           case 3:
           //enrolled students data
           System.out.println("enter id of student: ");
           int eid=scanner.nextInt();
           System.out.println("enter id of course : ");
           int ecid=scanner.nextInt();
           enrollment.enroll(eid,ecid);
           break;
           case 4:
            // View enrolled courses for a student
            System.out.println("enter id of course : ");
            int csid=scanner.nextInt();
            enrollment.viewcourses(csid);

           case 5:
             //drop course
                      System.out.println("enter id of student: ");
                      int did=scanner.nextInt();
                      System.out.println("enter id of course : ");
                      int dcid=scanner.nextInt();
                      enrollment.drop(did, dcid);
                      break;

           default:
           System.out.println("Invalid choice. Please try again.");
           break;
}
System.out.println("enter 1 to continue or 0 to exit : ");
ch=scanner.nextInt();

}while(ch != 0);
    
}
}
