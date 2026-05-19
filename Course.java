package individual1;

import java.util.ArrayList;

public class Course {

    private String title;
    private String teacherId;
    private ArrayList<String> studentIds = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void addStudentId(String studentId) {
        studentIds.add(studentId);
    }

    public String getTitle() {
        return title;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public ArrayList<String> getStudentIds() {
        return studentIds;
    }
}
