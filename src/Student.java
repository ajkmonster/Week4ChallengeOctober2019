import java.util.ArrayList;

public class Student extends Person {

    public Student(){}

    public Student(long id, String email, String password, String name) {
        super(id, email, password, name);
    }

    public void enroll(Enrollment enrollment, Class c){
        enrollment.setStudentId(this.getId());
        enrollment.setClassId(c.getId());
   }
}
