import java.util.ArrayList;

public class Class {
    private long id;
    private Faculty faculty;
    private String name;
    private String description;
    public Class(){

    }

    public Class(long id, Faculty faculty, String name, String description) {
        this.id = id;
        this.faculty = faculty;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void display(){
        System.out.println(getName() + " taught by " + getFaculty().getName());
        System.out.println(getDescription());
        System.out.println("Class ID: " + getId());
    }
}
