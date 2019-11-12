import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static ArrayList<Enrollment> enrollments = new ArrayList<>();
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Faculty> faculties = new ArrayList<>();
    public static ArrayList<Class> classes = new ArrayList<>();
    public static HashMap<String,Long> whoAmI = new HashMap<>();
    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        boolean login = false;
        generateData();
        System.out.println("Welcome! Would you like to login?(Y/N)");
        if(key.nextLine().equalsIgnoreCase("y")){
            int counter = 3;
            do {
                System.out.println("Are you a (S)tudent or (F)aculty?");
                login = promptLogin(key,counter);
                counter--;
                if(counter == 0){
                    System.out.println("No more tries. Sorry.");
                    break;
                }
            } while(login == false);
            if(login == true && whoAmI.containsKey("s")){
                System.out.println();
                System.out.println("List of all the classes: ");
                for (Class c : classes){
                    c.display();
                }
                System.out.print("Would you like to enroll in a class? (Y/N) ");
                String answer = key.nextLine();
                while(answer.equalsIgnoreCase("y")){
                    System.out.println("What is the Class ID?");
                    long id = key.nextLong();
                    key.nextLine();
                    if(checkClass(id)){
                        Date date = new Date();
                        Enrollment enrollment = new Enrollment();
                        enrollment.setClassId(id);
                        enrollment.setStudentId(whoAmI.get("s"));
                        enrollment.setDate(date.toString());
                        enrollment.setGrade("N/A");
                        enrollments.add(enrollment);
                        System.out.println("Would you like to enroll for another class? (Y/N) ");
                        answer = key.nextLine();
                    } else {
                        System.out.println("No such class exists, Try again.");
                        System.out.println("Would you like to enroll for another class? (Y/N) ");
                        answer = key.nextLine();
                    }
                }
                for(Enrollment e : enrollments){
                    System.out.println(e.toString());
                }
            }
            else if(login == true && whoAmI.containsKey("f")) {
                System.out.println();
                System.out.println("List of all the classes: ");
                for (Class c : classes){
                    c.display();
                }
                System.out.println("Would you like to be hired? (Y/N)");
                String answer = key.nextLine();
                while(answer.equalsIgnoreCase("y")){
                    System.out.println("What is the Class ID?");
                    long id = key.nextLong();
                    key.nextLine();
                    if(checkClass(id) && classes.get((int)id-1).getFaculty().getId() != whoAmI.get("f")){
                        System.out.println("We have updated the class to now be taught by you!");
                        classes.get((int)id-1).getFaculty().setId(whoAmI.get("f"));
                        System.out.println("Would you like to be hired for another class? (Y/N)");
                        answer = key.nextLine();
                    } else if (checkClass(id) && classes.get((int)id-1).getFaculty().getId() == whoAmI.get("f")) {
                        System.out.println("You are already teaching that class!");
                        System.out.println("Would you like to be hired for another class? (Y/N)");
                        answer = key.nextLine();
                    } else {
                        System.out.println("No class like that exists right now!");
                        System.out.println("Would you like to be hired for another class? (Y/N)");
                        answer = key.nextLine();
                    }
                }
                for(Class c : classes){
                    c.display();
                }
            }
        }

    }

    public static void generateData(){
        students.add(new Student(1,"student1@email.com","password","student1"));
        students.add(new Student(2,"student2@email.com","password","student2"));
        students.add(new Student(3,"student3@email.com","password","student3"));
        faculties.add(new Faculty(1,"faculty1@email.com","password","faculty1"));
        faculties.add(new Faculty(1,"faculty1@email.com","password","faculty2"));
        faculties.add(new Faculty(1,"faculty1@email.com","password","faculty3"));
        classes.add(new Class(1,faculties.get(0),"Java101","A java beginner's class"));
        classes.add(new Class(2,faculties.get(1),"Java102","A java beginner's class - second level"));
        classes.add(new Class(3,faculties.get(2),"Java103","A java beginner's class - third level"));
    }
    public static boolean promptLogin(Scanner key, int count){
        if(key.nextLine().equalsIgnoreCase("s")){

            System.out.println("Welcome Student");
            System.out.print("Enter your email: ");
            String email = key.nextLine();
            System.out.print("Enter your password: ");
            String pass = key.nextLine();
            if(checkStudent(email,pass) == true){
                System.out.println("you've logged in.");
                return true;
            } else {
                System.out.println("Please try again.");
                System.out.println("You have " + count + " more tries.");
                return false;
            }
        } else if (key.nextLine().equalsIgnoreCase("f")) {
            System.out.println("Welcome Faculty");
            System.out.print("Enter your email: ");
            String email = key.nextLine();
            System.out.print("Enter your password: ");
            String pass = key.nextLine();
            if(checkFaculty(email,pass) == true){
                System.out.println("you've logged in.");
                return true;
            } else {
                System.out.println("Please try again.");
                System.out.println("You have " + count + " more tries.");
                return false;
            }
        }
        System.out.println("You entered something wrong. Please try again.");
        return false;
    }
    public static boolean checkStudent(String email,String password){
        for(Student s : students){
            if(email.equalsIgnoreCase(s.getEmail()) && password.equalsIgnoreCase(s.getPassword())){
                whoAmI.put("s",s.getId());
                return true;
            }
        }
        return false;
    }
    public static boolean checkFaculty(String email,String password){
        for(Faculty f : faculties){
            if(email.equalsIgnoreCase(f.getEmail()) && password.equalsIgnoreCase(f.getPassword())){
                whoAmI.put("f",f.getId());
                return true;
            }
        }
        return false;
    }
    public static boolean checkClass(long id){
        for(Class c : classes){
            if(id == c.getId()){
                return true;
            }
        }
        return false;
    }
}
