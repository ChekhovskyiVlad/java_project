package individual1;

public class Student extends Person {

    private long idStudent;
    private short grades[];
    public String enrolledCourses[];

    public Student(String name, String lastName, String gmail, String passwordHash) {
        super(name, lastName, gmail, passwordHash);
    }

}
