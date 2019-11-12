public class Enrollment {
    private String date;
    private long classId;
    private long studentId;
    private String grade;

    public Enrollment(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    @Override
    public String toString(){
        return this.classId + "-class id and " + this.studentId + "-student id \n"
                + this.date;
    }
}
